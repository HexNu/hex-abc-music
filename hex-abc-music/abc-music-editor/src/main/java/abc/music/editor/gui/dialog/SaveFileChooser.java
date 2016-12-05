package abc.music.editor.gui.dialog;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import nu.hex.abc.music.service.Service;
import nu.hex.mediatype.CommonMediaType;
import nu.hex.mediatype.HexMediaType;

/**
 * Created 2016-nov-14
 *
 * @author hl
 */
public class SaveFileChooser extends JFileChooser {

    public SaveFileChooser(String name, String mediaType) {
        super(getDirectory(mediaType));
        super.setDialogTitle("Select Destination Directory");
        super.setAcceptAllFileFilterUsed(mediaType != null && !mediaType.isEmpty());
        super.setFileFilter(getFileFilter(mediaType));
        super.setSelectedFile(setFile(name, mediaType));
    }

    private static File setFile(String name, String mediaType) {
        name = name.replaceAll("\\s", "_");
        switch (mediaType) {
            case CommonMediaType.TEXT_VND_ABC:
                return new File(getDirectory(mediaType) + "/" + name + ".abc");
            case CommonMediaType.APPLICATION_POSTSCRIPT:
                return new File(getDirectory(mediaType) + "/" + name + ".ps");
            case CommonMediaType.IMAGE_SVG_XML:
                return new File(getDirectory(mediaType) + "/" + name + ".svg");
            case HexMediaType.APPLICATION_VND_HEX_AMX_XML:
                return new File(getDirectory(mediaType) + "/" + name + ".amx");
            default:
                return new File(getDirectory(mediaType) + "/" + name);
        }
    }

    private static File getDirectory(String mediaType) {
        switch (mediaType) {
            case CommonMediaType.TEXT_VND_ABC:
                return Service.getAbcDirectory();
            case CommonMediaType.APPLICATION_POSTSCRIPT:
                return Service.getPsDirectory();
            case CommonMediaType.IMAGE_SVG_XML:
                return Service.getSvgDirectory();
            default:
                return Service.getAppDirectory();
        }
    }

    private static FileNameExtensionFilter getFileFilter(String mediaType) {
        switch (mediaType) {
            case CommonMediaType.TEXT_VND_ABC:
                return new FileNameExtensionFilter("ABC Files", "abc");
            case CommonMediaType.APPLICATION_POSTSCRIPT:
                return new FileNameExtensionFilter("PostScript Files", "ps");
            case CommonMediaType.IMAGE_SVG_XML:
                return new FileNameExtensionFilter("SVG Image Files", "svg");
            case HexMediaType.APPLICATION_VND_HEX_AMX_XML:
                return new FileNameExtensionFilter("Hex ABC Editor Files", "amx");
            default:
                return new FileNameExtensionFilter("Files", "abc", "ps", "svg", "amx");
        }
    }

}
