package nu.hex.abc.music.service;

import nu.hex.abc.music.service.meta.AppInfo;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        
        System.out.println(AppInfo.getInstance().getName());
        System.out.println(AppInfo.getInstance().getVersion());
        System.out.println(AppInfo.getInstance().getVendor());
        System.out.println(AppInfo.getInstance().getLastBuild());
        /*
        Person person = new Person();
        person.setFirstName("Håkan");
        person.setLastName("Lidén");
        Person jon = new Person();
        jon.setFirstName("Jon");
        jon.setLastName("Pipare");

        PersonRole composer = new PersonRole(Person.Role.COMPOSER);
        composer.setPerson(person);
        PersonRole author = new PersonRole(Person.Role.AUTHOR);
        author.setPerson(person);
        PersonRole transcriber = new PersonRole(Person.Role.TRANSCRIBER);
        transcriber.setPerson(person);
        PersonRole trad = new PersonRole(Person.Role.TRAD);
        trad.setPerson(jon);

        Tune tune = new Tune(1);
        tune.addTitle("Flokadal");
        tune.addCreator(trad);
        tune.addCreator(composer);
        tune.addCreator(author);
        tune.addCreator(transcriber);
        tune.setRythm("Dalska");
        tune.setCopyright("Håkan Lidén 2016");

        Origin origin = new Origin();
        origin.setFieldContent("Flokadal");
        tune.addOrigin(origin);

        Comment comment = new Comment();
        comment.setFieldContent("Polska");
        tune.addComment(comment);

        History history1 = new History();
        history1.setFieldContent("Brate lärde sig denna dalska av Jon då han besökte Borgevid vintern 649-650");
        tune.addHistory(history1);
        History history2 = new History();
        history2.setFieldContent("Jon sa sig i sin tur har lärt den av Mor Hildegun som brukade tralla polskan till dans sent på natten");
        tune.addHistory(history2);
        
        Tempo tempo = new Tempo();
        tempo.setLabel("Moderato");
        tempo.setUnit("1/4");
        tempo.setUnitsPerMinute(96);
        tune.setTempo(tempo);

        Meter meter = new Meter();
        meter.setFieldContent("3/4");
        tune.setMeter(meter);

        tune.setUnitNoteLength(Tune.Unit.EIGHTH);

        Key key = new Key();
        key.setPitch(Key.Pitch.D);
        key.setSignature(Key.Signature.NATURAL);
        key.setMode(Key.Mode.DORIAN);
        tune.setKey(key);

        Voice voice1 = new Voice(tune, "flp");
        voice1.setName("Piccolo Flöjt");
        voice1.setShortName("P. Fl.");
        Key key1 = new Key();
        Modifier modifier1 = new Modifier();
        modifier1.setTranspose(12);
        key1.setPitch(Key.Pitch.D);
        key1.setMode(Key.Mode.DORIAN);
        key1.setModifier(modifier1);
        voice1.setKey(key1);
        voice1.setNotes("DA | G/F/E/D/ Ad f2 | f/f/ e>d d/^c/e/c/ | \n"
                + "A3/2 a ff | ed/^c/ d2 |] ");
        Voice voice2 = new Voice(tune, "vl");
        voice2.setName("Fiol");
        voice2.setShortName("Vl.");
        voice2.setNotes("DA | G/F/E/D/ Ad f2 | f/f/ e>d d/^c/e/c/ | \n"
                + "A3/2 a ff | ed/^c/ d2 |] ");
        
        Voice voice3 = new Voice(tune, "clp");
        voice3.setName("Piccolo Klarinett");
        voice3.setShortName("P. Kl.");
        Key key3 = new Key();
        Modifier modifier3 = new Modifier();
        modifier3.setClef(Modifier.Clef.DEFAULT_CLEF);
        modifier3.setOctave(Modifier.Octave.UP);
        modifier3.setTranspose(-2);
        key3.setPitch(Key.Pitch.E);
        key3.setSignature(Key.Signature.NATURAL);
        key3.setMode(Key.Mode.DORIAN);
        key3.setModifier(modifier3);
        voice3.setKey(key3);
        voice3.setNotes("EB | A/G/F/E/ Bc g2 | g/g/ F>e e/^d/f/d/ | \n"
                + "B3/2 b gg | fe/^d/ e2 |] ");
        tune.addVoice(voice1);
        tune.addVoice(voice3);
        tune.addVoice(voice2);
        Writer writer = new AbcWriter(tune);
        System.out.println(writer.write());
        
        File resultFile = new File("/home/hl/Skrivbord/result.abc");
        new AbcFileWriter(tune, resultFile).write();

*/

//        List<Tune> tunes = new ArrayList<>();
//        tunes.add(tune);
//        tune.setId(2);
//        tunes.add(tune);
//        String result = new AbcWriter(tunes).write();
//        System.out.println("***");
//        System.out.println(result);
//        System.out.println("***");
    }
}
