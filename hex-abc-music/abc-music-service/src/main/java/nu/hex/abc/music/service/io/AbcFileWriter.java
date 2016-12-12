package nu.hex.abc.music.service.io;

import abc.music.core.domain.Book;
import abc.music.core.domain.Person;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nu.hex.abc.music.service.meta.MetaService;
import nu.hex.abc.music.service.properties.PropertyService;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
class AbcFileWriter implements Writer<File> {

    private static final String DEFAULT_CHARSET = "%%encoding utf-8";
    private static final String ABC_VERSION = "%%abc-version ";
    private static final String DEFAULT_ABC_VERSION = "2.1";
    private static final String ABC_CREATOR = "%%abc-creator hex-abc-music " + MetaService.getAppInfo().getVersion();
    private static final String NEW_LINE = "\n";
    private static final String COMMENT = "%";
    private final PropertyService properties = new PropertyService();

    private final List<Tune> tunes;
    private final File file;
    private String abcDoc = "";
    private String name;
    private String introduction;

    public AbcFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public AbcFileWriter(List<Tune> tunes, File file) {
        this.tunes = tunes;
        this.file = file;
    }

    public AbcFileWriter(Book book, File file) {
        this.tunes = book.getTunes();
        this.file = file;
        this.name = book.getName();
        this.introduction = book.getIntroduction();
        if (book.getPrintPersons()) {
            addPersonsInformation(book.getPersonsHeader(), book.getPersonsText(), book.getPersons());
        }
    }

    public AbcFileWriter(Project project, File file) {
        this.tunes = project.getTunes();
        this.file = file;
        this.name = project.getName();
        this.introduction = project.getIntroduction();
        if (project.getPrintPersons()) {
            List<Person> persons = project.getPersons();
            Collections.sort(persons, (a, b) -> a.getFormalName().compareTo(b.getFormalName()));
            addPersonsInformation(project.getPersonsHeader(), project.getPersonsText(), project.getPersons());
        }
        if (project.getPrintBooks()) {
            addBooksInformation(project.getBooksHeader(), project.getBooksText(), project.getBooks());
        }
    }

    @Override
    public File write() {
        createFileHeader();
        abcDoc += new AbcDocWriter(tunes).write();
        return new SimpleFileWriter(file, abcDoc).write();
    }

    private void createFileHeader() {
        String abcVersion = properties.getProperty("abc-version");
        if (abcVersion == null) {
            abcVersion = DEFAULT_ABC_VERSION;
        }
        abcDoc += ABC_VERSION
                + abcVersion
                + NEW_LINE
                + DEFAULT_CHARSET
                + NEW_LINE
                + ABC_CREATOR
                + NEW_LINE;
    }

    private void addPersonsInformation(String header, String text, List<Person> persons) {
    }

    private void addBooksInformation(String header, String text, List<Book> books) {
    }
}
