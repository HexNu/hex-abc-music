package abc.music.editor.action;

import abc.music.core.domain.Person;
import abc.music.core.domain.PersonRole;
import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import java.util.List;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.components.CreatePersonRolesDialog;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class AddPersonRoleAction extends AmeAction<List<PersonRole>> {

    private final Person.Role role;

    public AddPersonRoleAction(AbcMusicEditor parent, Person.Role role) {
        super(parent);
        this.role = role;
    }

    @Override
    protected void performAction(ActionEvent event) {
        CreatePersonRolesDialog dialog = new CreatePersonRolesDialog(editor, role);
        dialog.setVisible(true);
        setResult(dialog.get());
    }

}
