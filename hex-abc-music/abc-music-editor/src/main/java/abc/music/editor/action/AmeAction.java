package abc.music.editor.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import abc.music.editor.AbcMusicEditor;
import nu.hex.abc.music.service.Service;

/**
 * Created 2016-dec-01
 *
 * @author hl
 * @param <T>
 */
public abstract class AmeAction<T> implements ActionListener {

    private T result = null;
    protected final AbcMusicEditor editor;

    public AmeAction(AbcMusicEditor editor) {
        this.editor = editor;
    }

    @Override
    public final void actionPerformed(ActionEvent event) {
        performAction(event);
    }

    protected abstract void performAction(ActionEvent event);

    protected void setResult(T result) {
        this.result = result;
    }

    public T get() {
        return result;
    }

    protected Service getService() {
        if (editor.getProject() == null) {
            return null;
        }
        return new Service(editor.getProject());
    }

    protected void setRightStatus(String message) {
        editor.getStatusBar().setRightStatus(message);
    }

    protected void setRightStatus(String message, int time) {
        editor.getStatusBar().setRightStatus(message, time);
    }

    protected void setRightStatus(String message, int time, int delay) {
        editor.getStatusBar().setRightStatus(message, time, delay);
    }

    protected void setCenterStatus(String message) {
        editor.getStatusBar().setCenterStatus(message);
    }

    protected void setCenterStatus(String message, int time) {
        editor.getStatusBar().setCenterStatus(message, time);
    }

    protected void setLeftStatus(String message) {
        editor.getStatusBar().setLeftStatus(message);
    }

    protected void setLeftStatus(String message, int time) {
        editor.getStatusBar().setLeftStatus(message, time);
    }
}
