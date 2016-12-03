package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import se.digitman.lightxml.XmlDocument;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class CreateSvgDocumentAction extends AmeAction<XmlDocument> {

    private final Tune tune;

    public CreateSvgDocumentAction(AbcMusicEditor editor, Tune tune) {
        super(editor);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        setResult(getService().getIoService().createSvgDocument(tune));
    }

}
