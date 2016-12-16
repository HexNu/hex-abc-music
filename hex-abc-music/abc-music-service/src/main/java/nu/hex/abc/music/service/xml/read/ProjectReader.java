package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Book;
import abc.music.core.domain.FormatTemplate;
import abc.music.core.domain.Project;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import nu.hex.abc.music.service.io.Reader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class ProjectReader implements Reader<Project> {

    private final XmlNode node;
    private Project result;

    public ProjectReader(XmlNode node) {
        this.node = node;
    }

    @Override
    public Project read() {
        result = new Project(node.getAttribute("name"));
        if (node.hasAttribute("abc-version")) {
            result.setAbcVersion(node.getAttribute("abc-version"));
        }
        if (node.hasAttribute("preferred-template")) {
            result.setPreferredTemplate(node.getAttribute("preferred-template"));
        }
        if (node.hasAttribute("summary")) {
            result.setSummary(node.getAttribute("summary"));
        }
        if (node.hasChildNamed("titles") && node.getChild("titles").hasChildNamed("title")) {
            node.getChild("titles").getChildren("title").stream().forEach((node) -> {
                result.addTitle(node.getText());
            });
        }
        if (node.hasChildNamed("persons-text")) {
            if (node.getChild("persons-text").hasAttribute("print")) {
                result.setPrintPersons(Boolean.valueOf(node.getChild("persons-text").getAttribute("print")));
            }
            if (node.getChild("persons-text").hasAttribute("header")) {
                result.setPersonsHeader(node.getChild("persons-text").getAttribute("header"));
            }
            result.setPersonsText(node.getChild("persons-text").getText());
        }
        if (node.hasChildNamed("books-text")) {
            if (node.getChild("books-text").hasAttribute("print")) {
                result.setPrintBooks(Boolean.valueOf(node.getChild("books-text").getAttribute("print")));
            }
            if (node.getChild("books-text").hasAttribute("header")) {
                result.setBooksHeader(node.getChild("books-text").getAttribute("header"));
            }
            result.setBooksText(node.getChild("books-text").getText());
        }
        if (node.hasChildNamed("introduction")) {
            result.setPreface(node.getChild("introduction").getText());
        } else if (node.hasChildNamed("preface")) {
            if (node.getChild("preface").hasAttribute("header")) {
                result.setPrefaceHeader(node.getChild("preface").getAttribute("header"));
            }
            result.setPreface(node.getChild("preface").getText());
        }
        if (node.hasChildNamed("owner")) {
            if (result.getOwner() == null) {
                result.setOwner(new Project.Owner(result));
            }
            result.getOwner().setFirstName(node.getChild("owner").getAttribute("first-name"));
            result.getOwner().setLastName(node.getChild("owner").getAttribute("last-name"));
            result.getOwner().setEmail(node.getChild("owner").getAttribute("email"));
        }
        result.setLastUpdated(LocalDateTime.parse(node.getAttribute("last-updated"), DateTimeFormatter.ISO_DATE_TIME));
        if (node.hasChildNamed("persons") && node.getChild("persons").hasChildNamed("person")) {
            node.getChild("persons").getChildren("person").stream().forEach(this::addPerson);
        }
        if (node.hasChildNamed("tunes") && node.getChild("tunes").hasChildNamed("tune")) {
            node.getChild("tunes").getChildren("tune").stream().forEach(this::addTune);
        }
        if (node.hasChildNamed("books") && node.getChild("books").hasChildNamed("book")) {
            node.getChild("books").getChildren("book").stream().forEach(this::addBook);
        }
        if (node.hasChildNamed("formats") && node.getChild("formats").hasChildNamed("template")) {
            node.getChild("formats").getChildren("template").stream().forEach(this::addFormat);
        }
        return result;
    }

    private void addTune(XmlNode tuneNode) {
        result.addTune(new TuneReader(result, tuneNode).read());
    }

    private void addPerson(XmlNode personNode) {
        result.addPerson(new PersonReader(personNode).read());
    }

    private void addBook(XmlNode bookNode) {
        Book book = new BookReader(result, bookNode).read();
        result.addBook(book);
        if (book.getName() == null || book.getName().isEmpty()) {
            book.setName("No name");
        }
    }
    
    private void addFormat(XmlNode formatNode) {
        FormatTemplate template = new FormatTemplateReader(formatNode).read();
        result.addFormatTemplate(template);
        if (template.getName() == null || template.getName().isEmpty()) {
            template.setName("No name");
        }
    }
}
