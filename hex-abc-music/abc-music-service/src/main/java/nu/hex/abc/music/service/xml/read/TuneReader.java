package nu.hex.abc.music.service.xml.read;

import abc.music.core.domain.Comment;
import abc.music.core.domain.Copyright;
import abc.music.core.domain.History;
import abc.music.core.domain.Origin;
import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Project;
import abc.music.core.domain.Tune;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class TuneReader extends NodeReader<Tune> {

    private final Project project;

    public TuneReader(Project project, XmlNode node) {
        super(node);
        this.project = project;
    }

    @Override
    public Tune read() {
        Tune result = new Tune(project);
        result.setId(Integer.valueOf(node.getAttribute("id")));
        if (node.hasChildNamed("titles") && node.getChild("titles").hasChildNamed("title")) {
            node.getChild("titles").getChildren("title").stream().forEach((n) -> {
                result.addTitle(n.getText());
            });
        }
        if (node.hasChildNamed("creators") && node.getChild("creators").getChildren() != null && !node.getChildren().isEmpty()) {
            if (!node.isCdata()) {
                PersonRole pr = new PersonRole(Person.Role.find(node.getName()));
                pr.setPerson(project.getPerson(Integer.valueOf(node.getAttribute("id"))));
                result.addCreator(pr);
            }
        }
        if (node.hasChildNamed("origins") && node.getChild("origins").hasChildNamed("origin")) {
            node.getChild("origins").getChildren("origin").stream().forEach((f) -> {
                if (f.getText() != null && !f.getText().isEmpty()) {
                    result.addOrigin(new Origin(f.getText()));
                }
            });
        }
        if (node.hasChildNamed("comments") && node.getChild("comments").hasChildNamed("comment")) {
            node.getChild("comments").getChildren("comment").stream().forEach((f) -> {
                if (f.getText() != null && !f.getText().isEmpty()) {
                    result.addComment(new Comment(f.getText()));
                }
            });
        }
        if (node.hasChildNamed("histories") && node.getChild("histories").hasChildNamed("history")) {
            node.getChild("histories").getChildren("history").stream().forEach((f) -> {
                if (f.getText() != null && !f.getText().isEmpty()) {
                    result.addHistory(new History(f.getText()));
                }
            });
        }
        if (node.hasChildNamed("copyrights") && node.getChild("copyrights").hasChildNamed("copyright")) {
            node.getChild("copyrights").getChildren("copyright").stream().forEach((f) -> {
                if (f.getText() != null && !f.getText().isEmpty()) {
                    result.addCopyright(new Copyright(f.getText()));
                }
            });
        }
        if (node.hasChildNamed("tempo")) {
            result.setTempo(new TempoReader(node.getChild("tempo")).read());
        }
        if (node.hasChildNamed("meter")) {
            result.setMeter(new MeterReader(node.getChild("meter")).read());
        }
        if (node.hasChildNamed("key")) {
            result.setKey(new KeyReader(node.getChild("key")).read());
        }
        if (node.hasChildNamed("voices") && node.getChild("voices").hasChildNamed("voice")) {
            node.getChild("voices").getChildren("voice").stream().forEach((v)-> {
                result.addVoice(new VoiceReader(v).read());
            });
        }
        return result;
    }

}
