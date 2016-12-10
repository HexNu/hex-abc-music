package abc.music.editor.action;

import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.gui.TuneHeadersPanel;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class RemovePersonRoleAction extends AmeAction<Void> {

    private final TuneHeadersPanel panel;
    private final PersonRole personRole;
    private final Tune tune;

    public RemovePersonRoleAction(TuneHeadersPanel panel, PersonRole personRole, Tune tune) {
        super(panel.getEditor());
        this.panel = panel;
        this.personRole = personRole;
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        if (personRole != null) {
            tune.removeCreator(personRole);
            panel.updateLists();
        }
    }

}
