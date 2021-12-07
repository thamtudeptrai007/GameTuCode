package uet.oop.bomberman.Menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

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

            }
        });
    }

    private void createHelpButton() {
        Menu_Button helpButton = new Menu_Button("HELP");
        AddMenuButtons(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
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
