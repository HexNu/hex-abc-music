package abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class BackupProjectAction extends AmeAction<Project> {

    private final Project project;

    public BackupProjectAction(AbcMusicEditor parent) {
        super(parent);
        this.project = parent.getProject();
    }

    @Override
    protected void performAction(ActionEvent event) {
        new SaveTuneAction(editor).performAction(event);
        getService().getWriterService().backupProject();
        setRightStatus("Backup done of project \"" + project.getName() + "\".", 1800);
        int numberOfTunes = project.getTunes().size();
        String delayedMessage = "Project contains " + numberOfTunes + " ";
        delayedMessage += numberOfTunes == 1 ? "tune." : "tunes.";
        setRightStatus(delayedMessage, 1500, 1803);
    }
}
