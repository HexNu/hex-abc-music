package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Book;
import abc.music.core.domain.Tune;
import nu.hex.abc.music.service.io.Writer;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class BookWriter implements Writer<XmlNode> {

    private final Book book;
    private final XmlNode result;

    public BookWriter(Book book) {
        this.book = book;
        result = NodeFactory.createNode("book");
    }

    @Override
    public XmlNode write() {
        result.addAttribute("name", book.getName());
        XmlNode personsTextNode = NodeFactory.createNode("persons-text", book.getPersonsText());
        personsTextNode.addAttribute("print", book.getPrintPersons().toString());
        if (book.hasPreferredTemplate()) {
            result.addAttribute("preferred-template", book.getPreferredTemplate());
        }
        if (book.getPersonsHeader() != null && !book.getPersonsHeader().isEmpty()) {
            personsTextNode.addAttribute("header", book.getPersonsHeader());
        }
        result.addChild(personsTextNode);
        if (book.getShortDescription() != null) {
            result.addChild(NodeFactory.createNode("short-description", book.getShortDescription()));
        }
        if (book.getCopyright() != null && !book.getCopyright().isEmpty()) {
            result.addAttribute("copyright", book.getCopyright());
        }
        if (book.getPreface() != null) {
            XmlNode prefaceNode = NodeFactory.createNode("preface", book.getPreface());
            if (book.getPrefaceHeader() != null) {
                prefaceNode.addAttribute("header", book.getPrefaceHeader());
            }
            result.addChild(prefaceNode);
        }
        book.getTunes().stream().forEach(this::addTune);
        return result;
    }

    private void addTune(Tune tune) {
        XmlNode tuneNode = NodeFactory.createNode("tune");
        tuneNode.addAttribute("id", tune.getId());
        result.addChild(tuneNode);
    }
}
