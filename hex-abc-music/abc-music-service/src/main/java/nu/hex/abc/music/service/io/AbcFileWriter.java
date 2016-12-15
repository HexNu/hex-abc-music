package nu.hex.abc.music.service.io;

import abc.music.core.domain.Book;
import abc.music.core.domain.Collection;
import abc.music.core.domain.Person;
import abc.music.core.domain.Tune;
import abc.music.core.util.TextUtil;
import java.io.File;
import java.util.ArrayList;
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
    private static final String TIMES_ROMAN = "Times-Roman",
            TIMES_BOLD = "Times-Bold",
            TIMES_ITALIC = "Times-Italic";
    private final PropertyService properties = new PropertyService();
    private final List<Tune> tunes;
    private final File file;
    private String abcDoc = "";
    private boolean isBook = false;
    private boolean isCollection = false;

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
        isBook = collection instanceof Book;
        isCollection = true;
        addHeaderAndIntroduction(collection);
        if (collection.getPrintPersons()) {
            List<Person> persons = collection.getPersons();
            Collections.sort(persons, (a, b) -> a.getFormalName().compareTo(b.getFormalName()));
            setPageBreak();
            setSkip(2.7);
            addPersonsInformation(collection.getPersonsHeader(), collection.getPersonsText(), persons);
        }
        if (collection.getPrintBooks()) {
            setPageBreak();
            setSkip(2.7);
            addBooksInformation(collection.getBooksHeader(), collection.getBooksText(), collection.getBooks());
        }
    }

    @Override
    public File write() {
        if (isCollection && !isBook) {
            Collections.sort(tunes, (a,b) -> a.getName().compareTo(b.getName()));
        }
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

    private void addHeaderAndIntroduction(Collection collection) {
        appendLine("%%writefields Q false");
        appendLine("%%topmargin 2.7cm");
        appendLine("%%leftmargin 2.7cm");
        appendLine("%%rightmargin 2.7cm");
        appendLine("%%botmargin 2.7cm");
        appendLine("%%indent 0.7cm");
        setSkip(7.5);
        setFont(TIMES_BOLD, 30);
        appendCenteredLine(collection.getName());
        setPageBreak();
        if (!collection.getTitles().isEmpty()) {
            appendCenteredLine(" ");
            setPageBreak();
            setSkip(7.5);
            for (int i = 0; i < collection.getTitles().size(); i++) {
                if (i > 0) {
                    setFont(TIMES_ITALIC, 21);
                }
                appendCenteredLine(collection.getTitles().get(i));
            }
        }
        setPageBreak();
        setFont(TIMES_BOLD, 18);
        setSkip(2.7);
        appendSingleTextLine(collection.getPrefaceHeader());
        setFont(TIMES_ROMAN, 14);
        appendMultipleTextLines(collection.getPreface(), 0.8);
    }

    private void addPersonsInformation(String header, String text, List<Person> persons) {
        setFont(TIMES_BOLD, 18);
        appendSingleTextLine(header);
        setFont(TIMES_ITALIC, 14);
        appendMultipleTextLines(text, 0.8);
        for (Person p : persons) {
            setFont(TIMES_BOLD, 14);
            appendSingleTextLine(p.getFirstName() + " " + p.getLastName());
            setFont(TIMES_ROMAN, 14);
            appendMultipleTextLines(p.getHistory(), 0.8);
        }
    }

    private void addBooksInformation(String header, String text, List<Book> books) {
        setFont(TIMES_BOLD, 18);
        appendSingleTextLine(header);
        setFont(TIMES_ITALIC, 14);
        appendMultipleTextLines(text, 0.8);
        for (Book b : books) {
            setFont(TIMES_BOLD, 14);
            appendSingleTextLine(b.getName());
            setFont(TIMES_ROMAN, 14);
            if (!isBook) {
                List<String> titles = new ArrayList<>();
                for (Tune t : b.getTunes()) {
                    titles.add(t.getName());
                }
                appendMultipleTextLines(b.getShortDescription(), titles, 0.8);
            } else {
                appendMultipleTextLines(b.getShortDescription(), 0.8);
            }
        }
    }

    private void appendSingleTextLine(String text) {
        if (hasContent(text)) {
            appendLine("%%begintext");
            appendLine("%%" + text);
            appendLine("%%endtext");
        }
    }

    private void appendMultipleTextLines(String text, double verticalSkip) {
        if (hasContent(text)) {
            for (String paragraph : text.split("\n\n")) {
                appendLine("%%begintext");
                String[] para = new TextUtil(paragraph).createLines(100).split("\n");
                for (String line : para) {
                    appendLine("%%" + line);
                }
                appendLine("%%endtext");
                setSkip(verticalSkip);
            }
        }
    }

    private void appendMultipleTextLines(String text, List<String> list, double verticalSkip) {
        boolean isFirst = true;
        if (hasContent(text)) {
            for (String paragraph : text.split("\n\n")) {
                if (!isFirst) {
                    setSkip(verticalSkip);
                }
                isFirst = false;
                appendLine("%%begintext");
                String[] para = new TextUtil(paragraph).createLines(100).split("\n");
                for (String line : para) {
                    appendLine("%%" + line);
                }
                appendLine("%%endtext");
            }
        }
        appendLine("%%begintext");
        list.stream().forEach((l) -> {
            appendLine("%% - " + l);
        });
        appendLine("%%endtext");
        setSkip(verticalSkip);
    }

    private void appendCenteredLine(String text) {
        if (hasContent(text)) {
            appendLine("%%center " + text);
        }
    }

    private static boolean hasContent(String text) {
        return text != null && !text.isEmpty();
    }

    private void appendLine(String string) {
        abcDoc += string + NEW_LINE;
    }

    private void setFont(String fontName, int size) {
        appendLine("%%textfont " + fontName + " " + size);
    }

    private void setSkip(Double value) {
        appendLine("%%vskip " + value + "cm");
    }

    private void setPageBreak() {
        appendLine("%%newpage");
    }
}
