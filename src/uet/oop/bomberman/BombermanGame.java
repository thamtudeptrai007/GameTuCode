package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.Menu.ViewManager;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.IOException;

public class BombermanGame {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 33;
    public static final long TPF = 1000000000 / FPS;
    public static final int defaultNumberLives = 7;
    public static final int defaultFlameSize = 1;
    public static final int defaultNumberBombs = 1;
    public static final int defaultScore = 0;
    public static final double defaultSPF = 0.11;
    public static final int defaultSpeed = 2;
    public static final int maxNumberLives = defaultNumberLives;
    public static final int maxFlameSizes = 3;
    public static final int maxNumberBombs = 7;
    public static final int maxSpeed = 5;

    public GraphicsContext gc;
    public Canvas canvas;
    private GameMap map;
    private final Bomber bomber = new Bomber(defaultFlameSize, defaultNumberBombs, defaultNumberLives, defaultScore,
                                                                defaultSPF, defaultSpeed);

    private long lastTime;

    private Scene scene;
    private VBox vBox;
    private Stage gameStage;
    private Stage menuStage;


    public Scene getScene() {
        return scene;
    }

    public void creatNewGame(Stage menuStage) throws IOException {
        init();

        try {
            gameStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.menuStage = menuStage;
        this.menuStage.hide();

    }

    public void init() throws IOException {

        // Tao Canvas
        canvas = new Canvas(Sprite.DEFAULT_SIZE * WIDTH, Sprite.DEFAULT_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        //dung

        Text level = new Text("Level: ");
        Text time = new Text("Time: ");
        Text point = new Text("Points:");
        Text lives  = new Text("Lives:");

        HBox hBox = new HBox();
        hBox.setSpacing(40) ;
        hBox.getChildren().addAll(level, time, point, lives);
        vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(canvas);

        // Tao scene
        scene = new Scene(vBox);
        gameStage = new Stage();
        gameStage.setScene(scene);
        map = new GameMap(scene, gc, canvas,1);

        map.createMap(bomber);
        //Sound.playBackground();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastTime >= TPF) {
                    map.render();
                    map.update(now);
                    lastTime = now;
                    Bomber bomber1 = map.getBomber();
                    level.setText("Level: " + map.getLevel());
                    time.setText("Time: " + map.getTotalTime());
                    point.setText("Points: " + bomber1.getScore());
                    lives.setText("Lives: " + bomber1.getNumberLives());

                }
            }
        };
        timer.start();
    }
}
