package nu.hex.abc.music.service.xml.read;

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
        
        return result;
    }

}
