package abc.music.editor.action;

import abc.music.editor.AbcMusicEditor;
import abc.music.core.domain.VoiceMidiChannel;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.MidiChannelDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-26
 *
 * @author hl
 */
public class ShowMidiDialogAction extends AmeAction<VoiceMidiChannel> {

    private final VoiceMidiChannel channel;

    public ShowMidiDialogAction(AbcMusicEditor editor, VoiceMidiChannel channel) {
        super(editor);
        this.channel = channel;
    }

    @Override
    protected void performAction(ActionEvent event) {
        MidiChannelDialog dialog = new MidiChannelDialog(editor, channel);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            setResult(dialog.get());
        }
    }

}
