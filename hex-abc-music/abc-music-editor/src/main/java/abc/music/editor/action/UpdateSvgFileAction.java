package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class UpdateSvgFileAction extends AmeAction<File> {

    private final Tune tune;

    public UpdateSvgFileAction(AbcMusicEditor editor, Tune tune) {
        super(editor);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        setResult(getService().getIoService().createSvgFile(tune));
    }
    
}
