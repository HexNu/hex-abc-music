package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Project;
import java.time.format.DateTimeFormatter;
import nu.hex.abc.music.service.xml.ClassNode;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class ProjectWriter extends XmlWriter<Project> {

    public ProjectWriter(Project project) {
        super(project);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("name", entity.getName());
        result.addAttribute("abc-version", entity.getAbcVersion());
        result.addAttribute("summary", entity.getSummary());
        if (entity.getOwner() != null) {
            XmlNode ownerNode = new ClassNode(Project.Owner.class).getNode();
            ownerNode.addAttribute("first-name", entity.getOwner().getFirstName());
            ownerNode.addAttribute("last-name", entity.getOwner().getLastName());
            ownerNode.addAttribute("email", entity.getOwner().getEmail());
            result.addChild(ownerNode);
        }
        result.addAttribute("last-updated", entity.getLastUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
        XmlNode tunesNode = NodeFactory.createNode("tunes");
        result.addChild(tunesNode);
        XmlNode personsNode = NodeFactory.createNode("persons");
        result.addChild(personsNode);
        entity.getPersons().stream().forEach((person) -> {
            personsNode.addChild(new PersonWriter(person).write());
        });
        entity.getTunes().stream().forEach((tune) -> {
            tunesNode.addChild(new TuneWriter(tune).write());
        });
        return result;
    }
}