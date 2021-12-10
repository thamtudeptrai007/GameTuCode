package uet.oop.bomberman.Menu;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.Support.Sound;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;

import java.io.*;
import java.util.Scanner;

public class ViewEndGame {
    private static final int HEIGHT = 416;
    private static final int WIDTH = 992;
    private AnchorPane endPane;
    private Scene endScene;
    private Stage endStage;

    public final static String FONT_PATH = "/resources/kenvector_future.ttf";

    /*public void ViewEndGame() {
        endPane = new AnchorPane();
        endScene = new Scene(endPane, WIDTH, HEIGHT);
        endStage = new Stage();
        endStage.setScene(endScene);

        createBackground();
        creatText();
        creatButton();
    }*/


    private void creatButton() {
        Menu_Button bt_submit = new Menu_Button("Submit");
        bt_submit.setLayoutX(410);
        bt_submit.setLayoutY(260);
        endPane.getChildren().add(bt_submit);

        bt_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ViewManager viewManager = null;
                Sound.playMouseClick();
                try {
                    viewManager = new ViewManager();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewManager.getMainStage().show();
                endStage.hide();
            }
        });
    }

    private void creatText(int point, boolean winLose) {
        InfoLabel line1 = new InfoLabel("");
        if (winLose) {
            line1.setText("You Won!!!");
        }
        else {
            line1.setText("Game Over");
        }
            InfoLabel line2 = new InfoLabel("Your scores: " + point);

        line1.setLayoutX(300);
        line1.setLayoutY(100);

        line2.setLayoutX(390);
        line2.setLayoutY(180);

        line1.setTextFill(Color.web("#FFA500"));
        line2.setTextFill(Color.web("#FFA500"));

        line1.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 55));
        line2.setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 20));
        endPane.getChildren().add(line1);
        endPane.getChildren().add(line2);
    }

    public Stage getEndStage() {
        return endStage;
    }

    private void createBackground() {
        Image backgroundImage = new Image("/resources/deep_blue.png", 256, 256, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        endPane.setBackground(new Background(background));
    }

    private Stage menuStage;

    public void creatEndGame(Stage menuStage, int point, boolean winLose) throws FileNotFoundException {
        endPane = new AnchorPane();
        endScene = new Scene(endPane, WIDTH, HEIGHT);
        endStage = new Stage();
        endStage.setScene(endScene);

        endStage.getIcons().add(new Image("resources/icon.png"));
        endStage.setTitle("BOMBERMAN GAME");

        //luu diem vao file


        String[] a = new String[5];
        try {
            FileReader fr = new FileReader("res/levels/score.txt");
            BufferedReader br = new BufferedReader(fr);
            File file = new File("res/levels/score.txt");
            Scanner sc = new Scanner(file);
            for (int i = 0; i< 5; i++) {
                String temp =  br.readLine();//Integer.valueOf(br.readLine().trim());
                a[i] = temp;
                //System.out.println(a[i]+"//");
                int ss = Integer.parseInt(a[i]);
                //System.out.println(ss+1);
            }
            br.close();
            fr.close();
        } catch (Exception e) {}


        for (int i = 0; i< 5; i++) {
            int ss = Integer.parseInt(a[i]);
            if (point > ss) {
                for (int j = 4; j >= i + 1; j--){
                    a[j] = a[j-1];
                }
                a[i] = String.valueOf(point);
                break;
            }
        }

        try {
            FileWriter fw = new FileWriter("res/levels/score.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 5; i++ ) {
                bw.write(a[i]);
                bw.write("\n");
            }
            bw.close();
            fw.close();
        } catch (Exception ignored){}



        createBackground();
        creatButton();
        creatText(point, winLose);

        endStage.show();
        this.menuStage = menuStage;
        this.menuStage.hide();

    }
}
