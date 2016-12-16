package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Key;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
class KeyReader extends NodeReader<Key> {

    public KeyReader(XmlNode node) {
        super(node);
    }

    @Override
    public Key read() {
        Key result = new Key();
        if (node.hasAttribute("pitch")) {
            result.setPitch(Key.Pitch.find(node.getAttribute("pitch")));
        }
        if (node.hasAttribute("mode")) {
            result.setMode(Key.Mode.find(node.getAttribute("mode")));
        }
        if (node.hasAttribute("signature")) {
            result.setSignature(Key.Signature.find(node.getAttribute("signature")));
        }
        return result;
    }

}
