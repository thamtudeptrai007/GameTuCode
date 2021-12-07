package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.ViewManager;

public class Main  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            ViewManager viewManager = new ViewManager();
            stage = viewManager.getMainStage();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
