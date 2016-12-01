package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Voice;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class AddVoiceActon extends AmeAction<Voice> {

    public AddVoiceActon(AbcMusicEditor parent) {
        super(parent);
    }

    @Override
    protected void performAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
