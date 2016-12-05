package abc.music.editor.gui.support;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class SharpFlatNaturalKeyListener extends KeyAdapter {

    public SharpFlatNaturalKeyListener() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.isControlDown()) {
            
            switch (e.getKeyCode()) {
                case KeyEvent.VK_3:
                    setChar('♯', e.getSource());
                    break;
                case KeyEvent.VK_B:
                    setChar('♭', e.getSource());
                    break;
                case KeyEvent.VK_0:
                    setChar('♮', e.getSource());
                    break;
                default:
                    super.keyTyped(e);
                    break;
            }
        }
        super.keyTyped(e);
    }
    
    private void setChar(char c, Object source)  {
        if (source instanceof JTextArea) {
            ((JTextArea) source).setText(((JTextArea) source).getText() + c);
        } else if (source instanceof JTextField) {
            ((JTextField) source).setText(((JTextField) source).getText() + c);
        }
    }

}
