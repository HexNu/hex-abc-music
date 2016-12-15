package abc.music.editor.gui.support;

import abc.music.core.util.TextUtil;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.VoicePanel;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.UndockedVoicePanelDialog;
import abc.music.editor.gui.dialog.tune.AnnotationDialog;
import abc.music.editor.gui.dialog.tune.KeyChangeDialog;
import abc.music.editor.gui.dialog.tune.MeterChangeDialog;
import abc.music.editor.gui.dialog.tune.OrnamentChooser;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

/**
 * Created 2016-dec-07
 *
 * @author hl
 */
public class NoteEditorKeyListener extends AmeKeyAdapter {

    private static final String SOFT_LINE_BREAK = "\\ \n  ",
            COMMENT = "% ";
    private final AbcMusicEditor editor;
    private final VoicePanel panel;

    public NoteEditorKeyListener(AbcMusicEditor editor, VoicePanel panel) {
        this.editor = editor;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.isControlDown()) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    setString(event, SOFT_LINE_BREAK);
                    break;
                case KeyEvent.VK_5:
                    setString(event, COMMENT);
                    break;
                case KeyEvent.VK_8:
                    addTie(event);
                    break;
                case KeyEvent.VK_D:
                    addDecoration(event);
                    break;
                case KeyEvent.VK_G:
                    addChord(event);
                    break;
                case KeyEvent.VK_K:
                    changeKey(event);
                    break;
                case KeyEvent.VK_M:
                    changeMeter(event);
                    break;
                case KeyEvent.VK_N:
                    addAnnotation(event);
                    break;
                case KeyEvent.VK_R:
                    reverse(event);
                    break;
                case KeyEvent.VK_U:
                    detachPanel(event);
                    break;
                case KeyEvent.VK_W:
                    addWordLine(event);
                    break;
                case KeyEvent.VK_LESS:
                    setString(event, " | ", 3);
                case KeyEvent.VK_PERIOD:
                    setString(event, " :: ", 4);
                default:
                    System.out.println(event.getKeyCode());
                    super.keyTyped(event);
                    break;
            }
        }
    }

    private void changeKey(KeyEvent event) {
        KeyChangeDialog dialog = new KeyChangeDialog(editor, panel.getVoice());
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            setString(event, "[" + dialog.get().get() + "] ");
        }
    }

    private void changeMeter(KeyEvent event) {
        MeterChangeDialog dialog = new MeterChangeDialog(editor, panel.getVoice());
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            setString(event, "[" + dialog.get().get() + "] ");
        }
    }

    private void addAnnotation(KeyEvent e) {
        AnnotationDialog dialog = new AnnotationDialog(editor);
        dialog.setVisible(true);
        if (dialog.getResult().equals(AmeDialog.Result.OK)) {
            if (dialog.getAnnotation().isEmpty()) {
                setString(e, dialog.get(), -2);
            } else {
                setString(e, dialog.get());
            }
        }
    }

    private void addChord(KeyEvent event) {
        setString(event, "\"\"", -1);
    }

    private void addDecoration(KeyEvent e) {
        OrnamentChooser chooser = new OrnamentChooser(editor);
        if (chooser.getResult().equals(AmeDialog.Result.OK) && !chooser.get().isEmpty()) {
            setString(e, chooser.get());
        }
    }

    private void addTie(KeyEvent event) {
        JTextArea source = (JTextArea) event.getSource();
        if (source.getSelectedText() != null) {
            String text = source.getText();
            int end = source.getSelectionEnd();
            String first = text.substring(0, source.getSelectionStart());
            String second = source.getSelectedText();
            String third = text.substring(source.getSelectionEnd());
            source.setText(first + "(" + second + ")" + third);
            source.setCaretPosition(end + 2);
        }
    }

    private void reverse(KeyEvent event) {
        JTextArea source = (JTextArea) event.getSource();
        if (source.getSelectedText() != null) {
            String text = source.getText();
            int end = source.getSelectionEnd();
            String first = text.substring(0, source.getSelectionStart());
            String second = source.getSelectedText();
            String reversed = "";
            for (String s : second.split("(?=[a-gA-G])")) {
                reversed = s + reversed;
            }
            String third = text.substring(source.getSelectionEnd());
            source.setText(first + reversed + third);
            source.setCaretPosition(end);
        }
    }

    private void addWordLine(KeyEvent event) {
        JTextArea source = (JTextArea) event.getSource();
        String text = source.getText();
        int caretPosition = source.getCaretPosition();
        int endOfCurrentLine = new TextUtil(text).nextIndexOf('\n', caretPosition);
        String first = text.substring(0, endOfCurrentLine);
        String second = "\nw: ";
        String third = text.substring(endOfCurrentLine);
        source.setText(first + second + third);
        source.setCaretPosition(endOfCurrentLine + 4);
    }

    private void detachPanel(KeyEvent event) {
        if (panel.isDocked()) {
            UndockedVoicePanelDialog dialog = new UndockedVoicePanelDialog(panel, panel.getIndex());
            dialog.setVisible(true);
        }
    }
}
