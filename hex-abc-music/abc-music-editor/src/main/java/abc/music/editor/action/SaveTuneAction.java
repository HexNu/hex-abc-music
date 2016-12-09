package abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.TuneHeadersPanel;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.AmeTextDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class SaveTuneAction extends AmeAction<Tune> {

    public SaveTuneAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        TuneHeadersPanel tuneEditor = editor.getTuneEditor();
        if (tuneEditor.getTune().getName() == null || tuneEditor.getTune().getName().isEmpty()) {
            AmeTextDialog dialog = new AmeTextDialog(editor, "Set a Tune Title", "Title:");
            dialog.setVisible(true);
            if (dialog.getResult().equals(AmeDialog.Result.OK)) {
                tuneEditor.getTune().addTitle(dialog.get());
            } else {
                return;
            }
        }
        tuneEditor.updateTune();
    }

}
