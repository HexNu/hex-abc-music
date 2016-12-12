package nu.hex.abc.music.service.io;

import abc.music.core.domain.Book;
import abc.music.core.domain.Collection;
import abc.music.core.domain.Person;
import abc.music.core.domain.Tune;
import abc.music.core.util.TextUtil;
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

    public AbcFileWriter(Tune tune, File file) {
        this(Arrays.asList(tune), file);
    }

    public AbcFileWriter(List<Tune> tunes, File file) {
        createFileHeader();
        this.tunes = tunes;
        this.file = file;
    }

    public AbcFileWriter(Collection collection, File file) {
        createFileHeader();
        this.tunes = collection.getTunes();
        this.file = file;
        addHeaderAndIntroduction(collection.getName(), collection.getIntroduction());
        if (collection.getPrintPersons()) {
            List<Person> persons = collection.getPersons();
            Collections.sort(persons, (a, b) -> a.getFormalName().compareTo(b.getFormalName()));
            addPersonsInformation(collection.getPersonsHeader(), collection.getPersonsText(), persons);
        }
        if (collection.getPrintBooks()) {
            addBooksInformation(collection.getBooksHeader(), collection.getBooksText(), collection.getBooks());
        }
    }

    @Override
    public File write() {
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

    private void addHeaderAndIntroduction(String header, String information) {
        appendLine("%%topmargin 2.7cm");
        appendLine("%%leftmargin 2.7cm");
        appendLine("%%rightmargin 2.7cm");
        appendLine("%%botmargin 2.7cm");
        appendLine("%%vskip 11cm");
        appendLine("%%textfont Times-Bold 30");
        appendLine("%%center " + header);
        appendLine("%%newpage");
        appendLine("%%textfont Times-Bold 21");
        appendLine("%%vskip 2.7cm");
        appendLine("%%begintext");
        appendLine("%%" + header);
        appendLine("%%endtext");
        appendLine("%%textfont Times-Roman 14");
        for (String paragraph : information.split("\n\n")) {
            appendLine("%%begintext");
            String[] para = new TextUtil(paragraph).createLines(100).split("\n");
            for (String line : para) {
                appendLine("%%" + line);
            }
            appendLine("%%endtext");
            appendLine("%%vskip 0.8cm");
        }
    }

    private void addPersonsInformation(String header, String text, List<Person> persons) {
        appendLine("%%textfont Times-Bold 18");
        appendLine("%%begintext");
        appendLine("%%" + header);
        appendLine("%%endtext");
        for (String paragraph : text.split("\n\n")) {
            appendLine("%%textfont Times-Roman 14");
            appendLine("%%begintext");
            String[] para = new TextUtil(paragraph).createLines(100).split("\n");
            for (String line : para) {
                appendLine("%%" + line);
            }
            appendLine("%%endtext");
            appendLine("%%vskip 0.8cm");
        }
        for (Person p : persons) {
            appendLine("%%textfont Times-Bold 14");
            appendLine("%%begintext");
            appendLine("%%" + p.getFirstName() + " " + p.getLastName());
            appendLine("%%endtext");
            for (String paragraph : p.getHistory().split("\n\n")) {
                appendLine("%%textfont Times-Roman 14");
                appendLine("%%begintext");
                String[] para = new TextUtil(paragraph).createLines(100).split("\n");
                for (String line : para) {
                    appendLine("%%" + line);
                }
                appendLine("%%endtext");
                appendLine("%%vskip 0.8cm");
            }
        }
    }

    private void addBooksInformation(String header, String text, List<Book> books) {
        appendLine("%%vskip 1.8cm");
    }

    private void appendLine(String string) {
        abcDoc += string + NEW_LINE;
    }
}
