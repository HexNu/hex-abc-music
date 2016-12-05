package abc.music.editor.help;

import abc.music.editor.AmeConstants;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
class HelpMenuHeader extends JLabel {

    public HelpMenuHeader(XmlNode itemNode) {
        super(itemNode.getAttribute("label"));
        super.setBorder(new EmptyBorder(12, 0, 0, 0));
        if (itemNode.hasAttribute("summary")) {
            super.setToolTipText(itemNode.getAttribute("summary"));
        }
        super.setFont(AmeConstants.MENU_HEADER_FONT);
        super.setForeground(AmeConstants.MENU_HEADER_COLOR);
    }

}
