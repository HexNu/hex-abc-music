package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Voice;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
class VoiceReader extends NodeReader<Voice> {

    public VoiceReader(XmlNode node) {
        super(node);
    }

    @Override
    public Voice read() {
        Voice result = new Voice();
        result.setVoiceId(node.getAttribute("id"));
        if (node.hasAttribute("short-name")) {
            result.setShortName(node.getAttribute("short-name"));
        }
        if (node.hasAttribute("name")) {
            result.setName(node.getAttribute("name"));
        }
        if (node.hasAttribute("stem")) {
            result.setStem(Voice.Stem.find(node.getAttribute("stem")));
        }
        if (node.hasAttribute("use-voice-key")) {
            result.setUseVoiceKey(Boolean.valueOf(node.getAttribute("use-voice-key")));
        }
        if (node.hasAttribute("use-voice-modifier")) {
            result.setUseVoiceModifiers(Boolean.valueOf(node.getAttribute("use-voice-modifier")));
        }
        if (node.hasChildNamed("key")) {
            result.setKey(new KeyReader(node.getChild("key")).read());
        }
        if (node.hasChildNamed("modifier")) {
            result.setModifier(new ModifierReader(node.getChild("modifier")).read());
        }
        if (node.hasChildNamed("body")) {
            result.setNotes(node.getChild("body").getText());
        }
        return result;
    }

}
