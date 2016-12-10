package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Lyrics;
import nu.hex.abc.music.service.io.Writer;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-09
 *
 * @author hl
 */
public class LyricsWriter implements Writer<XmlNode> {

    private final Lyrics lyrics;
    private Integer lineIndex;

    public LyricsWriter(Lyrics lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public XmlNode write() {
        XmlNode result = NodeFactory.createNode("lyrics");
        lyrics.getStrophes().keySet().stream().forEach((key) -> {
            lineIndex = 0;
            XmlNode stropheNode = NodeFactory.createNode("strophe");
            stropheNode.addAttribute("number", key);
            lyrics.getStrophe(key).stream().forEach((line) -> {
                XmlNode lineNode = NodeFactory.createNode("line", line);
                lineNode.addAttribute("number", lineIndex);
                stropheNode.addChild(lineNode);
                lineIndex++;
            });
            result.addChild(stropheNode);
        });
        return result;
    }
}
