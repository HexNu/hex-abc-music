package nu.hex.abc.music.service.xml.read;

import abc.music.core.ProjectCarrier;
import abc.music.core.domain.Comment;
import abc.music.core.domain.History;
import abc.music.core.domain.Key;
import abc.music.core.domain.Meter;
import abc.music.core.domain.Origin;
import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Tempo;
import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.io.Reader;

/**
 * Created 2016-dec-21
 *
 * @author hl
 */
public class AbcFileReader implements Reader<ProjectCarrier> {

    private final File file;
    private Charset charset;
    private String voiceBody = "";
    private Tune currentTune = new Tune();
    private Voice currentVoice = new Voice();

    public AbcFileReader(File file) {
        this.file = file;
    }

    @Override
    public ProjectCarrier read() {
        ProjectCarrier result = new ProjectCarrier();
        result.tunes = new ArrayList<>();
        result.persons = new ArrayList<>();
        findCharset();
        try {
            Files.readAllLines(file.toPath(), charset).stream().forEach((String line) -> {
                if (line != null && line.length() > 2) {
                    if (line.substring(1, 2).equals(":") && !line.substring(0, 1).equals("|")) {
                        String field = line.substring(0, 1);
                        String lineContent = line.substring(2).trim();
                        switch (field) {
                            case "X":
                                if (currentTune.getVoices().isEmpty()) {
                                    currentTune.addVoice(currentVoice);
                                    currentVoice.setVoiceId(String.valueOf(currentTune.getVoices().size()));
                                }
                                if (currentVoice.getNotes() == null || currentVoice.getNotes().isEmpty()) {
                                    currentVoice.setNotes(voiceBody);
                                    voiceBody = "";
                                }
                                currentTune = new Tune();
                                result.tunes.add(currentTune);
                                currentVoice = new Voice();
                                voiceBody = "";
                                break;
                            case "T":
                                currentTune.addTitle(lineContent);
                                break;
                            case "R":
                                currentTune.setRythm(lineContent);
                                break;
                            case "O":
                                currentTune.addOrigin(new Origin(lineContent));
                                break;
                            case "H":
                                currentTune.addHistory(new History(lineContent));
                                break;
                            case "N":
                                currentTune.addComment(new Comment(lineContent));
                                break;
                            case "A":
                            case "Z":
                            case "C":
                                PersonRole pr = new PersonRole(field.equals("C")
                                        ? Person.Role.COMPOSER
                                        : field.equals("A")
                                        ? Person.Role.AUTHOR
                                        : field.equals("Z")
                                        ? Person.Role.TRANSCRIBER
                                        : Person.Role.TRAD);
                                Person person = new Person();
                                if (lineContent.contains(",")) {
                                    person.setFirstName(lineContent.split(",")[1].trim());
                                    person.setLastName(lineContent.split(",")[0].trim());
                                } else {
                                    person.setFirstName(lineContent.split("\\s")[0].trim());
                                    person.setLastName(lineContent.split("\\s")[1].trim());
                                }
                                pr.setPerson(person);
                                if (!result.persons.contains(person)) {
                                    result.persons.add(person);
                                }
                                currentTune.addCreator(pr);
                                break;
                            case "Q":
                                Tempo tempo = new Tempo();
                                tempo.setUnit(Tempo.Unit.find(lineContent.split("=")[0].trim()));
                                tempo.setUnitsPerMinute(Integer.valueOf(lineContent.split("=")[1].trim()));
                                currentTune.setTempo(tempo);
                                break;
                            case "M":
                                Meter meter = new Meter();
                                switch (lineContent) {
                                    case "C":
                                        meter.setNumerator(4);
                                        meter.setDenominator(4);
                                        meter.setUseSymbol(Boolean.TRUE);
                                        break;
                                    case "C|":
                                        meter.setNumerator(2);
                                        meter.setDenominator(2);
                                        meter.setUseSymbol(Boolean.TRUE);
                                        break;
                                    default:
                                        meter.setNumerator(Integer.valueOf(lineContent.split("/")[0]));
                                        meter.setDenominator(Integer.valueOf(lineContent.split("/")[1]));
                                        meter.setUseSymbol(Boolean.FALSE);
                                        break;
                                }
                                currentTune.setMeter(meter);
                                break;
                            case "L":
                                currentTune.setTimeValue(Tune.TimeValue.find(lineContent));
                                break;
                            case "K":
                                currentTune.setKey(Key.valueOf(lineContent));
                                break;
                            case "V":
                                if (voiceBody != null && !voiceBody.isEmpty()) {
                                    currentVoice.setNotes(voiceBody);
                                    voiceBody = "";
                                }
                                currentVoice = new Voice();
                                currentTune.addVoice(currentVoice);
                                currentVoice.setVoiceId(String.valueOf(currentTune.getVoices().size()));
                                break;
                            case "w":
                                voiceBody += line + "\n";
                                break;
                            case "W":
                                break;

                        }

                    } else if (line.startsWith("%")) {
                    } else {
                        voiceBody += line + "\n";
                    }
                }
            });
            if (currentTune.getVoices().isEmpty()) {
                currentTune.addVoice(currentVoice);
                currentVoice.setVoiceId(String.valueOf(currentTune.getVoices().size()));
            }
            if (currentVoice.getNotes() == null || currentVoice.getNotes().isEmpty()) {
                currentVoice.setNotes(voiceBody);
                voiceBody = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(AbcFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private void findCharset() {
        charset = Charset.forName("ISO-8859-1");
        try {
            for (String line : Files.readAllLines(file.toPath())) {
                if (line != null && line.contains("encoding")) {
                    charset = Charset.forName(line.split("\\s+")[1].toUpperCase());
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AbcFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        File abcFile = new File("/home/hl/Skrivbord/Erborigien_649_-_650.abc");
        ProjectCarrier pc = new AbcFileReader(abcFile).read();
        List<Tune> tunes = pc.tunes;
        for (Person p : pc.persons) {
            System.out.println(p.getFormalName());
        }
        for (Tune t : tunes) {
            for (String title : t.getTitleFields()) {
                System.out.println(title);
            }
            for (PersonRole pr : t.getCreators()) {
                System.out.println(pr.get());
            }
            for (Origin o : t.getOrigin()) {
                System.out.println(o.get());
            }
            System.out.println(t.getMeter().get());
            System.out.println(t.getTempo().get());
            System.out.println(t.getTimeValueField());
            System.out.println(t.getKey().get());
            for (Voice v : t.getVoices()) {
                System.out.println("V: " + v.getVoiceId());
                System.out.println(v.getNotes());
            }
        }
    }

}
