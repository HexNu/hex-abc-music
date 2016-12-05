package abc.music.editor.gui;

import abc.music.editor.AbcMusicEditor;
import abc.music.editor.action.OpenLinkAction;
import abc.music.editor.gui.support.ListItemMouseListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class LinkItem extends JLabel {

    private URI uri;

    public LinkItem(AbcMusicEditor editor, XmlNode node) {
        super.setIcon(new ImageIcon(getClass().getResource("/images/empty-icon.png")));
        super.setText(node.getAttribute("title"));
        StringBuilder toolTip = new StringBuilder("<html>");
        for (String s : node.getText().split("\n")) {
            toolTip.append(s).append("<br>");
        }
        toolTip.append("<br>").append(node.getAttribute("uri"));
        super.setToolTipText(toolTip.toString());
        try {
            this.uri = new URI(node.getAttribute("uri"));
            super.addMouseListener(new ListItemMouseListener() {

                @Override
                protected void doubleClickAction() {
                    new OpenLinkAction(editor, uri).actionPerformed(null);
                }
            });
        } catch (URISyntaxException ex) {
            Logger.getLogger(LinkItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
