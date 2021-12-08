package uet.oop.bomberman.Menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Support.Sound;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int HEIGHT = 416;
    private static final int WIDTH = 992;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 80;

    public final static String FONT_PATH = "/resources/kenvector_future.ttf";
    
    private Menu_subScene creditsSubscene;
    private Menu_subScene helpSubscene;
    private Menu_subScene scoreSubscene;


    private Menu_subScene sceneToHide;


    List<Menu_Button> menuButtons = new ArrayList<>();

    public ViewManager() throws IOException {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createBackground();
        CreateButtons();
        createLogo();

    }

    private void showSubScene(Menu_subScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    private void createSubScenes() {

        creditsSubscene = new Menu_subScene();
        mainPane.getChildren().add(creditsSubscene);
        helpSubscene = new Menu_subScene();
        mainPane.getChildren().add(helpSubscene);
        scoreSubscene = new Menu_subScene();
        mainPane.getChildren().add(scoreSubscene);

        creatCreditsSubscene();
        creatHelpSubscene();

    }

    private void creatCreditsSubscene() {
        creditsSubscene = new Menu_subScene();
        mainPane.getChildren().add(creditsSubscene);

        InfoLabel creditsLabel = new InfoLabel("ABOUT US:");
        InfoLabel lb_cr_line1 = new InfoLabel("1 . LE VAN HUY - K65 C-CLC");
        InfoLabel lb_cr_line2 = new InfoLabel("2. LE NGOC MINH -  K65 CD");
        InfoLabel lb_cr_line3 = new InfoLabel("3. NGUYEN VAN DUNG - K65 CD");
        InfoLabel lb_cr_line4 = new InfoLabel("INSTRUCTOR: MISS HUYEN");

        creditsLabel.setLayoutX(200);
        creditsLabel.setLayoutY(30);

        lb_cr_line1.setLayoutX(60);
        lb_cr_line1.setLayoutY(80);
        lb_cr_line2.setLayoutX(60);
        lb_cr_line2.setLayoutY(120);
        lb_cr_line3.setLayoutX(60);
        lb_cr_line3.setLayoutY(160);
        lb_cr_line4.setLayoutX(80);
        lb_cr_line4.setLayoutY(220);

        creditsSubscene.getPane().getChildren().add(creditsLabel);
        creditsSubscene.getPane().getChildren().add(lb_cr_line1);
        creditsSubscene.getPane().getChildren().add(lb_cr_line2);
        creditsSubscene.getPane().getChildren().add(lb_cr_line3);
        creditsSubscene.getPane().getChildren().add(lb_cr_line4);
    }

    private void creatHelpSubscene() {
        helpSubscene = new Menu_subScene();
        mainPane.getChildren().add(helpSubscene);

        InfoLabel creditsLabel = new InfoLabel("HOW TO PLAY:");
        InfoLabel lb_cr_line1 = new InfoLabel("- PRESS LEFT, RIGHT, UP, DOWN TO MOVE");
        InfoLabel lb_cr_line2 = new InfoLabel("- PRESS SPACEBAR TO SET BOM");
        InfoLabel lb_cr_line3 = new InfoLabel("- KILL ALL ENEMY ON THE PLAY SCREEN");
        InfoLabel lb_cr_line4 = new InfoLabel("- MOVE TO THE POTAL TO NEXT LEVEL ");

        creditsLabel.setLayoutX(180);
        creditsLabel.setLayoutY(30);

        lb_cr_line1.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20));
        lb_cr_line2.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20));
        lb_cr_line3.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20));
        lb_cr_line4.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20));

        lb_cr_line1.setLayoutX(25);
        lb_cr_line1.setLayoutY(80);
        lb_cr_line2.setLayoutX(25);
        lb_cr_line2.setLayoutY(120);
        lb_cr_line3.setLayoutX(25);
        lb_cr_line3.setLayoutY(160);
        lb_cr_line4.setLayoutX(25);
        lb_cr_line4.setLayoutY(200);

        helpSubscene.getPane().getChildren().add(creditsLabel);
        helpSubscene.getPane().getChildren().add(lb_cr_line1);
        helpSubscene.getPane().getChildren().add(lb_cr_line2);
        helpSubscene.getPane().getChildren().add(lb_cr_line3);
        helpSubscene.getPane().getChildren().add(lb_cr_line4);
    }

    private void AddMenuButtons(Menu_Button button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 65);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    public Stage getMainStage() {
        return mainStage;
    }


    private void CreateButtons() throws IOException {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() throws IOException {
        Menu_Button startButton = new Menu_Button("PLAY");
        AddMenuButtons(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sound.playMouseClick();
                //Sound.playMouseRelease();
                BombermanGame bombermanGame = new BombermanGame();
                try {
                    bombermanGame.creatNewGame(mainStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void createScoresButton() {
        Menu_Button scoresButton = new Menu_Button("SCORES");
        AddMenuButtons(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubscene);
                Sound.playMouseClick();
            }
        });
    }

    private void createHelpButton() {
        Menu_Button helpButton = new Menu_Button("HELP");
        AddMenuButtons(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Sound.playMouseClick();
                showSubScene(helpSubscene);
            }
        });
    }

    private void createCreditsButton() {

        Menu_Button creditsButton = new Menu_Button("CREDITS");
        AddMenuButtons(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubscene);
                Sound.playMouseClick();
            }
        });
    }

    private void createExitButton() {
        Menu_Button exitButton = new Menu_Button("EXIT");
        AddMenuButtons(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
                Sound.playMouseClick();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("/resources/deep_blue.png", 256, 256, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
    private void createLogo() {
        ImageView logo = new ImageView("/resources/boomberman.png");
        logo.setLayoutX(750);
        logo.setLayoutY(-10);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());

            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);

            }
        });

        mainPane.getChildren().add(logo);

    }
}
