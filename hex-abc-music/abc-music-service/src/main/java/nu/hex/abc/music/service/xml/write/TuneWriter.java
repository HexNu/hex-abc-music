package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class TuneWriter extends XmlWriter<Tune> {

    public TuneWriter(Tune tune) {
        super(tune);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("rythm", entity.getRythm());
        result.addAttribute("time-value", entity.getTimeValue().toString());
        entity.getTitles().stream().forEach((title) -> {
            result.addChild(NodeFactory.createNode("tilte", title));
        });
        entity.getCreators().forEach((creator) -> {
            XmlNode node = NodeFactory.createNode(creator.getRole().toString().replaceAll(" ", "-"));
            node.addAttribute("person", creator.getPerson().toString());
            result.addChild(node);
        });
        entity.getOrigin().stream().forEach(this::addSimpleNode);
        entity.getComments().stream().forEach(this::addSimpleNode);
        entity.getHistory().stream().forEach(this::addSimpleNode);
        entity.getCopyright().stream().forEach(this::addSimpleNode);
        result.addChild(new TempoWriter(entity.getTempo()).write());
        result.addChild(new MeterWriter(entity.getMeter()).write());
        result.addChild(new KeyWriter(entity.getKey()).write());
        entity.getVoices().stream().forEach(this::addVoice);
        result.addChild(NodeFactory.createNode("score-layout", entity.getScoreLayout()));
        return result;
    }

    private void addVoice(Voice voice) {
        result.addChild(new VoiceWriter(voice).write());
    }
}
