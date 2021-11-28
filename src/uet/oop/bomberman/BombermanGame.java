package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 24;
    public static final long TPF = 1000000000 / FPS;
    public static final int numberLives = 7;

    public GraphicsContext gc;
    public Canvas canvas;
    public GameMap map;

    private long lastTime;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        map = new GameMap(scene, gc, canvas,1);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastTime >= TPF) {
                    map.render();
                    map.update(now);
                    lastTime = now;
                }
            }
        };
        timer.start();

        map.createMap(numberLives);

        //keyBoard(scene, map.getBomber());
    }

    public void keyBoard(Scene scene, Bomber bomber) {
        scene.setOnKeyPressed(bomber::press);
        /*scene.setOnKeyPressed(keyEvent -> {
            bomber.press(keyEvent);
        });*/
        scene.setOnKeyPressed(bomber::release);
    }
}
