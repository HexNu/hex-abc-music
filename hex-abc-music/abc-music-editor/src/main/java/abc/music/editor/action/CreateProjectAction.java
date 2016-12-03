package abc.music.editor.action;

import abc.music.core.domain.Project;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.components.CreateProjectDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class CreateProjectAction extends AmeAction<Project> {

    public CreateProjectAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        CreateProjectDialog dialog = new CreateProjectDialog(editor);
        dialog.setVisible(true);
        setResult(dialog.get());
        
        
        
    }
    
}
