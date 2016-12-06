package abc.music.editor.gui;

import abc.music.editor.AbcMusicEditor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public abstract class AmeSidePanel extends AmePanel {

    public AmeSidePanel(AbcMusicEditor editor) {
        super(editor, "");
    }

    public AmeSidePanel(AbcMusicEditor editor, Font font) {
        super(editor, "", font);
    }

    @Override
    protected Border getPanelBorder() {
        return BorderFactory.createEmptyBorder();
    }

}
