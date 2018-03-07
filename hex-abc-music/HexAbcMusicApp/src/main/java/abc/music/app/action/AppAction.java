package abc.music.app.action;

import abc.music.app.EditorApp;


/**
 * Created 2017-okt-24
 *
 * @author hl
 * @param <T>
 */
public abstract class AppAction<T> {

    protected final EditorApp app;

    public AppAction(EditorApp app) {
        this.app = app;
    }

    public final T actionPerformed() {
        if (!isEnabled()) {
            return null;
        }
        return performAction();
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract T performAction();
}
