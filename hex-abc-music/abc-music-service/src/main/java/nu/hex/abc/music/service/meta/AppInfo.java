package nu.hex.abc.music.service.meta;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-28
 *
 * @author hl
 */
public class AppInfo {

    private static final AppInfo INSTANCE = new AppInfo();
    private final XmlNode infoNode;

    private AppInfo() {
        InputStream infoStream = AppInfo.class.getResourceAsStream("/app-info.xml");
        infoNode = new DocumentToXmlNodeParser(infoStream).parse();
    }

    public static AppInfo getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return infoNode.getChild("name").getText();
    }
    
    public String getVendor() {
        return infoNode.getChild("vendor").getText();
    }
    public String getVersion() {
        return infoNode.getChild("version").getText();
    }
    public LocalDateTime getLastBuild() {
        return LocalDateTime.parse(infoNode.getChild("last-build").getText(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
