package nu.hex.abc.music.service.xml;

import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-30
 *
 * @author hl
 */
public class ClassNode {

    private final Class c;

    /**
     *
     * @param c
     */
    public ClassNode(Class c) {
        this.c = c;
    }

    public XmlNode getNode() {
        XmlNode result = NodeFactory.createNode(getNodeName());
        return result;
    }

    public XmlNode getNode(String content) {
        XmlNode result = NodeFactory.createNode(getNodeName(), content);
        return result;
    }

    protected String getNodeName() {
        String[] parts = c.getSimpleName().split("(?=\\p{Upper})");
        int i = 0;
        String result = "";
        for (String s : parts) {
            if (i++ > 0) {
                result += "-";
            }
            result += s.toLowerCase();
        }
        return result;
    }
}
