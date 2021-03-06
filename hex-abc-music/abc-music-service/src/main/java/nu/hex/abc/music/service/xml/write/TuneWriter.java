package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Comment;
import abc.music.core.domain.Copyright;
import abc.music.core.domain.History;
import abc.music.core.domain.Origin;
import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import nu.hex.abc.music.service.xml.ClassNode;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
class TuneWriter extends XmlWriter<Tune> {

    public TuneWriter(Tune tune) {
        super(tune);
    }

    @Override
    public XmlNode write() {
        result.addAttribute("id", entity.getId());
        result.addAttribute("rythm", entity.getRythm());
        result.addAttribute("time-value", entity.getTimeValue().toString());
        XmlNode titlesNode = NodeFactory.createNode("titles");
        result.addChild(titlesNode);
        if (entity.getTitles().isEmpty()) {
            titlesNode.addChild(NodeFactory.createNode("title", "No title"));
        } else {
            entity.getTitles().stream().forEach((title) -> {
                titlesNode.addChild(NodeFactory.createNode("title", title));
            });
        }
        XmlNode creatorsNode = NodeFactory.createNode("creators");
        result.addChild(creatorsNode);
        if (!entity.getCreators().isEmpty()) {
            entity.getCreators().forEach((creator) -> {
                if (creator != null && creator.getPerson() != null && creator.getRole() != null && creator.getPerson().getId() != null) {
                    XmlNode node = NodeFactory.createNode(creator.getRole().toString().toLowerCase().replaceAll(" ", "-"));
                    node.addAttribute("person", creator.getPerson().getId());
                    creatorsNode.addChild(node);
                }
            });
        }
        XmlNode originsNode = new ClassNode(Origin.class).getCollectionNode();
        result.addChild(originsNode);
        entity.getOrigin().stream().forEach((o) -> {
            addSimpleNode(originsNode, o);
        });
        XmlNode commentsNode = new ClassNode(Comment.class).getCollectionNode();
        result.addChild(commentsNode);
        entity.getComments().stream().forEach((c) -> {
            addSimpleNode(commentsNode, c);
        });
        XmlNode historyNode = new ClassNode(History.class).getCollectionNode();
        result.addChild(historyNode);
        entity.getHistory().stream().forEach((h) -> {
            addSimpleNode(historyNode, h);
        });
        XmlNode copyrightNode = new ClassNode(Copyright.class).getCollectionNode();
        result.addChild(copyrightNode);
        entity.getCopyright().stream().forEach((c) -> {
            addSimpleNode(copyrightNode, c);
        });
        result.addChild(new TempoWriter(entity.getTempo()).write());
        result.addChild(new MeterWriter(entity.getMeter()).write());
        result.addChild(new KeyWriter(entity.getKey()).write());
        result.addChild(new ModifierWriter(entity.getModifier()).write());
        result.addChild(new ClassNode(Voice.class).getCollectionNode());
        entity.getVoices().stream().forEach(this::addVoice);
        if (!entity.getLyrics().isEmpty()) {
            result.addChild(new LyricsWriter(entity.getLyrics()).write());
        }
        result.addChild(NodeFactory.createNode("score-layout", entity.getScoreLayout()));
        return result;
    }

    private void addVoice(Voice voice) {
        result.getChild("voices").addChild(new VoiceWriter(voice).write());
    }
}
