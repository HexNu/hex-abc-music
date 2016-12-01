package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.OpenProjectDialog;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class OpenProjectAction extends AmeAction<Project> {

    public OpenProjectAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        OpenProjectDialog dialog = new OpenProjectDialog(parent);
        dialog.setVisible(true);
    }

}
