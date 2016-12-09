package nu.hex.abc.music.service.io;

import abc.music.core.exception.AbcException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2016-nov-17
 *
 * @author hl
 */
class SimpleFileWriter implements Writer<File> {

    private final String doc;
    private final File file;
    private final Charset charset;

    public SimpleFileWriter(File file, String doc) {
        this(file, doc, Charset.forName("UTF-8"));
    }

    public SimpleFileWriter(File file, String doc, Charset charset) {
        this.doc = doc;
        this.file = file;
        this.charset = charset;
    }

    @Override
    public File write() {
        try {
            Files.write(file.toPath(), doc.getBytes(charset));
            Logger.getLogger(SimpleFileWriter.class.getName()).log(Level.INFO, "Written to file: {0}", file.getAbsolutePath());
            return file;
        } catch (IOException ex) {
            Logger.getLogger(SimpleFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            throw new AbcException("Could not write to file: " + file.getAbsolutePath(), ex);
        }
    }
}
