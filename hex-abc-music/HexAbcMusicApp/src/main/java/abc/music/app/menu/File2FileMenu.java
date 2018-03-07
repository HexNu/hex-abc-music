package abc.music.app.menu;

import abc.music.app.EditorApp;
import abc.music.app.action.OpenSystemFileAction;
import abc.music.core.util.FileSize;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import nu.hex.mediatype.MediaTypeIdentifier;

/**
 * Created 2017-sep-28
 *
 * @author hl
 */
public class File2FileMenu {

    private final File file;
    private final EditorApp manager;
    private final Menu menu;

    public File2FileMenu(EditorApp manager, Menu menu, File file) {
        this.manager = manager;
        this.menu = menu;
        this.file = file;
    }

    public Menu process() {
        crawl(menu, file);
        return menu;
    }

    private Menu crawl(Menu currentMenu, File folder) {
        List<File> allFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));
        allFiles.sort((a, b) -> a.getAbsolutePath().compareTo(b.getAbsolutePath()));
        List<File> fileList = new ArrayList<>();
        List<File> folderList = new ArrayList<>();
        allFiles.forEach((f) -> {
            if (f.isDirectory()) {
                folderList.add(f);
            } else {
                fileList.add(f);
            }
        });
        fileList.forEach((f) -> {
            String name = f.getName();
            Label label = new Label(name.substring(0, name.lastIndexOf(".")));
            CustomMenuItem fileItem = new CustomMenuItem(label);
            StringBuilder fileInfo = new StringBuilder();
            fileInfo.append("File Name: \t").append(f.getName()).append("\n");
            fileInfo.append("File Size: \t\t").append(new FileSize(f.length()).get()).append(" - (").append(f.length()).append(" bytes)").append("\n");
            fileInfo.append("File Path: \t\t").append(f.getAbsolutePath()).append("\n");
            Iterator mediaType = new MediaTypeIdentifier()
                    .getMediaTypeByFileSuffix(f.getName().substring(name.lastIndexOf(".") + 1)).iterator();
            if (mediaType.hasNext()) {
                fileInfo.append("Media Type: \t").append(mediaType.next());
            }
            Tooltip tooltip = new Tooltip(fileInfo.toString());
            Tooltip.install(label, tooltip);
            fileItem.setOnAction((ActionEvent e) -> {
                new OpenSystemFileAction(manager, f).actionPerformed();
            });
            currentMenu.getItems().add(fileItem);
        });
        if (!folderList.isEmpty() && !fileList.isEmpty()) {
            currentMenu.getItems().add(new SeparatorMenuItem());
        }
        folderList.forEach((f) -> {
            Menu childMenu = new Menu(f.getName());
            currentMenu.getItems().add(crawl(childMenu, f));
        });
        return currentMenu;
    }
}
