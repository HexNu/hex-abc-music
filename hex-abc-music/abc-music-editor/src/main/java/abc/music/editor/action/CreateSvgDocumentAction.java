package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.digitman.lightxml.DocumentToXmlNodeParser;
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
        try {
            File svgFile = getService().getIoService().createSvgFile(tune);
            XmlDocument svgDoc = new XmlDocument(new DocumentToXmlNodeParser(new FileInputStream(svgFile)).parse());
            setResult(svgDoc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateSvgDocumentAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
