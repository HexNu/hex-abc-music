package nu.hex.abc.music.service.xml.read;

import nu.hex.abc.music.service.io.Reader;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 * @param <T>
 */
public abstract class NodeReader<T> implements Reader<T> {

    protected XmlNode node;

    public NodeReader(XmlNode node) {
        this.node = node;
    }
}
