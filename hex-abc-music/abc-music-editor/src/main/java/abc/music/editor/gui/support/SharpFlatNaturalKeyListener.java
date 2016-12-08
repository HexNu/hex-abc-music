package abc.music.editor.gui.support;

import java.awt.event.KeyEvent;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class SharpFlatNaturalKeyListener extends AmeKeyAdapter {

    private static final String SHARP = "♯", FLAT = "♭", NATURAL = "♮";

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_3:
                    setString(e, SHARP);
                    break;
                case KeyEvent.VK_B:
                    setString(e, FLAT);
                    break;
                case KeyEvent.VK_0:
                    setString(e, NATURAL);
                    break;
                default:
                    super.keyTyped(e);
                    break;
            }
        }
    }
}
