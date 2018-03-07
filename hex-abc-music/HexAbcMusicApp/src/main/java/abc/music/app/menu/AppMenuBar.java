package abc.music.app.menu;

import abc.music.app.EditorApp;
import abc.music.app.action.BackupProjectAction;
import abc.music.app.action.ExitAction;
import abc.music.app.action.SaveProjectAction;
import abc.music.app.control.AppInfoAlert;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import nu.hex.abc.music.service.meta.AppInfo;
import nu.hex.abc.music.service.properties.AbcMusicProperties;

/**
 * Created 2017-okt-27
 *
 * @author hl
 */
public class AppMenuBar extends MenuBar {

    private final EditorApp app;

    public AppMenuBar(EditorApp app) {
        this.app = app;
        init();
    }

    private void init() {
        Menu fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);
        MenuItem openItem = new MenuItem("Open Collection");
        MenuItem saveItem = new MenuItem("Save Collection");
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveItem.setOnAction((ActionEvent event) -> {
            new SaveProjectAction(app).actionPerformed();
        });
        MenuItem backupItem = new MenuItem("Save Backup of Collection");
        backupItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        backupItem.setOnAction((ActionEvent event) -> {
            new BackupProjectAction(app).actionPerformed();
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        exitItem.setOnAction((ActionEvent event) -> {
            new ExitAction(app).actionPerformed();
        });
        fileMenu.getItems().addAll(openItem, saveItem, backupItem, new SeparatorMenuItem(), exitItem);

        Menu libraryMenu = new Menu("_Library");
        libraryMenu.setMnemonicParsing(true);
//        manager.getService().getLibraryFolder().ifPresent((libFolder) -> {
//            new File2FileMenu(manager, libraryMenu, libFolder).process();
//        });

        Menu helpMenu = new Menu("_Help");
        helpMenu.setMnemonicParsing(true);
        Alert about = createAboutDialog();
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction((ActionEvent event) -> {
            about.showAndWait();
        });
        helpMenu.getItems().addAll(aboutItem);
        getMenus().addAll(fileMenu, libraryMenu, helpMenu);
    }

    private Alert createAboutDialog() {
        AppInfoAlert about = new AppInfoAlert(app, "About");
        about.setHeader("Hex Role Playing Game EditorApp");
        about.setContent(getInfoText());
        return about;
    }

    private StringBuilder getInfoText() {
        AbcMusicProperties properties = AbcMusicProperties.getInstance();
        AppInfo appInfo = AppInfo.getInstance();
        StringBuilder result = new StringBuilder("Product Version: ").append(appInfo.getName()).append(" ").append(appInfo.getVersion()).append("\n");
        result.append("Vendor: ").append(appInfo.getVendor()).append("\n");
        result.append("Last Build: ").append(appInfo.getLastBuild()).append("\n");
//        result.append("Project Directory: ").append(properties.getProperty(RpgEditorAppProperties.CAMPAIGN_FOLDER)).append("\n");
//        result.append("Backup Directory: ").append(properties.getProperty(RpgEditorAppProperties.BACKUP_FOLDER)).append("\n");
//        result.append("Image Directory: ").append(properties.getProperty(RpgEditorAppProperties.IMAGE_FOLDER)).append("\n");
//        result.append("Last Edited Project: \"").append(properties.getProperty(RpgEditorAppProperties.LATEST_SAVED_CAMPAIGN)).append("\"\n");
        result.append("Auto Open Latest Project: ").append(properties.getPropertyAsBoolean("auto-open-project") ? "on" : "off").append("\n").toString();
        result.append("Keep Editor History: ").append(properties.getPropertyAsBoolean("preserve-project-history") ? "on" : "off").append("\n").toString();
        return result;
    }
}
