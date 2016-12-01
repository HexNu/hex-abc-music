package nu.hex.abc.music.editor.components;

import abc.music.core.domain.Project;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import nu.hex.abc.music.editor.Constants;

/**
 * Created 2016-dec-01
 *
 * @author hl
 */
public abstract class AmePanel extends JPanel {

    protected final Project project;
    private final String title;
    private final Font font;

    public AmePanel(Project project, String title) {
        this(project, title, Constants.MEDIUM_TITLE_FONT);
    }

    public AmePanel(Project project, String title, Font font) {
        this.project = project;
        this.title = title;
        this.font = font;
        setup();
    }

    private void setup() {
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, font, Constants.TITLE_COLOR));
        init();
    }

    protected abstract void init();
}
