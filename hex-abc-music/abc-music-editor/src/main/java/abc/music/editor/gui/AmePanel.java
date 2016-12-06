package abc.music.editor.gui;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.AmeConstants;
import javax.swing.border.Border;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public abstract class AmePanel extends JPanel {

    protected AbcMusicEditor editor;
    private final String title;
    private final Font font;

    public AmePanel(AbcMusicEditor editor, String title) {
        this(editor, title, AmeConstants.MEDIUM_TITLE_FONT);
    }

    public AmePanel(AbcMusicEditor editor, String title, Font font) {
        this.title = title;
        this.font = font;
        this.editor = editor;
        setup();
    }

    public AbcMusicEditor getApplication() {
        return editor;
    }

    private void setup() {
        setOpaque(false);
        setBorder(getPanelBorder());
        init();
    }
    
    protected Border getPanelBorder() {
        return BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, font, AmeConstants.TITLE_COLOR);
    }

    protected abstract void init();
}
