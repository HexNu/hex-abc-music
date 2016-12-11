package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.HandleTuneInBooksDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class HandleTuneInBooksAction extends AmeAction<Void> {

    private final Tune tune;

    public HandleTuneInBooksAction(AbcMusicEditor editor, Tune tune) {
        super(editor);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        HandleTuneInBooksDialog dialog = new HandleTuneInBooksDialog(editor, tune);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            editor.updateMenuBar();
        }
    }

}
