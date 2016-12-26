package abc.music.editor.action;

import abc.music.editor.AbcMusicEditor;
import abc.music.core.domain.MidiChannels;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.MidiChannelDialog;
import java.awt.event.ActionEvent;

/**
 * Created 2016-dec-26
 *
 * @author hl
 */
public class ShowMidiDialogAction extends AmeAction<MidiChannels.Channel> {

    private final MidiChannels.Channel channel;

    public ShowMidiDialogAction(AbcMusicEditor editor, MidiChannels.Channel channel) {
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
