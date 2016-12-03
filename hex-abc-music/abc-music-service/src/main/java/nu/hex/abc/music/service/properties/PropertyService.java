package nu.hex.abc.music.service.properties;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class PropertyService {

    private static final AbcMusicProperties PROPERTIES = new AbcMusicProperties();
    public static final String PROJECT_PATH = PROPERTIES.getProperty(AbcMusicProperties.PROJECT_FOLDER);
    public static final String BACKUP_PATH = PROPERTIES.getProperty(AbcMusicProperties.BACKUP_FOLDER);
    public static final String ABC_PATH = PROPERTIES.getProperty(AbcMusicProperties.ABC_FOLDER);
    public static final String LATEST_SAVED_PROJECT = "latest-saved-project";

    public static AbcMusicProperties getProperties() {
        return PROPERTIES;
    }

    public void setProperty(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
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
}
