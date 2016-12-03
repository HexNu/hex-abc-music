package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Modifier;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class ModifierWriter extends XmlWriter<Modifier> {

    public ModifierWriter(Modifier modifier) {
        super(modifier);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("transpose", entity.getTranspose());
        result.addAttribute("clef", entity.getClef().toString());
        result.addAttribute("octave", entity.getOctave().toString());
        return result;
    }

}
