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
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.IOException;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 24;
    public static final long TPF = 1000000000 / FPS;
    public static final int defaultNumberLives = 7;
    public static final int defaultFlameSize = 1;
    public static final int defaultNumberBombs = 1;
    public static final int defaultScore = 0;

    public GraphicsContext gc;
    public Canvas canvas;
    private GameMap map;
    private final Bomber bomber = new Bomber(defaultFlameSize, defaultNumberBombs, defaultNumberLives, defaultScore);

    private long lastTime;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
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
        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(vBox);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

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
                    level.setText("Level: " + String.valueOf(map.getLevel()));
                    time.setText("Time: " + String.valueOf(map.getTotalTime()));
                    point.setText("Points: " + String.valueOf(bomber1.getScore()));
                    lives.setText("Lives: " + String.valueOf(bomber1.getNumberLives()));

                }

            }
        };
        timer.start();
    }
}
