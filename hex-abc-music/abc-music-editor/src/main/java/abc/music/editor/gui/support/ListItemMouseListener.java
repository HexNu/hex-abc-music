package abc.music.editor.gui.support;

import abc.music.editor.action.OpenLinkAction;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public abstract class ListItemMouseListener extends MouseAdapter {

    protected void clickAction() {
    }

    protected void doubleClickAction() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            clickAction();
        }
        if (e.getClickCount() == 2) {
            doubleClickAction();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JLabel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JLabel) e.getSource()).setCursor(Cursor.getDefaultCursor());
    }

}
