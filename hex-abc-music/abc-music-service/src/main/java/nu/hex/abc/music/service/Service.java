package nu.hex.abc.music.service;

import abc.music.core.domain.Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import nu.hex.abc.music.service.exception.NoProjectException;
import nu.hex.abc.music.service.exception.ProjectNotFoundException;
import nu.hex.abc.music.service.exception.ServiceException;
import nu.hex.abc.music.service.io.AmxWriter;
import nu.hex.abc.music.service.io.XmlFileWriter;
import nu.hex.abc.music.service.properties.AbcMusicProperties;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class Service {

    public static final String SUFFIX = "amx";
    private static final String PROJECT_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.PROJECT_FOLDER);
    private static final String BACKUP_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.BACKUP_FOLDER);
    private static final String ABC_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.ABC_FOLDER);

    private final Project project;

    public Service(Project project) {
        if (project == null) {
            throw new NoProjectException();
        }
        this.project = project;
    }

    public static File getSourceDirectory() {
        return new File(PROJECT_PATH);
    }

    public static File getBackupDirectory() {
        return new File(BACKUP_PATH);
    }

    public static File getAbcDirectory() {
        return new File(ABC_PATH);
    }

    public static Project openProject(File file) throws ServiceException {
        try {
            return new ProjectReader(new DocumentToXmlNodeParser(new FileInputStream(file)).parse()).read();
        } catch (FileNotFoundException ex) {
            throw new ProjectNotFoundException(file);
        }
    }

    public static Project openProject(String name) {
        return openProject(getProjectFile(name));
    }

    public Project saveProject() {
        project.setLastUpdated(LocalDateTime.now());
        File file = getProjectFile(project.getName());
        file.getParentFile().mkdirs();
        System.out.println(file.getAbsolutePath());
        XmlNode projectNode = new ProjectWriter(project).write();
        XmlDocument write = new AmxWriter(projectNode).write();
        new XmlFileWriter(write, file).write();
        AbcMusicProperties.getInstance().setProperty(AbcMusicProperties.LATEST_SAVED_PROJECT, project.getName());
        return project;
    }

    private static File getProjectFile(String name) {
        return new File(PROJECT_PATH + name + "." + SUFFIX);
    }

}
