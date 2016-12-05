package abc.music.editor;

import java.io.InputStream;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        InputStream resourceAsStream = Main.class.getResourceAsStream("/help/help.xml");
        XmlNode helpRoot = new DocumentToXmlNodeParser(resourceAsStream).parse();
        System.out.println(helpRoot);
    }

}
