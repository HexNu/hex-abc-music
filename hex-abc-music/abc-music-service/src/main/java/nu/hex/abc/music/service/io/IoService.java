package nu.hex.abc.music.service.io;

import abc.music.core.ProjectCarrier;
import abc.music.core.domain.Collection;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import java.io.File;
import java.util.List;
import nu.hex.abc.music.service.xml.read.SimpleProjectReader;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class IoService {

    public IoService() {
    }

    public static ProjectCarrier extractTunesAndPersons(File file) {
        XmlNode node = new AmxFileReader(file).read().getRoot();
        return new SimpleProjectReader(node).read();
    }

    public static XmlDocument readProjectFile(File file) {
        return new AmxFileReader(file).read();
    }

    public static File writeToFile(File file, String content) {
        return new SimpleFileWriter(file, content).write();
    }

    public File createXmlFile(File file, XmlDocument doc) {
        return new XmlFileWriter(doc, file).write();
    }

    public File createAmxFile(File file, XmlDocument doc) {
        new AmxFileWriter(doc.toString(), "project.xml", file).write();
        return file;
    }

    public XmlDocument createAmxfDocument(XmlNode projectNode) {
        return new AmxfWriter(projectNode).write();
    }

    public File createSvgFile(Tune tune) {
        return new SvgWriter(tune).write();
    }

    public File createAbcFile(Tune tune, File file) {
        return new AbcFileWriter(tune, file).write();
    }

    public File createAbcFile(List<Tune> tunes, File file) {
        return new AbcFileWriter(tunes, file).write();
    }

    public File createPsFile(Tune tune, File file) {
        return new PsFileWriter(tune, file).write();
    }

    public File createPsFile(List<Tune> tunes, File file) {
        return new PsFileWriter(tunes, file).write();
    }

    public File createPdfFile(Tune tune, File file) {
        return new PdfFileWriter(tune, file).write();
    }

    public File createPdfFile(List<Tune> tunes, File file) {
        return new PdfFileWriter(tunes, file).write();
    }

    public File exportCollectionAsAbc(Collection collection, File file) {
        return new AbcFileWriter(collection, file).write();
    }

    public File exportCollectionAsPs(Collection collection, File file) {
        return new PsFileWriter(collection, file).write();
    }

    public File exportCollectionAsPdf(Collection collection, File file) {
        return new PdfFileWriter(collection, file).write();
    }
    
    public void printFile(File file) {
        new PdfPrinter(file).print();
    }
}
