package abc.music.app.action;

import abc.music.app.EditorApp;

/**
 * Created 2017-nov-03
 *
 * @author hl
 */
public class BackupProjectAction extends AppAction<Void> {

    public BackupProjectAction(EditorApp app) {
        super(app);
    }

    @Override
    protected Void performAction() {
//        app.getService().backup();
//        app.setRightFooter("Backup of " + app.getProject().getName() + " done", 3);
        return null;
    }

}
