package abc.music.editor.action;

import abc.music.core.domain.Tune;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.TuneHeadersPanel;

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
        TuneHeadersPanel tuneEditor = editor.getTuneEditor();
        tuneEditor.updateTune();
    }

}
