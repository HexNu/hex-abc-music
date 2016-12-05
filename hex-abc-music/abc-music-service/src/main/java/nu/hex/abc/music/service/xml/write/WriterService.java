package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Project;
import java.io.File;
import java.time.LocalDateTime;
import nu.hex.abc.music.service.Service;
import nu.hex.abc.music.service.properties.PropertyService;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class WriterService {

    private final Project project;

    public WriterService(Project project) {
        this.project = project;
    }

    public Project saveProject() {
        Service service = new Service(project);
        project.setLastUpdated(LocalDateTime.now());
        File file = Service.getProjectFile(project.getName());
        file.getParentFile().mkdirs();
        XmlNode projectNode = new ProjectWriter(project).write();
        XmlDocument projectDoc = service.getIoService().createAmxfDocument(projectNode);
        service.getIoService().createAmxFile(file, projectDoc);
        service.getPropertyService().setProperty(PropertyService.LATEST_SAVED_PROJECT, project.getName());
        return project;
    }

}
