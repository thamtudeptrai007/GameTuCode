package uet.oop.bomberman.Map;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Balloom;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Oneal;
import uet.oop.bomberman.entities.StaticObject.Grass;
import uet.oop.bomberman.entities.StaticObject.Items.BombItem;
import uet.oop.bomberman.entities.StaticObject.Items.FlameItem;
import uet.oop.bomberman.entities.StaticObject.Items.Portal;
import uet.oop.bomberman.entities.StaticObject.Items.SpeedItem;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    private Scene scene;
    private GraphicsContext gc;
    private Canvas canvas;
    private int level;
    private int height;
    private int width;
    private char[][] map;
    private List<Entity> entities = new ArrayList<Entity>();
    //private List<Entity> stillObjects = new ArrayList<Entity>();

    public GameMap(Scene scene, GraphicsContext gc, Canvas canvas, int level) {
        this.scene = scene;
        this.gc = gc;
        this.canvas = canvas;
        this.level = level;
    }

    public void createMap(int numberLives) throws IOException {
        entities.clear();
        FileInputStream fis = new FileInputStream("res/levels/Level" + level + ".txt");
        Scanner scanner = new Scanner(fis);
        level = scanner.nextInt();
        height = scanner.nextInt();
        width = scanner.nextInt();
        map = new char[width][height];
        scanner.nextLine();
        String line;
        for (int i = 0; i < height; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++)
                map[j][i] = line.charAt(j);
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Entity object;
                if (j == 0 || j == height - 1 || i == 0 || i == width - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                entities.add(object);
            }
        }

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Entity object = null;
                switch (map[x][y]) {
                    case '#':
                        object = new Wall(x, y, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'x':
                        entities.add(new Portal(x, y, Sprite.portal.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'b':
                        entities.add(new BombItem(x, y, Sprite.powerup_bombs.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'f':
                        entities.add(new FlameItem(x, y, Sprite.powerup_flames.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 's':
                        entities.add(new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()));
                        object = new Brick(x, y, Sprite.brick.getFxImage());
                        break;
                    case 'p':
                        Bomber bomber = new Bomber(x, y, Sprite.player_right.getFxImage());
                        bomber.setMoveAnimation(Direction.RIGHT, Animation.player_right.getFxImages());
                        bomber.setMoveAnimation(Direction.LEFT, Animation.player_left.getFxImages());
                        bomber.setMoveAnimation(Direction.UP, Animation.player_up.getFxImages());
                        bomber.setMoveAnimation(Direction.DOWN, Animation.player_down.getFxImages());
                        bomber.setDeadAnimation(Animation.player_dead.getFxImages());
                        bomber.setNumberLives(numberLives);
                        object = bomber;
                        scene.setOnKeyPressed(bomber::press);
                        scene.setOnKeyReleased(bomber::release);
                        break;
                    case '1':
                        Balloom balloom = new Balloom(x, y, Animation.balloom_left.getFxImages());
                        balloom.setMoveAnimation(Direction.RIGHT, Animation.balloom_right.getFxImages());
                        balloom.setMoveAnimation(Direction.LEFT, Animation.balloom_left.getFxImages());
                        balloom.setMoveAnimation(Direction.UP, Animation.balloom_right.getFxImages());
                        balloom.setMoveAnimation(Direction.DOWN, Animation.balloom_left.getFxImages());
                        balloom.setDeadAnimation(Animation.balloom_dead.getFxImages());
                        object = balloom;
                        break;
                    case '2':
                        Oneal oneal = new Oneal(x, y, Animation.oneal_left.getFxImages());
                        oneal.setMoveAnimation(Direction.RIGHT, Animation.oneal_right.getFxImages());
                        oneal.setMoveAnimation(Direction.LEFT, Animation.oneal_left.getFxImages());
                        oneal.setMoveAnimation(Direction.UP, Animation.oneal_right.getFxImages());
                        oneal.setMoveAnimation(Direction.DOWN, Animation.oneal_left.getFxImages());
                        oneal.setDeadAnimation(Animation.oneal_dead.getFxImages());
                        object = oneal;
                        break;
                    default:

                }
                if (object != null) {
                    entities.add(object);
                }
            }
    }
    public void update(long now) {
        int curLive = 0;
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber) {
                Bomber bomber = (Bomber) entities.get(i);
                curLive = bomber.getNumberLives();
                if (curLive == 0) {

                    System.out.println("You Lose");
                    System.exit(0);
                }
            }
            entities.get(i).update(entities, now);

            for (int j = 0; j < entities.size(); j++) {
                if (entities.get(j) instanceof Bomber) {
                    int lastLive = ((Bomber) entities.get(j)).getNumberLives();
                    if (curLive - lastLive == 1) {
                        try {
                            createMap(lastLive);
                        } catch (Exception e) {

                        }
                        return;
                    }
                }
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
