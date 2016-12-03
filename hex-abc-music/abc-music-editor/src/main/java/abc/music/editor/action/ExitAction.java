package abc.music.editor.action;

import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class ExitAction extends AmeAction<Void> {

    public ExitAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        System.exit(0);
    }
}
