package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.TunesFromOtherProjectChooser;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created 2016-dec-11
 *
 * @author hl
 */
public class ImportFromOtherProjectAction extends AmeAction<List<Tune>> {

    public ImportFromOtherProjectAction(AbcMusicEditor editor) {
        super(editor);
    }

    @Override
    protected void performAction(ActionEvent event) {
        TunesFromOtherProjectChooser chooser = new TunesFromOtherProjectChooser(editor);
        chooser.showDialog(editor, "Import");
        editor.updateMenuBar();
    }

}
