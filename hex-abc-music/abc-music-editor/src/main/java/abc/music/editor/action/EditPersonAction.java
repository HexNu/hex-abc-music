package abc.music.editor.action;

import abc.music.core.domain.Person;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.components.AmeDialog;
import abc.music.editor.components.EditPersonDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class EditPersonAction extends AmeAction<Person> {

    private final Person person;

    public EditPersonAction(AbcMusicEditor parent, Person person) {
        super(parent);
        this.person = person;
    }

    @Override
    protected void performAction(ActionEvent event) {
        EditPersonDialog dialog = new EditPersonDialog(editor, person);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            editor.updateMenuBar();
        }
    }
}
