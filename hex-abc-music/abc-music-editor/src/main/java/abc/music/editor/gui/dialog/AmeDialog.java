package abc.music.editor.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import abc.music.editor.AbcMusicEditor;
import javax.swing.WindowConstants;

/**
 * Created 2016-dec-01
 *
 * @author hl
 * @param <T>
 */
public abstract class AmeDialog<T> extends JDialog {

    private T obj;
    private Result result;
    protected final AbcMusicEditor editor;

    public AmeDialog(AbcMusicEditor editor, String title) {
        super(editor, title, true);
        this.editor = editor;
        setup();
        super.setLocationRelativeTo(editor);
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void setup() {
        init();
        result = Result.CANCEL;
    }

    protected abstract void init();

    @Override
    protected JRootPane createRootPane() {
        JRootPane customRootPane = new JRootPane();
        ActionListener enterActionListener = (ActionEvent e) -> {
            ok();
        };
        customRootPane.registerKeyboardAction(enterActionListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionListener escActionListener = (ActionEvent e) -> {
            cancel();
        };
        customRootPane.registerKeyboardAction(escActionListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
//        ActionListener f5ActionListener = (ActionEvent e) -> {
//            openNameGeneratorPage();
//        };
//        customRootPane.registerKeyboardAction(f5ActionListener,
//                KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        return customRootPane;
    }

    public Result getResult() {
        return result;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    protected abstract void accept();

    protected void abort() {
    }

    public void ok() {
        accept();
        result = Result.OK;
        setVisible(false);
    }

    public void cancel() {
        abort();
        result = Result.CANCEL;
        setVisible(false);
    }

    protected boolean isEmpty(JTextField field) {
        return field.getText() == null || field.getText().isEmpty();
    }

    protected boolean isEmpty(JTextArea area) {
        return area.getText() == null || area.getText().isEmpty();
    }

    public enum Result {
        OK, CANCEL
    }
}
