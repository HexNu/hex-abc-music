package nu.hex.abc.music.service;

import abc.music.core.domain.Book;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nu.hex.abc.music.service.exception.NoProjectException;
import nu.hex.abc.music.service.io.IoService;
import nu.hex.abc.music.service.meta.MetaService;
import nu.hex.abc.music.service.properties.PropertyService;
import nu.hex.abc.music.service.util.TuneHelper;
import nu.hex.abc.music.service.xml.read.ReaderService;
import nu.hex.abc.music.service.xml.write.WriterService;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class Service {

    public static final String[] SUFFIX = {"amx", "amxf"};
//    public static final String PROJECT_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.PROJECT_FOLDER);
//    public static final String BACKUP_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.BACKUP_FOLDER);
//    public static final String ABC_PATH = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.ABC_FOLDER);

    private final Project project;

    public Service(Project project) {
        if (project == null) {
            throw new NoProjectException();
        }
        this.project = project;
    }

    public static File getSourceDirectory() {
        return new File(PropertyService.PROJECT_PATH);
    }

    public static File getBackupDirectory() {
        return new File(PropertyService.BACKUP_PATH);
    }

    public static File getAppDirectory() {
        return new File(PropertyService.APP_PATH);
    }

    public static File getAbcDirectory() {
        return new File(PropertyService.ABC_PATH);
    }

    public static File getPsDirectory() {
        return new File(PropertyService.PS_PATH);
    }

    public static File getSvgDirectory() {
        return new File(PropertyService.SVG_PATH);
    }

//
//    public static Project openProject(File file) throws ServiceException {
//        try {
//            return new ProjectReader(new DocumentToXmlNodeParser(new FileInputStream(file)).parse()).read();
//        } catch (FileNotFoundException ex) {
//            throw new ProjectNotFoundException(file);
//        }
//    }
//
//    public static Project openProject(String name) {
//        return openProject(getProjectFile(name));
//    }
//
//    public Project saveProject() {
//        project.setLastUpdated(LocalDateTime.now());
//        File file = getProjectFile(project.getName());
//        file.getParentFile().mkdirs();
//        System.out.println(file.getAbsolutePath());
//        XmlNode projectNode = new ProjectWriter(project).write();
//        XmlDocument write = new AmxWriter(projectNode).write();
//        new XmlFileWriter(write, file).write();
//        AbcMusicProperties.getInstance().setProperty(AbcMusicProperties.LATEST_SAVED_PROJECT, project.getName());
//        return project;
//    }
//
    public List<Tune> searchTunes(String searchString) {
        List<Tune> result = new ArrayList<>();
        for (Tune tune : project.getTunes()) {
            for (String title : tune.getTitles()) {
                if (title.toLowerCase()
                        .startsWith(searchString.toLowerCase())
                        && !result.contains(tune)) {
                    result.add(tune);
                    break;
                }
            }
            if (!result.contains(tune)) {
                List<String> reducedTuneStrings = new TuneHelper(tune).getMusicNotesAsSearchString();
                String reducedSearchString = TuneHelper.musicNotesToSearchString(searchString);
                if (reducedSearchString != null && !reducedSearchString.isEmpty()) {
                    for (String reducedString : reducedTuneStrings) {
                        if (reducedString.startsWith(reducedSearchString)) {
                            result.add(tune);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public List<Book> findByTune(Tune tune) {
        List<Book> result = new ArrayList<>();
        project.getBooks().stream().filter((book) -> (book.hasTune(tune))).forEach((book) -> {
            result.add(book);
        });
        return result;
    }

    public ReaderService getReaderService() {
        return new ReaderService(project);
    }

    public WriterService getWriterService() {
        return new WriterService(project);
    }

    public IoService getIoService() {
        return new IoService(project);
    }

    public PropertyService getPropertyService() {
        return new PropertyService();
    }

    public MetaService getMetaService() {
        return new MetaService(project);
    }

    public static File getProjectFile(String name) {
        return new File(PropertyService.PROJECT_PATH + name + "." + SUFFIX[0]);
    }

    public static File getBackupFile(String name) {
        name = name + "-" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).replaceAll("\\D", "");
        return new File(PropertyService.BACKUP_PATH + name + "." + SUFFIX[0]);
    }
//    
//    public static File getCompressedProjectFile() {
//        return new File(PropertyService.PROJECT_PATH + "project.xml");
//    }

}
