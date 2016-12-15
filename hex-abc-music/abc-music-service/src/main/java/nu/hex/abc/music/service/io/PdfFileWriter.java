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
 * Created 2016-dec-04
 *
 * @author hl
 */
class PdfFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final File file;
    private final Collection collection;
    private File psFile;

    public PdfFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public PdfFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
        this.collection = null;
    }

    public PdfFileWriter(Collection collection, File file) {
        this.tunes = collection.getTunes();
        this.file = file;
        this.collection = collection;
    }

    @Override
    public File write() {
        try {
            if (collection == null) {
                psFile = new PsFileWriter(tunes, new File(getPsFile())).write();
            } else {
                psFile = new PsFileWriter(collection, new File(getPsFile())).write();
            }
            //TODO : Something about space in filenames must be handled
            String cmdString = "/usr/bin/ps2pdf " + psFile.getAbsolutePath() + " " + file.getAbsolutePath();
            System.out.println(cmdString);
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
            Logger.getLogger(PdfFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (psFile != null) {
                psFile.delete();
            }
        }
    }

    private String getPsFile() {
        return file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + ".ps";
    }

}
