package abc.music.editor.gui.support;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Created 2016-dec-07
 *
 * @author hl
 */
public abstract class AmeKeyAdapter extends KeyAdapter {

    private Robot robot;

    public AmeKeyAdapter() {
        try {
            this.robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(AmeKeyAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setString(KeyEvent event, String text, Integer moveCursor) {
        if (robot != null) {
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
        if (moveCursor != null && event != null) {
            Timer timer = new Timer(100, (ActionEvent e) -> {
                if (event.getSource() instanceof JTextArea) {
                    JTextArea area = (JTextArea) event.getSource();
                    area.setCaretPosition(area.getCaretPosition() + moveCursor);
                } else if (event.getSource() instanceof JTextField) {
                    JTextField field = (JTextField) event.getSource();
                    field.setCaretPosition(field.getCaretPosition() + moveCursor);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    protected void setString(KeyEvent e, String text) {
        setString(e, text, 0);
    }
}
