package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.core.domain.Voice;
import java.awt.event.ActionEvent;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.CreateVoiceDialog;

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
        CreateVoiceDialog dialog = new CreateVoiceDialog(editor, tune);
        dialog.setVisible(true);
        setResult(dialog.get());
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            Voice voice = get();
            if (voice.getVoiceId() == null || voice.getVoiceId().isEmpty()) {
                voice.setVoiceId(String.valueOf(tune.getVoices().size() + 1));
                voice.getKey().setMode(tune.getKey().getMode());
                voice.getKey().setPitch(tune.getKey().getPitch());
                voice.getKey().setSignature(tune.getKey().getSignature());
                voice.getKey().getModifier().setClef(tune.getKey().getModifier().getClef());
                voice.getKey().getModifier().setOctave(tune.getKey().getModifier().getOctave());
                voice.getKey().getModifier().setTranspose(tune.getKey().getModifier().getTranspose());
            }
            tune.addVoice(get());
        }
    }
}
