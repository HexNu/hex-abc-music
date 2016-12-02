package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Person;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.AmeDialog;
import nu.hex.abc.music.editor.components.EditPersonDialog;

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
        EditPersonDialog dialog = new EditPersonDialog(parent);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
//            parent.getProject().addPerson(dialog.get());
            parent.updateMenuBar();
        }
    }
}
