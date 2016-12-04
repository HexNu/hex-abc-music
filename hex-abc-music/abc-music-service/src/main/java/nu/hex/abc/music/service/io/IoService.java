package nu.hex.abc.music.service.io;

import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import java.io.File;
import java.util.List;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class IoService {

    private final Project project;

    public IoService(Project project) {
        this.project = project;

    }

    public static File writeToFile(File file, String content) {
        return new SimpleFileWriter(file, content).write();
    }

    public File createXmlFile(File file, XmlDocument doc) {
        return new XmlFileWriter(doc, file).write();
    }

    public XmlDocument createAmxDocument(XmlNode projectNode) {
        return new AmxWriter(projectNode).write();
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

}
