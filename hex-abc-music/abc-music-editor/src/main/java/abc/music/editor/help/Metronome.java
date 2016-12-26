package abc.music.editor.help;

import abc.music.core.domain.Meter;
import abc.music.core.domain.Tempo;
import abc.music.editor.AbcMusicEditor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-dec-26
 *
 * @author hl
 */
public class Metronome {

    private final AbcMusicEditor editor;
    private final Meter meter;
    private final Tempo tempo;

    public Metronome(AbcMusicEditor editor, Meter meter, Tempo tempo) {
        this.editor = editor;
        this.meter = meter;
        this.tempo = tempo;
    }

    public File generateFile() {
        try {
            String abc = "%%encoding utf-8\n\n";
            abc += "X: 1\n";
            abc += "T: Metronome\n";
            abc += meter.get() + "\n";
            abc += "L: 1/" + meter.getDenominator() + "\n";
            abc += tempo.get() + "\n";
            abc += "K: D\n";
            abc += "%%MIDI program 1 47 \n";
            for (int b = 0; b < 16; b++) {
                for (int i = 0; i < meter.getNumerator(); i++) {
                    abc += "[Dd] ";
                }
                abc += " | ";
            }
            File abcFile = new File(Service.getMidiDirectory().getAbsolutePath() + "/temp-metronome.abc");
            Files.write(abcFile.toPath(), Arrays.asList(abc.split("\n")));
            return new Service(editor.getProject()).getIoService().createMidiFile(abcFile);
        } catch (IOException ex) {
            Logger.getLogger(Metronome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
