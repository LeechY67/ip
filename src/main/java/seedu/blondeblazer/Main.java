package seedu.blondeblazer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.blondeblazer.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/seedu.blondeblazer.BlondeBlazer.txt";
    private BlondeBlazer blondeBlazer = new BlondeBlazer(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlondeBlazer(blondeBlazer);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
