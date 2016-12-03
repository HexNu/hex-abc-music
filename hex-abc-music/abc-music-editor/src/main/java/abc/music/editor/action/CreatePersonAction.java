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
public class CreatePersonAction extends AmeAction<Person> {

    public CreatePersonAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        EditPersonDialog dialog = new EditPersonDialog(editor);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
//            parent.getProject().addPerson(dialog.get());
            editor.updateMenuBar();
        }
    }
}
