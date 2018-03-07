package abc.music.app.control;

import abc.music.app.EditorApp;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

/**
 * Created 2017-okt-31
 *
 * @author hl
 */
public class AppInfoAlert extends Alert {

    protected final EditorApp manager;

    public AppInfoAlert(EditorApp app) {
        this(app, "Information");
    }

    public AppInfoAlert(EditorApp app, String title) {
        super(AlertType.INFORMATION);
        super.setTitle(title);
        super.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        super.getDialogPane().getStylesheets().add("/styles/dialog.css");
        super.setResizable(true);
        this.manager = app;
    }

    public void setMinWidth(double minWidth) {
        super.getDialogPane().setMinWidth(minWidth);
    }

    public void setHeader(String text) {
        super.setHeaderText(text);
    }

    public void setContent(StringBuilder builder) {
        setContent(builder.toString());
    }

    public void setContent(String content) {
        super.setContentText(content);
    }
}
