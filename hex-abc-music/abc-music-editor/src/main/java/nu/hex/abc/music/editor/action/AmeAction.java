package nu.hex.abc.music.editor.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nu.hex.abc.music.editor.AbcMusicEditor;

/**
 * Created 2016-dec-01
 *
 * @author hl
 * @param <T>
 */
public abstract class AmeAction<T> implements ActionListener {

    private T result = null;
    protected final AbcMusicEditor parent;

    public AmeAction(AbcMusicEditor parent) {
        this.parent = parent;
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
}
