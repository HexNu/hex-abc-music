package abc.music.app.action;

import abc.music.app.EditorApp;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created 2017-nov-02
 *
 * @author hl
 */
public class OpenInBrowserAction extends AppAction<Void> {

    private final String url;

    public OpenInBrowserAction(EditorApp app, String url) {
        super(app);
        this.url = url;
    }

    @Override
    protected Void performAction() {
        new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(OpenInBrowserAction.class.getName()).log(Level.SEVERE, "Could not open \"" + url + "\" in the default browser", ex);
            }
        }).start();
        return null;
    }

}
