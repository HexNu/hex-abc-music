package abc.music.editor.action;

import abc.music.core.domain.Tune;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.SaveFileChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import nu.hex.mediatype.CommonMediaType;
import nu.hex.mediatype.HexMediaType;

/**
 * Created 2016-dec-04
 *
 * @author hl
 */
public class CreateFileAction extends AmeAction<File> {

    private final List<Tune> tunes;
    private final String mediaType;
    private String name;

    public CreateFileAction(AbcMusicEditor editor, Tune tune, String mediaType) {
        this(editor, Arrays.asList(tune), mediaType);
        name = tune.getName();
    }

    public CreateFileAction(AbcMusicEditor editor, List<Tune> tunes, String mediaType) {
        super(editor);
        this.tunes = tunes;
        this.mediaType = mediaType;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void performAction(ActionEvent event) {
        if (name == null) {
            name = "Out";
        }
        SaveFileChooser fileChooser = new SaveFileChooser(name, mediaType);
        int chooserResult = fileChooser.showSaveDialog(editor);
        if (chooserResult == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            switch (mediaType) {
                case CommonMediaType.TEXT_VND_ABC:
                    setResult(getService().getIoService().createAbcFile(tunes, file));
                    break;
                case CommonMediaType.APPLICATION_POSTSCRIPT:
                    setResult(getService().getIoService().createPsFile(tunes, file));
                    break;
                case CommonMediaType.APPLICATION_PDF:
                    setResult(getService().getIoService().createPdfFile(tunes, file));
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
            setRightStatus("File created: " +  get().getAbsolutePath(), 3000);
        }
    }

}
