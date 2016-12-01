package nu.hex.abc.music.service;

import abc.music.core.domain.Project;
import nu.hex.abc.music.service.io.Writer;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class ProjectWriter implements Writer<XmlDocument> {

    private final Project project;

    public ProjectWriter(Project project) {
        this.project = project;
    }

    @Override
    public XmlDocument write() {
        XmlNode projectNode = NodeFactory.createNode("abc-project");
        XmlDocument result = new XmlDocument(projectNode);
        result.getHead().addAttribute("encoding", "UTF-8");
        return result;
    }

}
