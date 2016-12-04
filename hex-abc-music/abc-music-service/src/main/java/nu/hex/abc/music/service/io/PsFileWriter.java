package nu.hex.abc.music.service.io;

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
 * Created 2016-dec-04
 *
 * @author hl
 */
public class PsFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final File file;
    private File abcFile;

    public PsFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public PsFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
    }

    @Override
    public File write() {
        try {
            abcFile = new AbcFileWriter(tunes, new File(getAbcFile())).write();
            //TODO : Something about space in filenames must be handled
            String cmdString = "/usr/bin/abcm2ps " + abcFile.getAbsolutePath() + " -O " + file.getAbsolutePath();
            Logger.getLogger(SvgWriter.class.getName()).log(Level.INFO, "Running: {0}", cmdString);
            Process process = Runtime.getRuntime().exec(cmdString);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Logger.getLogger(SvgWriter.class.getName()).log(Level.WARNING, line);
                }
            }
            process.waitFor();
            return new File(file.getAbsolutePath());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(PsFileWriter.class.getName()).log(Level.SEVERE, null, ex);
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
