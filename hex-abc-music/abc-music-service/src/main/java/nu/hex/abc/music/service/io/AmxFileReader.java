package nu.hex.abc.music.service.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
class AmxFileReader implements Reader<XmlDocument> {

    private final File inputFile;

    public AmxFileReader(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public XmlDocument read() {
        try {
            try (ZipFile zipFile = new ZipFile(inputFile)) {
                Enumeration<?> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    String name = zipEntry.getName();
                    File file = new File(name);
                    if (name.endsWith("/")) {
                        file.mkdirs();
                        continue;
                    }
                    File parent = file.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }
                    FileOutputStream fos;
                    try (InputStream is = zipFile.getInputStream(zipEntry)) {
                        fos = new FileOutputStream(file);
                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = is.read(bytes)) >= 0) {
                            fos.write(bytes, 0, length);
                        }
                    }
                    fos.close();
                    if (file.getName().endsWith(".xml")) {
                        XmlNode data = new DocumentToXmlNodeParser(new FileInputStream(file)).parse();
                        XmlDocument result = new XmlDocument(data);
                        result.getHead().addAttribute("encoding", "UTF-8");
                        file.delete();
                        return result;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AmxFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        File file = new File("/home/hl/Skrivbord/Egna låtar.amx");
        System.out.println(new AmxFileReader(file).read());
    }
}
