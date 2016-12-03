package abc.music.editor.action;

import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.settings.SettingsDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class ShowSettingsAction extends AmeAction<Void> {

    public ShowSettingsAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        new SettingsDialog(editor.getProject(), editor).setVisible(true);
    }
}
