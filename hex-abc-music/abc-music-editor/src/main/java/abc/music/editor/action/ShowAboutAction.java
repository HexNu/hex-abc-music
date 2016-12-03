package abc.music.editor.action;

import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.settings.AboutDialog;

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
        new AboutDialog(editor).setVisible(true);
    }
}
