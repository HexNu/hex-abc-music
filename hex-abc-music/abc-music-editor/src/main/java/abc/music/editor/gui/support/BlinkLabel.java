package abc.music.editor.gui.support;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
public class BlinkLabel extends JLabel {

    private static final int BLINKING_RATE = 750; // period in milliseconds

    private boolean blinking = false;
    private Timer timer = null;

    /**
     * Create a new blinkable label with given text string.
     *
     * @param text a string to display on the label.
     */
    public BlinkLabel(String text) {
        super(text);
    }

    /**
     * Turn the blinking on or off.
     *
     * @param blinking is true to turn blinking on
     */
    public void setBlinking(boolean blinking) {
        if (this.blinking == blinking) {
            return; // nothing to do
        }
        this.blinking = blinking;
        // if there is a timer running then cancel it.
        // there should only be a timer if the label is blinking.
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        // to make the label blink, schedule a BlinkTask.
        if (blinking) {
            timer = new Timer();
            // parameters are: (TimerTask, delay, period)
            timer.scheduleAtFixedRate(new BlinkTask(this), 0, BLINKING_RATE);
        }
    }

    /**
     * Get the blinking state of the label.
     *
     * @return true if label is in blinking state
     */
    public boolean getBlinking() {
        return this.blinking;
    }

    /**
     * A task to control blinking behavior.
     */
    class BlinkTask extends TimerTask {

        private final JLabel label;
        /**
         * label's original background color
         */
        private final Color bg;
        /**
         * label's original foreground color
         */
        private final Color fg;

        public BlinkTask(JLabel label) {
            this.label = label;
            fg = label.getForeground();
            bg = label.getBackground();
        }

        /**
         * Reverse the foreground color of label.
         */
        @Override
        public void run() {
            if (label.getForeground() == fg) {
                label.setForeground(bg);
                label.setBackground(fg);
            } else {
                label.setForeground(fg);
                label.setBackground(bg);
            }
        }

        /**
         * When the TimerTask is canceled, restore the original foreground
         * color. This ensures the label is visible.
         */
        @Override
        public boolean cancel() {
            label.setForeground(fg);
            return true; // success
        }
    }

}
