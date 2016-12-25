package nu.hex.abc.music.service.io;

import abc.music.core.domain.Collection;
import abc.music.core.domain.Tune;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2016-dec-22
 *
 * @author hl
 */
class MidiFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final Collection collection;
    private final File file;
    private File abcFile;

    public MidiFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public MidiFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
        this.collection = null;
    }

    public MidiFileWriter(Collection collection, File file) {
        this.file = file;
        this.tunes = collection.getTunes();
        this.collection = collection;
    }

    @Override
    public File write() {
        try {
            if (collection == null) {
                abcFile = new AbcFileWriter(tunes, new File(getAbcFile())).write();
            } else {
                abcFile = new AbcFileWriter(collection, new File(getAbcFile())).write();
            }
            String cmdString = "/usr/bin/abc2midi " + abcFile.getAbsolutePath();
            Logger.getLogger(SvgWriter.class.getName()).log(Level.INFO, "Running: {0}", cmdString);
            Process process = Runtime.getRuntime().exec(cmdString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Logger.getLogger(SvgWriter.class.getName()).log(Level.WARNING, line);
                }
            }
            process.waitFor();
            return new File(getMidiFile());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MidiFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (abcFile != null) {
                abcFile.delete();
            }
        }
        return null;
    }

    private String getAbcFile() {
        return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + ".abc";
    }

    private String getMidiFile() {
        if (!tunes.isEmpty() && tunes.size() == 1) {
            return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + tunes.get(0).getId() + ".mid";
        }
        return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + "1.mid";
    }
}
