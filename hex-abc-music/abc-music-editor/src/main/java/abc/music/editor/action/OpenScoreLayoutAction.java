package abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.ScoreLayoutDialog;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class OpenScoreLayoutAction extends AmeAction<String> {

    private final Tune tune;

    public OpenScoreLayoutAction(AbcMusicEditor parent, Tune tune) {
        super(parent);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        ScoreLayoutDialog dialog = new ScoreLayoutDialog(editor, tune);
        dialog.setVisible(true);
    }
}
