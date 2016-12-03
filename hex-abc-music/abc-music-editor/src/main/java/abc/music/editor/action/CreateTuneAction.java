package abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;

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
        Tune tune = new Tune(editor.getProject());
        editor.getProject().addTune(tune);
        setResult(tune);
    }
}
