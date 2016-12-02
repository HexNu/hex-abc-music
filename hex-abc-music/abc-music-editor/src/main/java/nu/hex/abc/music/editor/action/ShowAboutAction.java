package nu.hex.abc.music.editor.action;

import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.settings.AboutDialog;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class ShowAboutAction extends AmeAction<Void> {

    public ShowAboutAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        new AboutDialog(parent).setVisible(true);
    }

    
}
