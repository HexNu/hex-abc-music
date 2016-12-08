package abc.music.editor.gui.support;

import abc.music.core.domain.Voice;
import abc.music.editor.AbcMusicEditor;
import abc.music.editor.gui.dialog.AmeDialog;
import abc.music.editor.gui.dialog.notes.AnnotationDialog;
import abc.music.editor.gui.dialog.notes.KeyChangeDialog;
import abc.music.editor.gui.dialog.notes.OrnamentChooser;
import java.awt.event.KeyEvent;

/**
 * Created 2016-dec-07
 *
 * @author hl
 */
public class NoteEditorKeyListener extends AmeKeyAdapter {

    private static final String SOFT_LINE_BREAK = "\\ \n  ",
            COMMENT = "% ";
    private final Voice voice;
    private final AbcMusicEditor editor;

    public NoteEditorKeyListener(AbcMusicEditor editor, Voice voice) {
        this.editor = editor;
        this.voice = voice;
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
                case KeyEvent.VK_N:
                    addAnnotation(e);
                    break;
                case KeyEvent.VK_G:
                    addChord(e);
                    break;
                case KeyEvent.VK_D:
                    addDecoration(e);
                    break;
                default:
                    super.keyTyped(e);
                    break;
            }
        }
    }

    private void changeKey(KeyEvent event) {
        KeyChangeDialog dialog = new KeyChangeDialog(editor, voice);
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
//        chooser.setVisible(true);
        if (chooser.getResult().equals(AmeDialog.Result.OK) && !chooser.get().isEmpty()) {
            setString(e, chooser.get());
        }
    }
}
