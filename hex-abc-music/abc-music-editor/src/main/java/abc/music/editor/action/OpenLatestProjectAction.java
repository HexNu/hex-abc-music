package abc.music.editor.action;

import abc.music.core.domain.Project;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.service.properties.AbcMusicProperties;
import nu.hex.abc.music.service.properties.PropertyService;
import nu.hex.abc.music.service.xml.read.ReaderService;

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
        Boolean autoOpen = PropertyService.getProperties().getPropertyAsBoolean(AbcMusicProperties.AUTO_OPEN_LATEST_PROJECT);
        String latestProject = PropertyService.getProperties().getProperty(AbcMusicProperties.LATEST_SAVED_PROJECT);
        if (autoOpen != null && autoOpen && latestProject != null && !latestProject.isEmpty()) {
            setResult(ReaderService.openProject(latestProject));
        }
    }

}
