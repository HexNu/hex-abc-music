package abc.music.editor.action;

import abc.music.core.domain.Project;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.service.Service;
import nu.hex.abc.music.service.properties.AbcMusicProperties;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class OpenLatestProjectAction extends AmeAction<Project> {

    public OpenLatestProjectAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        Boolean autoOpen = AbcMusicProperties.getInstance().getPropertyAsBoolean(AbcMusicProperties.AUTO_OPEN_LATEST_PROJECT);
        String latestProject = AbcMusicProperties.getInstance().getProperty(AbcMusicProperties.LATEST_SAVED_PROJECT);
        if (autoOpen != null && autoOpen && latestProject != null && !latestProject.isEmpty()) {
            setResult(Service.openProject(latestProject));
        }
    }

}
