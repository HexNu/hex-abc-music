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
 * Created 2017-jun-28
 *
 * @author hl
 */
public class MusicXmlFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final Collection collection;
    private final File file;
    private File abcFile;

    public MusicXmlFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public MusicXmlFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
        this.collection = null;
    }

    public MusicXmlFileWriter(File file, Collection collection) {
        this.tunes = collection.getTunes();
        this.file = file;
        this.collection = collection;
    }

    @Override
    public File write() {
        try {
            // TODO: Each tune needs to be processed to a separate abc-file for 
            // further converting to MusicXML. The following solution will only 
            // convert one tune.
            if (collection == null) {
                abcFile = new AbcFileWriter(tunes, new File(getAbcFile())).write();
            } else {
                abcFile = new AbcFileWriter(collection, new File(getAbcFile())).write();
            }
            String cmdString = "/usr/bin/abcm2xml " + abcFile.getAbsolutePath() + " -O " + file.getAbsolutePath();
            Logger.getLogger(MusicXmlFileWriter.class.getName()).log(Level.INFO, "Running: {0}", cmdString);
            Process process = Runtime.getRuntime().exec(cmdString);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Logger.getLogger(MusicXmlFileWriter.class.getName()).log(Level.WARNING, line);
                }
            }
            return new File(file.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(MusicXmlFileWriter.class.getName()).log(Level.SEVERE, "Could not process data", e);
            return null;
        } finally {
            if (abcFile != null) {
                abcFile.delete();
            }
        }
    }

    private String getAbcFile() {
        return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + ".abc";
    }

}
