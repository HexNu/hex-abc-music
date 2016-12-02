package nu.hex.abc.music.service.xml.write;

import abc.music.core.domain.Field;
import nu.hex.abc.music.service.io.Writer;
import nu.hex.abc.music.service.xml.ClassNode;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-dec-01
 *
 * @author hl
 * @param <T>
 */
public abstract class XmlWriter<T> implements Writer<XmlNode> {

    protected final T entity;
    protected final XmlNode result;

    public XmlWriter(T entity) {
        this.entity = entity;
        this.result = new ClassNode(entity.getClass()).getNode();
    }

    protected void addSimpleNode(XmlNode collectionNode, Field f) {
        collectionNode.addChild(new ClassNode(f.getClass()).getNode(f.getContent()));
    }

//    
//
//    protected void addSimpleNode(XmlNode parentNode, Field field) {
//        result.addChild(getSimpleNode(field));
//        parentNode.addChild(result);
//    }
//
//    protected XmlNode getSimpleNode(Field field) {
//        return new ClassNode(field.getClass()).getNode(field.getContent());
//    }
}
