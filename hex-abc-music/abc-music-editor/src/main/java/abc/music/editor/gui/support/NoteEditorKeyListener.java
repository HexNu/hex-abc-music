package abc.music.editor.gui.support;

import abc.music.core.domain.Voice;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.VoicePanel;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.UndockedVoicePanelDialog;
import abc.music.editor.gui.dialog.notes.AnnotationDialog;
import abc.music.editor.gui.dialog.notes.KeyChangeDialog;
import abc.music.editor.gui.dialog.notes.MeterChangeDialog;
import abc.music.editor.gui.dialog.notes.OrnamentChooser;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import org.ghost4j.GhostscriptLibrary;

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
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    setString(e, SOFT_LINE_BREAK);
                    break;
                case KeyEvent.VK_5:
                    setString(e, COMMENT);
                    break;
                case KeyEvent.VK_K:
                    changeKey(e);
                    break;
                case KeyEvent.VK_M:
                    changeMeter(e);
                    break;
                case KeyEvent.VK_N:
                    addAnnotation(e);
                    break;
                case KeyEvent.VK_G:
                    addChord(e);
                    break;
                case KeyEvent.VK_D:
                    addDecoration(e);
                    break;
                case KeyEvent.VK_U:
                    detachPanel(e);
                    break;
                case KeyEvent.VK_8:
                    addTie(e);
                    break;
                default:
                    super.keyTyped(e);
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

    private void detachPanel(KeyEvent e) {
        if (panel.isDocked()) {
            UndockedVoicePanelDialog dialog = new UndockedVoicePanelDialog(panel, panel.getIndex());
            dialog.setVisible(true);
        }
    }
}
