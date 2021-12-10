package uet.oop.bomberman.Menu;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class ViewNextLevel {
    private static final int HEIGHT = 416;
    private static final int WIDTH = 992;
    private AnchorPane nextLevelPane;
    private Scene nextLevelScene;
    private Stage nextLevelStage;

    public final static String FONT_PATH = "/resources/kenvector_future.ttf";

    private void createBackground() {
        Image backgroundImage = new Image("/resources/deep_blue.png", 256, 256, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        nextLevelPane.setBackground(new Background(background));
    }

    public void creatNextLevel() {
        nextLevelPane = new AnchorPane();
        nextLevelScene = new Scene(nextLevelPane, WIDTH, HEIGHT);
        nextLevelStage = new Stage();
        nextLevelStage.setScene(nextLevelScene);

        createBackground();

        nextLevelStage.show();

        //TimeUnit.SECONDS.sleep(2);

        //Thread.sleep(2000);

        //nextLevelStage.hide();
    }

    public Stage getNextLevelStage() {
        return nextLevelStage;
    }
}
