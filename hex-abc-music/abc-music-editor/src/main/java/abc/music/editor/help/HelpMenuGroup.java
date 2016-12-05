package abc.music.editor.help;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
class HelpMenuGroup extends JPanel {

    public HelpMenuGroup(HelpDialog parent, XmlNode itemNode) {
        super.setOpaque(false);
        BoxLayout menuGroupLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(menuGroupLayout);
        if (itemNode.hasAttribute("label")) {
            super.add(new HelpMenuHeader(itemNode));
        }
        for (XmlNode childNode : itemNode.getChildren()) {
            if (!childNode.isCdata()) {
                String nodeName = childNode.getName();
                switch (nodeName) {
                    case "item-group":
                        super.add(new HelpMenuGroup(parent, childNode));
                        break;
                    case "item":
                        super.add(new HelpMenuItem(parent, childNode));
                        break;
                }
            }
        }
    }

}
