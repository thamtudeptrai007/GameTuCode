package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.ViewEndGame;
import uet.oop.bomberman.Menu.ViewManager;

public class Main  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            ViewManager viewManager = new ViewManager();
            stage = viewManager.getMainStage();

            //ViewEndGame viewManager = new ViewEndGame();
            //stage = viewManager.getEndStage();

            //ViewEndGame viewEndGame = new ViewEndGame();
            //stage = viewEndGame.getEndStage();

            stage.getIcons().add(new Image("resources/icon.png"));
            stage.setTitle("BOMBERMAN GAME");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
