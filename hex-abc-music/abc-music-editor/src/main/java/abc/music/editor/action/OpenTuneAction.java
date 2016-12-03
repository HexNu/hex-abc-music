package abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class OpenTuneAction extends AmeAction<Tune> {

    private final Tune tune;

    public OpenTuneAction(AbcMusicEditor parent, Tune tune) {
        super(parent);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        editor.getTuneEditor().setTune(tune);
    }
}
