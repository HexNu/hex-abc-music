package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class CreateTuneAction extends AmeAction<Tune> {

    public CreateTuneAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        Tune tune = new Tune(parent.getProject());
        setResult(tune);
    }
}
