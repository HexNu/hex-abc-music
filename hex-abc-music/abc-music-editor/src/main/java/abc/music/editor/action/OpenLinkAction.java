package abc.music.editor.action;

import abc.music.editor.AbcMusicEditor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.net.URI;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class OpenLinkAction extends AmeAction<Void> {

    private final URI uri;

    public OpenLinkAction(AbcMusicEditor editor, URI uri) {
        super(editor);
        this.uri = uri;
    }

    @Override
    protected void performAction(ActionEvent event) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                setRightStatus("Could not open the link.", 2000);
            }
        }
    }
}
