package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Key;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class KeyWriter extends XmlWriter<Key> {

    public KeyWriter(Key key) {
        super(key);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("pitch", entity.getPitch().getName());
        result.addAttribute("mode", entity.getMode().getName());
        result.addAttribute("signature", entity.getSignature().getSign());
        result.addChild(new ModifierWriter(entity.getModifier()).write());
        return result;
    }

}
