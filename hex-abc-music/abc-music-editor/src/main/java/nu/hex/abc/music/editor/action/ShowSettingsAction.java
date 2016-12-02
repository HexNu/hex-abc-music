package nu.hex.abc.music.editor.action;

import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.settings.SettingsDialog;

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
        new SettingsDialog(parent.getProject(), parent).setVisible(true);
    }
}
