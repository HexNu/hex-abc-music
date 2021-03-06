package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Voice;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class VoiceWriter extends XmlWriter<Voice> {

    public VoiceWriter(Voice voice) {
        super(voice);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("id", entity.getVoiceId());
        result.addAttribute("short-name", entity.getShortName());
        result.addAttribute("name", entity.getName());
        result.addAttribute("use-voice-key", entity.getUseVoiceKey().toString());
        result.addAttribute("use-voice-modifier", entity.getUseVoiceModifiers().toString());
        result.addAttribute("stem", entity.getStem().getName());
        result.addChild(new KeyWriter(entity.getKey()).write());
        result.addChild(new ModifierWriter(entity.getModifier()).write());
        result.addChild(NodeFactory.createNode("body", entity.getNotes()));
        if (entity.getMidiChannel() != null) {
            XmlNode midiChannelNode = NodeFactory.createNode("midi-channel");
            midiChannelNode.addAttribute("index", entity.getMidiChannel().getIndex());
            midiChannelNode.addAttribute("program", entity.getMidiChannel().getChannel().getProgram());
            result.addChild(midiChannelNode);
        }
        return result;
    }
}
