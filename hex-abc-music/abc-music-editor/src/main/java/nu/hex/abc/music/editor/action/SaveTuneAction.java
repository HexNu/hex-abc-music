package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.TuneHeadersPanel;

/**
 * Created 2016-dec-02
 *
 * @author hl
 */
public class SaveTuneAction extends AmeAction<Tune> {

    public SaveTuneAction(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        TuneHeadersPanel tuneEditor = parent.getTuneEditor();
        tuneEditor.updateTune();
    }

}
