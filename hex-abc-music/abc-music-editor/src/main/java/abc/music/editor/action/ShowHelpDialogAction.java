package abc.music.editor.action;

import abc.music.editor.AbcMusicEditor;
import abc.music.editor.help.HelpDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class ShowHelpDialogAction extends AmeAction<Void> {

    public ShowHelpDialogAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        new HelpDialog(editor).setVisible(true);
    }
}
