package abc.music.editor.action;

import abc.music.core.domain.Collection;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.SaveFileChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import nu.hex.mediatype.CommonMediaType;
import nu.hex.mediatype.HexMediaType;

/**
 * Created 2016-dec-12
 *
 * @author hl
 */
public class ExportCollectionAction extends AmeAction<File> {

    private final Collection collection;
    private final String mediaType;

    public ExportCollectionAction(AbcMusicEditor editor) {
        this(editor, editor.getProject(), CommonMediaType.APPLICATION_POSTSCRIPT);
    }

    public ExportCollectionAction(AbcMusicEditor editor, Collection collection, String mediaType) {
        super(editor);
        this.collection = collection;
        this.mediaType = mediaType;
    }

    @Override
    protected void performAction(ActionEvent event) {
        SaveFileChooser fileChooser = new SaveFileChooser(collection.getName(), mediaType);
        int chooserResult = fileChooser.showSaveDialog(editor);
        if (chooserResult == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            switch (mediaType) {
                case CommonMediaType.TEXT_VND_ABC:
                    setResult(getService().getIoService().exportCollectionAsAbc(collection, file));
                    break;
                case CommonMediaType.APPLICATION_POSTSCRIPT:
                    setResult(getService().getIoService().exportCollectionAsPs(collection, file));
                    break;
                case CommonMediaType.APPLICATION_PDF:
                    setResult(getService().getIoService().exportCollectionAsPdf(collection, file));
                    break;
                case CommonMediaType.IMAGE_SVG_XML:
                    break;
                case HexMediaType.APPLICATION_VND_HEX_AMX:
                    break;
                case HexMediaType.APPLICATION_VND_HEX_AMXF_XML:
                    break;
                default:
                    break;

            }
            setRightStatus("File created: " + get().getAbsolutePath(), 3000);
        }
    }

}
