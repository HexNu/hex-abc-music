package abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.ProjectFileChooser;
import nu.hex.abc.music.service.xml.read.ReaderService;

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
        setRightStatus("Select project file to open");
        int status = fileChooser.showOpenDialog(editor);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            setRightStatus("Opening file: " + file.getAbsolutePath());
            if (file != null) {
                setResult(ReaderService.openProject(file));
                setRightStatus("Project \"" + get().getName() + "\" opened.", 5000);
            }
        } else {
            setRightStatus("Open project cancelled", 3000);
        }
    }

}
