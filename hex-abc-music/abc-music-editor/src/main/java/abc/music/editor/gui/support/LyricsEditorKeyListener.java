package abc.music.editor.gui.support;

import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.LyricsPanel;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.tune.FontChooserDialog;
import java.awt.event.KeyEvent;

/**
 * Created 2016-dec-13
 *
 * @author hl
 */
public class LyricsEditorKeyListener extends AmeKeyAdapter {

    private final AbcMusicEditor editor;
    private final LyricsPanel panel;

    public LyricsEditorKeyListener(LyricsPanel panel) {
        this.editor = panel.getEditor();
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.isControlDown()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_T:
                    setTextLine(event);
                    break;
                default:
                    super.keyTyped(event);
                    break;
            }
        }
    }

    private void setTextLine(KeyEvent event) {
        FontChooserDialog dialog = new FontChooserDialog(panel);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            setString(event, dialog.get().toString());
        }
    }
}
