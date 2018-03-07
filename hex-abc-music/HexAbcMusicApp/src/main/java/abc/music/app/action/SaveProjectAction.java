package abc.music.app.action;

import abc.music.app.EditorApp;
import abc.music.core.event.listener.ChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created 2017-nov-03
 *
 * @author hl
 */
public class SaveProjectAction extends AppAction<File> {

    public static final List<ChangeListener> listeners = new ArrayList<>();

    public SaveProjectAction(EditorApp app) {
        super(app);
    }

    public static void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    protected File performAction() {
        File result = null;
//         result = app.getService().save();
        listeners.forEach((l) -> {
            l.set();
        });
//        app.setRightFooter(app.getProject().getName() + " saved", 3);
        return result;
    }

}
