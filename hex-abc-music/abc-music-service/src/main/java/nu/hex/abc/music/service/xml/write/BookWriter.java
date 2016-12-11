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
        if (book.getIngress() != null) {
            result.addChild(NodeFactory.createNode("ingress", book.getIngress()));
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
