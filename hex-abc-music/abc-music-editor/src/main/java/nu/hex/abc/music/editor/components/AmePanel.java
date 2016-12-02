package nu.hex.abc.music.editor.components;

import abc.music.core.domain.Project;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import nu.hex.abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.editor.AmeConstants;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public abstract class AmePanel extends JPanel {

    protected AbcMusicEditor editor;
    protected final Project project;
    private final String title;
    private final Font font;

    public AmePanel(AbcMusicEditor editor, Project project, String title) {
        this(editor, project, title, AmeConstants.MEDIUM_TITLE_FONT);
    }

    public AmePanel(AbcMusicEditor editor, Project project, String title, Font font) {
        this.project = project;
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
        setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, font, AmeConstants.TITLE_COLOR));
        init();
    }

    protected abstract void init();
}
