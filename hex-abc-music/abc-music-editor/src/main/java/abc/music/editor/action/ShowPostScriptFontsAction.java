package abc.music.editor.action;

import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.ShowPostScriptFontsDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-16
 *
 * @author hl
 */
public class ShowPostScriptFontsAction extends AmeAction<Void> {

    public ShowPostScriptFontsAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        new ShowPostScriptFontsDialog(editor).setVisible(true);
    }

}
