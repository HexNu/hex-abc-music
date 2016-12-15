package abc.music.editor.action;

import abc.music.core.domain.Collection;
import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import java.awt.event.ActionEvent;
import java.io.File;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-dec-15
 *
 * @author hl
 */
public class PrintAction extends AmeAction<Void> {

    private File pdfFile;
    private Collection collection;
    private Tune tune;

    public PrintAction(AbcMusicEditor editor) {
        super(editor);
    }

    public PrintAction(AbcMusicEditor editor, Collection collection) {
        super(editor);
        this.collection = collection;
    }

    public PrintAction(AbcMusicEditor editor, Tune tune) {
        super(editor);
        this.tune = tune;
    }

    @Override
    protected void performAction(ActionEvent event) {
        try {
            File printDirectory = Service.getPrintDirectory();
            if (tune != null) {
                pdfFile = new File(printDirectory.getAbsolutePath() + "/" + tune.getName().replaceAll("\\s", "_") + ".pdf");
                pdfFile = getService().getIoService().createPdfFile(tune, pdfFile);
            } else if (collection != null) {
                pdfFile = new File(printDirectory.getAbsoluteFile() + "/" + collection.getName().replaceAll("\\s", "_") + ".pdf");
                pdfFile = getService().getIoService().exportCollectionAsPdf(collection, pdfFile);
            }
            if (pdfFile != null) {
                getService().getIoService().printFile(pdfFile);
            }
        } finally {
            if (pdfFile != null) {
                pdfFile.delete();
            }
        }
    }

}
