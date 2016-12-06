package abc.music.editor.gui.support;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
 * Created 2016-dec-06
 *
 * @author hl
 */
public class AmeFileView extends FileView {

    @Override
    public String getTypeDescription(File file) {
        for (FileType ft : FileType.values()) {
            if (file.getAbsolutePath().toLowerCase().endsWith(ft.getSuffix())) {
                return ft.getDescription();
            }
        }
        return super.getTypeDescription(file);
    }

    @Override
    public Icon getIcon(File file) {
        for (FileType ft : FileType.values()) {
            if (file.getAbsolutePath().toLowerCase().endsWith(ft.getSuffix())) {
                return ft.getIcon16();
            }
        }
        return super.getIcon(file);
    }

    @Override
    public String getDescription(File f) {
        return null; // Leave handling to look and feel
    }

    @Override
    public Boolean isTraversable(File f) {
        return null; // Leave handling to look and feel
    }

    enum FileType {
        ABC("ABC notation file"),
        AMX("AMX file - Compressed"),
        AMXF("AMX file - Uncompressed"),
        MIDI("MIDI file"),
        PDF("PDF file"),
        PS("PostScript file");
        private final String description;

        private FileType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getSuffix() {
            return name().toLowerCase();
        }

        public ImageIcon getIcon32() {
            return getIcon(32);
        }
        public ImageIcon getIcon16() {
            return getIcon(16);
        }

        private ImageIcon getIcon(int size) {
            return new ImageIcon(getClass().getClassLoader().getResource("images/icons/BlankFileIcon" + size + ".png"));
        }

    }
    /*
    
    ImageFileView() {
        // Preload icons for the file view. The getClass().getResource()
        // construct is used so that an application can be packaged into a JAR 
        // file and still obtain these images.

        bmpicon = new ImageIcon(getClass().getResource("images/bmpicon.gif")); 
        gificon = new ImageIcon(getClass().getResource("images/gificon.gif"));
        jpgicon = new ImageIcon(getClass().getResource("images/jpgicon.gif"));
        pngicon = new ImageIcon(getClass().getResource("images/pngicon.gif"));
    }
     */

}
