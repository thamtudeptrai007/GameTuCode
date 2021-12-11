package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.Menu.InfoLabel;
import uet.oop.bomberman.Menu.ViewEndGame;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BombermanGame {

    private static final int defaultNextLevelTime = 100;
    private int cnt = defaultNextLevelTime;
    private int check = 1;

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int FPS = 33;
    public static final long TPF = 1000000000 / FPS;
    public static final int defaultNumberLives = 5;
    public static final int defaultFlameSize = 1;
    public static final int defaultNumberBombs = 1;
    public static final int defaultScore = 0;
    public static final double defaultSPF = 0.11;
    public static final int defaultSpeed = 2;
    public static final int maxNumberLives = 5;
    public static final int maxFlameSizes = 3;
    public static final int maxNumberBombs = 7;
    public static final int maxSpeed = 5;
    public static final boolean hack = false;
    public static final boolean soundBackground = false;
    public static final boolean soundEffects = true;

    public static final int defaultTotalTime = 300;

    public GraphicsContext gc;
    public Canvas canvas;
    private GameMap map;
    public static final Bomber defaultBomber = new Bomber(defaultFlameSize, defaultNumberBombs, defaultNumberLives, defaultScore,
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

        //level1
        InfoLabel level1 = new InfoLabel("Level 1!!!");
        level1.setLayoutX(300);
        level1.setLayoutY(150);
        level1.setTextFill(Color.web("#FFA500"));
        level1.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70));
        //level2
        InfoLabel level2 = new InfoLabel("Level 2!!!");
        level2.setLayoutX(300);
        level2.setLayoutY(150);
        level2.setTextFill(Color.web("#FFA500"));
        level2.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70));
        //level3
        InfoLabel level3 = new InfoLabel("Level 3!!!");
        level3.setLayoutX(300);
        level3.setLayoutY(150);
        level3.setTextFill(Color.web("#FFA500"));
        level3.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 70));

        Text level = new Text("Level: ");
        Text time = new Text("Time: ");
        Text point = new Text("Points:");
        Text lives  = new Text("Lives:");
        level.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 16));
        time.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 16));
        point.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 16));
        lives.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 16));

        HBox hBox = new HBox();
        hBox.setSpacing(40) ;
        hBox.setPadding(new Insets(5,3,3,3));
        hBox.getChildren().addAll(level, time, point, lives);
        hBox.setPrefHeight(20);

        AnchorPane hBox1 = new AnchorPane();
        hBox1.getChildren().add(level1);
        Label rac1 = new Label(".");
        hBox1.getChildren().add(rac1);
        rac1.setLayoutY(500);
        Image backgroundImage = new Image("/resources/deep_blue.png", 256, 256, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        hBox1.setBackground(new Background(background));

        AnchorPane hBox2 = new AnchorPane();
        hBox2.getChildren().add(level2);
        Label rac2 = new Label(".");
        hBox2.getChildren().add(rac2);
        rac2.setLayoutY(500);
        hBox2.setBackground(new Background(background));

        AnchorPane hBox3 = new AnchorPane();
        hBox3.getChildren().add(level3);
        Label rac3 = new Label(".");
        hBox3.getChildren().add(rac3);
        rac3.setLayoutY(500);
        hBox3.setBackground(new Background(background));

        vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(canvas);
        vBox.setStyle("-fx-background-color: lightblue;");


        ToolBar toolBar = new ToolBar();

        // Tao scene
        scene = new Scene(vBox);
        gameStage = new Stage();
        gameStage.setScene(scene);
        gameStage.getIcons().add(new Image("resources/icon.png"));
        Text title = new Text("Level: ");
        gameStage.setTitle("BOMBERMAN GAME JAVA");

        map = new GameMap(scene, gc, canvas,1);
        map.createMap(defaultBomber);

        Sound.playBackground();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (cnt >= 0) {
                    if (check > 0) {
                        check--;
                        vBox.getChildren().removeAll(hBox, canvas);
                        if (map.getLevel() == 1) {
                            vBox.getChildren().add(hBox1);
                            Sound.playStartStage();
                        }
                        if (map.getLevel() == 2) {
                            vBox.getChildren().add(hBox2);
                            Sound.playStartStage();
                        }
                        if (map.getLevel() == 3) {
                            vBox.getChildren().add(hBox3);
                            Sound.playStartStage();
                        }
                    }
                    cnt--;
                    if (cnt == 0) {
                        vBox.getChildren().remove(0);
                        vBox.getChildren().addAll(hBox, canvas);
                    }
                } else {

                    if (now - lastTime >= TPF) {
                        map.render();
                        map.update(now);
                        lastTime = now;

                        Bomber bomber = map.getBomber();

                        level.setText("Level: " + map.getLevel());
                        time.setText("Time: " + map.getTotalTime());
                        point.setText("Points: " + bomber.getScore());
                        lives.setText("Lives: " + bomber.getNumberLives());

                        if (bomber.getNumberLives() == 0 || map.getTotalTime() < 0) {
                            this.stop();
                            ViewEndGame viewEndGame = new ViewEndGame();
                            try {
                                viewEndGame.creatEndGame(gameStage, bomber.getScore(), false);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                        if (map.getPortal()) {
                            Sound.playStartStage();

                            if (map.getLevel() == 3) {
                                ViewEndGame viewEndGame = new ViewEndGame();
                                try {
                                    viewEndGame.creatEndGame(gameStage, bomber.getScore(), true);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                this.stop();
                            }
                            try {
                                defaultBomber.setAll(bomber);
                                map.setTotalTime(defaultTotalTime);
                                map.resetNext();
                                map.nextLevel();
                                map.createMap(bomber);

                                cnt = defaultNextLevelTime;
                                check = 1;

                            } catch (Exception ignored) {
                            }
                        }

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
