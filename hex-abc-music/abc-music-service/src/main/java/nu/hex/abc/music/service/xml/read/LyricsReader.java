package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Lyrics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nu.hex.abc.music.service.io.Reader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-09
 *
 * @author hl
 */
public class LyricsReader implements Reader<Lyrics> {

    private final XmlNode node;

    public LyricsReader(XmlNode node) {
        this.node = node;
    }

    @Override
    public Lyrics read() {
        Lyrics lyrics = new Lyrics();
        node.getChildren("strophe").stream().forEach((stropheNode) -> {
            Collections.sort(node.getChildren("line"), (a, b)
                    -> Integer.valueOf(a.getAttribute("number")).compareTo(Integer.valueOf(b.getAttribute("number"))));
            List<String> strophe = new ArrayList<>();
            stropheNode.getChildren("line").stream().forEach((lineNode) -> {
                strophe.add(lineNode.getText());
            });
            lyrics.addVerse(Integer.valueOf(stropheNode.getAttribute("number")), strophe);
        });
        return lyrics;
    }
}
