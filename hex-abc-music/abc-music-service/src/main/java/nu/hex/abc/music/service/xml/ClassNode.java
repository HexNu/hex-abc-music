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
        return NodeFactory.createNode(getNodeName());
    }

    public XmlNode getCollectionNode() {
        return NodeFactory.createNode(getCollectionNodeName());
    }

    public XmlNode getNode(String content) {
        return NodeFactory.createNode(getNodeName(), content);
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

    private String getCollectionNodeName() {
        String result = getNodeName();
        if (result.endsWith("y") && !result.endsWith("ey")) {
            return result.substring(0, result.lastIndexOf("y")) + "ies";
        }
        return result += "s";
    }
}
