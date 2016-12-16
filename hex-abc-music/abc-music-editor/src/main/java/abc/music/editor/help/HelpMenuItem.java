package abc.music.editor.help;

import abc.music.editor.gui.support.ListItemMouseListener;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-05
 *
 * @author hl
 */
class HelpMenuItem extends JLabel {

    private final String content;
    private final HelpDialog parent;

    public HelpMenuItem(HelpDialog parent, XmlNode itemNode) {
        super(itemNode.getAttribute("label"));
        super.setBorder(new EmptyBorder(1, 6, 0, 0));
        if (itemNode.hasAttribute("summary")) {
            super.setToolTipText(itemNode.getAttribute("summary"));
        }
        super.addMouseListener(new ListItemMouseListener() {
            @Override
            protected void clickAction() {
                displayHelp();
            }
        });

        XmlNode htmlNode = itemNode.getChild("content").getChild("html");
        if (itemNode.hasAttribute("summary")) {
            XmlNode summaryNode = NodeFactory.createNode("<p>");
            summaryNode.addChild(NodeFactory.createNode("i", itemNode.getAttribute("summary")));
            htmlNode.addChild(0, summaryNode);
        }
        htmlNode.addChild(0, NodeFactory.createNode("h2", itemNode.getAttribute("label")));
        content = htmlNode.toString();
        this.parent = parent;
    }

    private void displayHelp() {
        parent.setContent(content);
    }
}
