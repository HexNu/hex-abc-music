package nu.hex.abc.music.service.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.hex.abc.music.service.io.SimpleFileWriter;
import se.digitman.lightxml.DocumentToXmlNodeParser;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 * Created 2016-nov-17
 *
 * @author hl
 */
public class AbcMusicProperties {

    private static final String APPLICATION_ROOT_FOLDER = System.getProperty("user.home") + "/.hrm/";
    private static final String PUBLIC_APPLICATION_FOLDER = System.getProperty("user.home") + "/HexRpgManager/";
    public static final String SETTINGS_FOLDER = APPLICATION_ROOT_FOLDER + "settings/";
    private static final String SETTINGS_FILE = SETTINGS_FOLDER + "settings.xml";
    private static final String DEFAULT_PROJECT_FOLDER = APPLICATION_ROOT_FOLDER + "projects/";
    private static final String DEFAULT_BACKUP_FOLDER = APPLICATION_ROOT_FOLDER + "backup/";
    private static final String DEFAULT_IMAGE_FOLDER = PUBLIC_APPLICATION_FOLDER + "Images/";
    public static final String PROJECT_FOLDER = "project-folder";
    public static final String BACKUP_FOLDER = "backup-folder";
    public static final String IMAGE_FOLDER = "image-folder";
    public static final String LATEST_SAVED_PROJECT = "latest-saved-project";
    private final Map<String, String> defaultSettingsMap = new HashMap<>();
    private File settingsFile;
    private XmlNode settingsNode;
    private static final AbcMusicProperties properties = new AbcMusicProperties();

    private AbcMusicProperties() {
        init();
    }

    public static AbcMusicProperties getInstance() {
        return properties;
    }

    private void init() {
        defaultSettingsMap.put(PROJECT_FOLDER, DEFAULT_PROJECT_FOLDER);
        defaultSettingsMap.put(BACKUP_FOLDER, DEFAULT_BACKUP_FOLDER);
        defaultSettingsMap.put(IMAGE_FOLDER, DEFAULT_IMAGE_FOLDER);
        try {
            new File(APPLICATION_ROOT_FOLDER).mkdirs();
            new File(SETTINGS_FOLDER).mkdir();
            new File(DEFAULT_PROJECT_FOLDER).mkdir();
            new File(DEFAULT_BACKUP_FOLDER).mkdir();
            new File(DEFAULT_IMAGE_FOLDER).mkdirs();
            settingsFile = new File(SETTINGS_FILE);
            if (!settingsFile.exists()) {
                createSettingsFile();
            }
            settingsNode = new DocumentToXmlNodeParser(new FileInputStream(settingsFile)).parse();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbcMusicProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String propKey) {
        if (settingsNode == null) {
            if (defaultSettingsMap.containsKey(propKey)) {
                return defaultSettingsMap.get(propKey);
            }
        } else {
            for (XmlNode settingNode : settingsNode.getChildren("setting")) {
                if (settingNode.hasAttribute("key") && settingNode.getAttribute("key").equals(propKey)) {
                    return settingNode.getText();
                }
            }
        }
        return null;
    }

    public Integer getPropertyAsInteger(String propKey) {
        try {
            return Integer.valueOf(getProperty(propKey));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Double getPropertyAsDouble(String propKey) {
        try {
            return Double.valueOf(getProperty(propKey));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean getPropertyAsBoolean(String propKey) {
        return Boolean.valueOf(getProperty(propKey));
    }

    public Map<String, String> getProperties() {
        Map<String, String> result = new HashMap<>();
        settingsNode.getChildren("setting").stream().forEach((settingNode) -> {
            result.put(settingNode.getAttribute("key"), settingNode.getText());
        });
        return result;
    }

    public void setProperty(String propKey, String value) {
        boolean notExists = true;
        for (XmlNode settingNode : settingsNode.getChildren("setting")) {
            if (settingNode.getAttribute("key").equals(propKey)) {
                settingNode.clearText();
                settingNode.addText(value);
                notExists = false;
            }
        }
        if (notExists) {
            addSettingNode(value, propKey);
        }
        writeToSettingsFile();
    }

    private void addSettingNode(String value, String propKey) {
        XmlNode settingNode = NodeFactory.createNode("setting", value);
        settingNode.addAttribute("key", propKey);
        settingsNode.addChild(settingNode);
    }

    private void writeToSettingsFile() {
        XmlDocument settingsDoc = new XmlDocument(settingsNode);
        new SimpleFileWriter(settingsFile, settingsDoc.toString()).write();
    }

    private void createSettingsFile() {
        settingsNode = NodeFactory.createNode("settings");
        addSettingNode(DEFAULT_PROJECT_FOLDER, PROJECT_FOLDER);
        addSettingNode(DEFAULT_BACKUP_FOLDER, BACKUP_FOLDER);
        addSettingNode(DEFAULT_IMAGE_FOLDER, IMAGE_FOLDER);
        writeToSettingsFile();
    }
}
