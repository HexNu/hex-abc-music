package nu.hex.abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import java.awt.event.ActionEvent;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.components.AmeDialog;
import nu.hex.abc.music.editor.components.CreateVoiceDialog;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public class CreateVoiceActon extends AmeAction<Voice> {

    private final Tune tune;

    public CreateVoiceActon(AbcMusicEditor parent, Tune tune) {
        super(parent);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        CreateVoiceDialog dialog = new CreateVoiceDialog(parent, tune);
        dialog.setVisible(true);
        setResult(dialog.get());
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            tune.addVoice(get());
        }
    }
}
