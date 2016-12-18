package nu.hex.abc.music.service.io;

import abc.music.core.domain.Book;
import abc.music.core.domain.Collection;
import abc.music.core.domain.FormatTemplate;
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
    private static final String TAB = "\t";
    private final PropertyService properties = new PropertyService();
    private final List<Tune> tunes;
    private final File file;
    private String abcDoc = "";
    private boolean isBook = false;
    private boolean isCollection = false;
    private FormatTemplate template;
    private Integer lineLength = 100;
    private String copyright = "";

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
        tunes = collection.getTunes();
        copyright = collection.getCopyright();
        this.file = file;
        template = collection.getProject().getFormatTemplate(collection.getPreferredTemplate());
        if (template.hasLineLength()) {
            lineLength = template.getLineLength();
        }
        isBook = collection instanceof Book;
        isCollection = true;
        addHeaderAndIntroduction(collection);
        if (!collection.hasPreface()) {
            setHeadersAndFooters();
        }
        if (collection.getPrintPersons()) {
            List<Person> persons = collection.getPersons();
            Collections.sort(persons, (a, b) -> a.getFormalName().compareTo(b.getFormalName()));
            setPageBreak();
            addPersonsInformation(collection.getPersonsHeader(), collection.getPersonsText(), persons);
        }
        if (collection.getPrintBooks()) {
            setPageBreak();
            addBooksInformation(collection.getBooksHeader(), collection.getBooksText(), collection.getBooks());
        }
    }

    @Override
    public File write() {
        if (isCollection && !isBook) {
            Collections.sort(tunes, (a, b) -> a.getName().compareTo(b.getName()));
        }
        if (isCollection) {
            abcDoc += new AbcDocWriter(tunes, lineLength).write();
        } else {
            abcDoc += new AbcDocWriter(tunes).write();
        }
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
        listDeclaredFonts();
        listAbcFonts();
        appendLine("%%writefields Q false");
        setMargins();
        setSpaces();
        if (template.hasIndent()) {
            appendLine(template.getIndentAsAbcString());
        }
        if (template.hasScale()) {
            appendLine(template.getScaleAsAbcString());
        }
        appendLine("% --- DOCUMENT START ---");
        setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_NAME));
        if (template.getFonts().containsKey(FormatTemplate.Font.COLLECTION_NAME)) {
            appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_NAME));
        }
        addFrontPage(collection);
        if (collection.hasTitles()) {
            addTitlePage(collection);
        }
        if (collection.hasPreface()) {
            addPreface(collection);
        }
    }

    private void addFrontPage(Collection collection) {
        appendCenteredLine(collection.getName());
    }

    private void addTitlePage(Collection collection) {
        setPageBreak();
        appendCenteredLine(" ");
        setPageBreak();
        setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_TITLE));
        for (int i = 0; i < collection.getTitles().size(); i++) {
            if (i > 0) {
                if (i == 1) {
                    setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_SUBTITLE));
                }
                appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_SUBTITLE));
            } else {
                appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_TITLE));
            }
            appendCenteredLine(collection.getTitles().get(i));
        }
    }

    private void addPreface(Collection collection) {
        setPageBreak();
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_HEADER));
        setHeadersAndFooters();
        setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_PREFACE_HEADER));
        appendSingleTextLine(collection.getPrefaceHeader());
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_TEXT));
        appendMultipleTextLines(collection.getPreface(), template.getSpaceValue(FormatTemplate.Space.COLLECTION_PREFACE_TEXT));
    }

    private void setHeadersAndFooters() {
        String header = "%%header \"";
        if (template.hasHeaderLeft()) {
            header = addField(header, template.getHeaderLeft());
        }
        header += TAB;
        if (template.hasHeaderCenter()) {
            header = addField(header, template.getHeaderCenter());
        }
        header += TAB;
        if (template.hasHeaderRight()) {
            header = addField(header, template.getHeaderRight());
        }
        header += "\"";
        if (!headerIsEmpty(header)) {
            appendLine(template.getFontAsAbcString(FormatTemplate.Font.PAGE_HEADER));
            appendLine(header);
        }
        String footer = "%%footer \"";
        if (template.hasFooterLeft()) {
            footer = addField(footer, template.getFooterLeft());
        }
        footer += TAB;
        if (template.hasFooterCenter()) {
            footer = addField(footer, template.getFooterCenter());
        }
        footer += TAB;
        if (template.hasFooterRight()) {
            footer = addField(footer, template.getFooterRight());
        }
        footer += "\"";
        if (!footerIsEmpty(footer)) {
            appendLine(template.getFontAsAbcString(FormatTemplate.Font.PAGE_FOOTER));
            appendLine(footer);
        }
    }

    private static boolean headerIsEmpty(String header) {
        return header.equals("%%header \"\t\t\"");
    }

    private static boolean footerIsEmpty(String footer) {
        return footer.equals("%%footer \"\t\t\"");
    }

    private String addField(String text, String input) {
        if (input == null || input.isEmpty()) {
            return text;
        }
        if (input.equals("©")) {
            if (copyright != null) {
                text += copyright;
            }
        } else {
            text += input;
        }
        return text;
    }

    private void setMargins() {
        template.getMarginsAsAbcStrings().stream().forEach(this::appendLine);
    }

    private void setSpaces() {
        template.getSpacesAsAbcStrings().stream().filter((s) -> (s != null && !s.isEmpty())).forEach(this::appendLine);
    }

    private void listDeclaredFonts() {
        template.getDeclaredFonts().stream().forEach((f) -> {
            appendLine("%%textfont " + f);
        });
    }

    private void listAbcFonts() {
        template.getDocumentFontsAsAbcStrings().stream().forEach(this::appendLine);
    }

    private void addPersonsInformation(String header, String text, List<Person> persons) {
        setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_PERSON_HEADER));
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_HEADER));
        appendSingleTextLine(header);
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_TEXT));
        appendMultipleTextLines(text, template.getSpaceValue(FormatTemplate.Space.COLLECTION_PERSON_TEXT));
        persons.stream().forEach(this::addPersonInfo);
    }

    private void addPersonInfo(Person p) {
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PERSON_HEADER));
        appendSingleTextLine(p.getFirstName() + " " + p.getLastName());
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PERSON_TEXT));
        appendMultipleTextLines(p.getHistory(), template.getSpaceValue(FormatTemplate.Space.COLLECTION_PERSON_TEXT));
    }

    private void addBooksInformation(String header, String text, List<Book> books) {
        setSkip(template.getSpaceValue(FormatTemplate.Space.COLLECTION_BOOK_HEADER));
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_HEADER));
        appendSingleTextLine(header);
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_PREFACE_TEXT));
        appendMultipleTextLines(text, template.getSpaceValue(FormatTemplate.Space.COLLECTION_BOOK_TEXT));
        books.stream().forEach(this::addBookInfo);
    }

    private void addBookInfo(Book b) {
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_BOOK_HEADER));
        appendSingleTextLine(b.getName());
        appendLine(template.getFontAsAbcString(FormatTemplate.Font.COLLECTION_BOOK_TEXT));
        if (!isBook) {
            List<String> titles = new ArrayList<>();
            b.getTunes().stream().forEach((t) -> {
                titles.add(t.getName());
            });
            appendMultipleTextLines(b.getShortDescription(), titles, template.getSpaceValue(FormatTemplate.Space.COLLECTION_BOOK_TEXT));
        } else {
            appendMultipleTextLines(b.getShortDescription(), template.getSpaceValue(FormatTemplate.Space.COLLECTION_BOOK_TEXT));
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
            for (String paragraph : text.split(NEW_LINE + NEW_LINE)) {
                appendLine("%%begintext");
                String[] para = new TextUtil(paragraph).createLines(lineLength).split(NEW_LINE);
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
            for (String paragraph : text.split(NEW_LINE + NEW_LINE)) {
                if (!isFirst) {
                    setSkip(verticalSkip);
                }
                isFirst = false;
                appendLine("%%begintext");
                String[] para = new TextUtil(paragraph).createLines(lineLength).split(NEW_LINE);
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

    private void setSkip(Double value) {
        appendLine("%%vskip " + value + "cm");
    }

    private void setPageBreak() {
        appendLine("%%newpage");
    }
}
