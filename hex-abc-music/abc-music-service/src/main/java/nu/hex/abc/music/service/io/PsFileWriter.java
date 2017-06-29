package nu.hex.abc.music.service.io;

import abc.music.core.domain.Collection;
import abc.music.core.domain.Tune;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2016-dec-04
 *
 * @author hl
 */
class PsFileWriter implements Writer<File> {

    private final List<Tune> tunes;
    private final File file;
    private final Collection collection;
    private File abcFile;

    public PsFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public PsFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
        this.collection = null;
    }

    public PsFileWriter(Collection collection, File file) {
        this.tunes = collection.getTunes();
        this.file = file;
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
            //TODO : Something about space in filenames must be handled
            String cmdString = "/usr/bin/abcm2ps " + abcFile.getAbsolutePath() + " -O " + file.getAbsolutePath();
            Logger.getLogger(PsFileWriter.class.getName()).log(Level.INFO, "Running: {0}", cmdString);
            Process process = Runtime.getRuntime().exec(cmdString);
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Logger.getLogger(PsFileWriter.class.getName()).log(Level.WARNING, line);
                }
            }
            process.waitFor();
            List<String> lines = new ArrayList<>();
            List<String> readLines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
            for (String line : readLines) {
                if (line.startsWith("%%Title:")) {
                    line = "%%Title: " + line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf(".abc")).replaceAll("_", " ");
                }
                lines.add(line);
            }
            Files.write(file.toPath(), lines, Charset.forName("UTF-8"));
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
