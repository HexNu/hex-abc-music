package abc.music.editor.action;

import abc.music.core.domain.Project;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.EditProjectDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class EditProjectExportSettingsAction extends AmeAction<Project> {

    public EditProjectExportSettingsAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        EditProjectDialog dialog = new EditProjectDialog(editor);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            new SaveProjectAction(editor).actionPerformed(null);
        }
    }
}
