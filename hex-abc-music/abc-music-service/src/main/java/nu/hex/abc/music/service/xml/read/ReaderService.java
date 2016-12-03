package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import nu.hex.abc.music.service.Service;
import nu.hex.abc.music.service.exception.ProjectNotFoundException;
import nu.hex.abc.music.service.exception.ServiceException;
import se.digitman.lightxml.DocumentToXmlNodeParser;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class ReaderService {

    private final Project project;

    public ReaderService(Project project) {
        this.project = project;
    }

    public static Project openProject(File file) throws ServiceException {
        try {
            return new ProjectReader(new DocumentToXmlNodeParser(new FileInputStream(file)).parse()).read();
        } catch (FileNotFoundException ex) {
            throw new ProjectNotFoundException(file);
        }
    }

    public static Project openProject(String name) {
        return openProject(Service.getProjectFile(name));
    }
}
