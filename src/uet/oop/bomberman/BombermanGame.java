package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.Menu.InfoLabel;
import uet.oop.bomberman.Menu.ViewEndGame;
import uet.oop.bomberman.Menu.ViewManager;
import uet.oop.bomberman.Menu.ViewNextLevel;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BombermanGame {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 33;
    public static final long TPF = 1000000000 / FPS;
    public static final int defaultNumberLives = 3;
    public static final int defaultFlameSize = 1;
    public static final int defaultNumberBombs = 1;
    public static final int defaultScore = 0;
    public static final double defaultSPF = 0.11;
    public static final int defaultSpeed = 2;
    public static final int maxNumberLives = defaultNumberLives;
    public static final int maxFlameSizes = 3;
    public static final int maxNumberBombs = 7;
    public static final int maxSpeed = 5;
    public static final boolean hack = false;

    public static final int defaultTotalTime = 300;

    public GraphicsContext gc;
    public Canvas canvas;
    private GameMap map;
    private final Bomber bomber = new Bomber(defaultFlameSize, defaultNumberBombs, defaultNumberLives, defaultScore,
                                                                defaultSPF, defaultSpeed, hack);

    private long lastTime;

    private Scene scene;
    private VBox vBox;
    private Stage gameStage;
    private Stage menuStage;
    public final static String FONT_PATH = "/resources/kenvector_future.ttf";

    int temp = -1;

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
        //level.setFont(Font.loadFont("Verdana", 20));
        //time.setFont(Font.loadFont("Verdana", 18));
        //point.setFont(Font.loadFont("Verdana", 18));
        //lives.setFont(Font.loadFont("Verdana", 18));

        HBox hBox = new HBox();
        hBox.setSpacing(40) ;
       // hBox.setPadding(new Insets(5,3,3,3));
        hBox.getChildren().addAll(level, time, point, lives);
        //hBox.setPrefHeight(20);
        vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(canvas);
        //vBox.setStyle("-fx-background-color: grey;");

        ToolBar toolBar = new ToolBar();

        // Tao scene
        scene = new Scene(vBox);
        gameStage = new Stage();
        gameStage.setScene(scene);
        gameStage.getIcons().add(new Image("resources/icon.png"));
        Text title = new Text("Level: ");
        gameStage.setTitle("BOMBERMAN GAME JAVA");

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
                    //System.out.println(bomber1.isHack());
                    level.setText("Level: " + map.getLevel());
                    time.setText("Time: " + map.getTotalTime());
                    point.setText("Points: " + bomber1.getScore());
                    lives.setText("Lives: " + bomber1.getNumberLives());

                    if (bomber1.getNumberLives() == 0 || map.getTotalTime() < 0) {
                        this.stop();
                        ViewEndGame viewEndGame = new ViewEndGame();
                        try {
                            viewEndGame.creatEndGame(gameStage, bomber1.getScore());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    if (map.getPortal()) {
                        Sound.playStartStage();
                        try {
                            map.setTotalTime(defaultTotalTime);
                            map.setNext(1);
                            map.nextLevel();
                            map.createMap(bomber1);

                            //System.out.println("next level");

                        } catch (Exception ignored) {}
                    }

                }
            }
        };
        timer.start();
    }

    public Stage getGameStage() {
        return gameStage;
    }

}
