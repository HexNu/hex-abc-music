package nu.hex.abc.music.service.io;

import java.io.File;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.format.XmlPrettyPrinter;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class XmlFileWriter implements Writer<File> {

    private final XmlDocument doc;
    private final File file;

    public XmlFileWriter(XmlDocument doc, File file) {
        this.doc = doc;
        this.file = file;
    }

    public XmlFileWriter(XmlDocument doc, String path) {
        this(doc, new File(path));
    }

    @Override
    public File write() {
        String docString = new XmlPrettyPrinter(doc).process();
        return new SimpleFileWriter(file, docString).write();
    }
}
