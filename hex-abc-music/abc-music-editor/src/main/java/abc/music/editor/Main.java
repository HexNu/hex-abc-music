package abc.music.editor;

import java.io.File;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("/home/hl/Skrivbord");
        System.out.println(file.getAbsolutePath());
//        InputStream resourceAsStream = Main.class.getResourceAsStream("/help/help.xml");
//        XmlNode helpRoot = new DocumentToXmlNodeParser(resourceAsStream).parse();
//        System.out.println(helpRoot);
    }

}
