package abc.music.app.action;

import abc.music.app.EditorApp;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2017-nov-02
 *
 * @author hl
 */
public class OpenSystemFileAction extends AppAction<Void> {

    private final File file;

    public OpenSystemFileAction(EditorApp app, File file) {
        super(app);
        this.file = file;
    }

    @Override
    protected Void performAction() {
        new Thread(() -> {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(OpenSystemFileAction.class.getName()).log(Level.SEVERE, "Could not find application to open file " + file.getAbsolutePath(), ex);
            }
        }).start();
        return null;
    }

}
