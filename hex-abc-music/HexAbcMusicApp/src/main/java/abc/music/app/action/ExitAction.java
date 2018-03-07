package abc.music.app.action;

import abc.music.app.EditorApp;


/**
 * Created 2017-okt-27
 *
 * @author hl
 */
public class ExitAction extends AppAction<Void> {

    public ExitAction(EditorApp app) {
        super(app);
    }

    @Override
    protected Void performAction() {
        System.exit(0);
        return null;
    }

}
