package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import java.io.File;
import nu.hex.mediatype.CommonMediaType;

/**
 * Created 2016-dec-22
 *
 * @author hl
 */
public class GetMidiFileAction extends AmeAction<File> {

    private final Tune tune;

    public GetMidiFileAction(AbcMusicEditor editor, Tune tune) {
        super(editor);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        CreateFileAction action = new CreateFileAction(editor, tune, CommonMediaType.AUDIO_MIDI);
        action.actionPerformed(event);
        setResult(action.get());
        
    }
}
