package nu.hex.abc.music.editor.action;

import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.AmeDialog;
import nu.hex.abc.music.editor.components.CloseProjectDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class CloseProjectAction extends AmeAction<Void> {

    public CloseProjectAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        String projectName = parent.getProject().getName();
        setRightStatus("Closing project \"" + projectName + "\"");
        CloseProjectDialog dialog = new CloseProjectDialog(parent);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            parent.clearProject();
            setRightStatus("Project \"" + projectName + "\" closed", 3000);
        } else {
            setRightStatus("Closing project \"" + projectName +"\" cancelled", 2000);
        }
    }

}
