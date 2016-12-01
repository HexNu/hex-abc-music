package nu.hex.abc.music.editor.action;

import abc.music.core.domain.PersonRole;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class RemovePersonRoleAction extends AmeAction<Void> {

    private final PersonRole personRole;

    public RemovePersonRoleAction(AbcMusicEditor parent, PersonRole personRole) {
        super(parent);
        this.personRole = personRole;
    }

    @Override
    protected void performAction(ActionEvent event) {
        if (personRole != null) {
            System.out.println("Remove " + personRole.getPerson().toString());
        }
    }

}
