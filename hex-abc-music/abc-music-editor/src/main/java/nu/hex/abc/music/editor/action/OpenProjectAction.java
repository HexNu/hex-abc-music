package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.ProjectFileChooser;
import nu.hex.abc.music.service.Service;

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
        ProjectFileChooser fileChooser = new ProjectFileChooser();
        int status = fileChooser.showOpenDialog(parent);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                setResult(Service.openProject(file));
            }
        }
    }

}
