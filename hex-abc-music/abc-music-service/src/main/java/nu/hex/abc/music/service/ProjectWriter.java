package nu.hex.abc.music.service;

import abc.music.core.domain.Project;
import java.time.format.DateTimeFormatter;
import nu.hex.abc.music.service.xml.write.PersonWriter;
import nu.hex.abc.music.service.xml.write.TuneWriter;
import nu.hex.abc.music.service.xml.write.XmlWriter;
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
        result.addAttribute("last-updated", entity.getLastUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
        entity.getTunes().stream().forEach((tune) -> {
            result.addChild(new TuneWriter(tune).write());
        });
        entity.getPersons().stream().forEach((person) -> {
            result.addChild(new PersonWriter(person).write());
        });
        return result;
    }

}
