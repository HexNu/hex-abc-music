package abc.music.app;

import abc.music.core.domain.Project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import nu.hex.abc.music.service.Service;

/**
 * Created 2018-jan-24
 *
 * @author hl
 */
public class EditorApp extends Application {

    private static final String APP_LABEL = "Hex ABC Music Notes Editor";
    private Service service;
    private Project project;
    private Stage stage;
    private MenuBar menuBar;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        initUI();
    }

    private void initUI() {
        setupScene(stage);
        stage.show();
    }

    public Service getService() {
        return service;
    }

    public Project getProject() {
        return project;
    }

    private void setupScene(Stage currentStage) {
        root = new BorderPane();
        Scene scene = new Scene(root, 1900, 1100);
        currentStage.setTitle(APP_LABEL);
        currentStage.setScene(scene);
        scene.getStylesheets().add("/styles/styles.css");
    }

}
