package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Modifier;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
class ModifierReader extends NodeReader<Modifier> {

    public ModifierReader(XmlNode node) {
        super(node);
    }

    @Override
    public Modifier read() {
        Modifier result = new Modifier();
        if (node.hasAttribute("transpose")) {
            result.setTranspose(Integer.valueOf(node.getAttribute("transpose")));
        }
        if (node.hasAttribute("clef")) {
            result.setClef(Modifier.Clef.find(node.getAttribute("clef")));
        }
        if (node.hasAttribute("octave")) {
            result.setOctave(Modifier.OctaveClef.find(node.getAttribute("octave")));
        }
        return result;
    }

}
