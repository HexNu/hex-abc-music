package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Book;
import abc.music.core.domain.Project;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class BookReader extends NodeReader<Book> {

    private final Project project;
    private final Book result;

    public BookReader(Project project, XmlNode node) {
        super(node);
        this.project = project;
        result = new Book();
    }

    @Override
    public Book read() {
        if (node.hasAttribute("name")) {
            result.setName(node.getAttribute("name"));
        }
        if (node.hasAttribute("print-creators")) {
            result.setPrintCreators(Boolean.valueOf(node.getAttribute("print-creators")));
        } else {
            result.setPrintCreators(Boolean.FALSE);
        }
        if (node.hasChildNamed("short-description")) {
            result.setShortDescription(node.getChild("short-description").getText());
        }
        if (node.hasChildNamed("introduction")) {
            result.setIntroduction(node.getChild("introduction").getText());
        }
        node.getChildren("tune").stream().forEach(this::addTune);
        return result;
    }

    private void addTune(XmlNode tuneNode) throws NumberFormatException {
        result.addTune(project.getTune(Integer.valueOf(tuneNode.getAttribute("id"))));
    }
}
