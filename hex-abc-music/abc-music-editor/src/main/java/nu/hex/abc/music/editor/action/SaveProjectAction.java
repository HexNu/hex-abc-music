package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class SaveProjectAction extends AmeAction<Project> {

    private final Project project;

    public SaveProjectAction(AbcMusicEditor parent) {
        super(parent);
        this.project = parent.getProject();
    }

    @Override
    protected void performAction(ActionEvent event) {
        new Service(project).saveProject();
    }
}
