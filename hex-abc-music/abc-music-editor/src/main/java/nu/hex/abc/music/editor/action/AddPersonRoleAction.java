package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class AddPersonRoleAction extends AmeAction<PersonRole> {

    private final Person.Role role;

    public AddPersonRoleAction(AbcMusicEditor parent, Person.Role role) {
        super(parent);
        this.role = role;
    }

    @Override
    protected void performAction(ActionEvent event) {

    }

}
