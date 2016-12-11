package nu.hex.abc.music.service.xml.read;

import abc.music.core.ProjectCarrier;
import abc.music.core.domain.Project;
import nu.hex.abc.music.service.io.Reader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class SimpleProjectReader implements Reader<ProjectCarrier> {

    private final XmlNode node;
    private Project project;

    public SimpleProjectReader(XmlNode node) {
        this.node = node;
    }

    @Override
    public ProjectCarrier read() {
        project = new ProjectReader(node).read();
        ProjectCarrier result = new ProjectCarrier();
        result.persons = project.getPersons();
        result.tunes = project.getTunes();
        return result;
    }

}
