package nu.hex.abc.music.service.io;

import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class AmxWriter implements Writer<XmlDocument> {

    private final XmlNode node;

    public AmxWriter(XmlNode node) {
        this.node = node;
    }

    @Override
    public XmlDocument write() {
        XmlDocument doc = new XmlDocument(node);
        doc.getHead().addAttribute("encoding", "UTF-8");
        return doc;
    }

}
