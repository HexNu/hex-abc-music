package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Project;
import java.time.format.DateTimeFormatter;
import nu.hex.abc.music.service.xml.ClassNode;
import se.digitman.lightxml.NodeFactory;
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
        result.addAttribute("abc-version", entity.getAbcVersion());
        result.addAttribute("summary", entity.getSummary());
        XmlNode titlesNode = NodeFactory.createNode("titles");
        entity.getTitles().stream().forEach((title) -> {
            titlesNode.addChild(NodeFactory.createNode("title", title));
        });
        result.addChild(titlesNode);
        XmlNode personsTextNode = NodeFactory.createNode("persons-text", entity.getPersonsText());
        personsTextNode.addAttribute("print", entity.getPrintPersons().toString());
        if (entity.getPersonsHeader() != null && !entity.getPersonsHeader().isEmpty()) {
            personsTextNode.addAttribute("header", entity.getPersonsHeader());
        }
        result.addChild(personsTextNode);
        XmlNode booksTextNode = NodeFactory.createNode("books-text", entity.getBooksText());
        booksTextNode.addAttribute("print", entity.getPrintBooks().toString());
        if (entity.getBooksHeader() != null && !entity.getBooksHeader().isEmpty()) {
            booksTextNode.addAttribute("header", entity.getBooksHeader());
        }
        result.addChild(booksTextNode);
        if (entity.getPreface() != null && !entity.getPreface().isEmpty()) {
            XmlNode prefaceNode = NodeFactory.createNode("preface", entity.getPreface());
            if (entity.getPrefaceHeader() != null) {
                prefaceNode.addAttribute("header", entity.getPrefaceHeader());
            }
            result.addChild(prefaceNode);
        }
        if (entity.getOwner() != null) {
            XmlNode ownerNode = new ClassNode(Project.Owner.class).getNode();
            ownerNode.addAttribute("first-name", entity.getOwner().getFirstName());
            ownerNode.addAttribute("last-name", entity.getOwner().getLastName());
            ownerNode.addAttribute("email", entity.getOwner().getEmail());
            result.addChild(ownerNode);
        }
        result.addAttribute("last-updated", entity.getLastUpdated().format(DateTimeFormatter.ISO_DATE_TIME));
        XmlNode tunesNode = NodeFactory.createNode("tunes");
        result.addChild(tunesNode);
        XmlNode personsNode = NodeFactory.createNode("persons");
        result.addChild(personsNode);
        XmlNode booksNode = NodeFactory.createNode("books");
        result.addChild(booksNode);
        entity.getPersons().stream().forEach((person) -> {
            personsNode.addChild(new PersonWriter(person).write());
        });
        entity.getTunes().stream().forEach((tune) -> {
            tunesNode.addChild(new TuneWriter(tune).write());
        });
        entity.getBooks().stream().forEach((book) -> {
            booksNode.addChild(new BookWriter(book).write());
        });
        if (!entity.getFormatTemplates().isEmpty()) {
            XmlNode formatsNode = NodeFactory.createNode("formats");
            result.addChild(formatsNode);
            entity.getFormatTemplates().keySet().stream().forEach((key) -> {
                formatsNode.addChild(new FormatTemplateWriter(entity.getFormatTemplate(key)).write());
            });
        }
        return result;
    }
}
