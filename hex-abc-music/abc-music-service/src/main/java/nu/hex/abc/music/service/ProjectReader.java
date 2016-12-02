package nu.hex.abc.music.service;

import abc.music.core.domain.Project;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import nu.hex.abc.music.service.io.Reader;
import nu.hex.abc.music.service.xml.read.PersonReader;
import nu.hex.abc.music.service.xml.read.TuneReader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class ProjectReader implements Reader<Project> {

    private final XmlNode node;
    private Project result;

    public ProjectReader(XmlNode node) {
        this.node = node;
    }

    @Override
    public Project read() {
        result = new Project(node.getAttribute("name"));
        result.setLastUpdated(LocalDateTime.parse(node.getAttribute("last-updated"), DateTimeFormatter.ISO_DATE_TIME));
        if (node.hasChildNamed("tune")) {
            node.getChildren("tune").stream().forEach(this::addTune);
        }
        if (node.hasChildNamed("person")) {
            node.getChildren("person").stream().forEach(this::addPerson);
        }
        return result;
    }

    private void addTune(XmlNode tuneNode) {
        result.addTune(new TuneReader(result, tuneNode).read());
    }

    private void addPerson(XmlNode personNode) {
        result.addPerson(new PersonReader(personNode).read());
    }
}
