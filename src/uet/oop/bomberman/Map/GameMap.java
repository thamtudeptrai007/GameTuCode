package uet.oop.bomberman.Map;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_1_random;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_2_random_speed;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy_3;
import uet.oop.bomberman.entities.StaticObject.Grass;
import uet.oop.bomberman.entities.StaticObject.Items.*;
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
    private int next = 1;
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
        for (int i = 0; i < next; i++)
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
                    case 'l':
                        entities.add(new LiveItem(x, y, Sprite.powerup_live.getFxImage()));
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
                        Enemy_1_random balloom = new Enemy_1_random(x, y, Animation.enemy_1_random_left.getFxImages());
                        balloom.setMoveAnimation(Direction.RIGHT, Animation.enemy_1_random_right.getFxImages());
                        balloom.setMoveAnimation(Direction.LEFT, Animation.enemy_1_random_left.getFxImages());
                        balloom.setMoveAnimation(Direction.UP, Animation.enemy_1_random_up.getFxImages());
                        balloom.setMoveAnimation(Direction.DOWN, Animation.enemy_1_random_down.getFxImages());
                        balloom.setDeadAnimation(Animation.enemy_1_random_dead.getFxImages());
                        object = balloom;
                        break;
                    case '2':
                        Enemy_2_random_speed oneal = new Enemy_2_random_speed(x, y, Animation.enemy_1_random_speed_left.getFxImages());
                        oneal.setMoveAnimation(Direction.RIGHT, Animation.enemy_1_random_speed_right.getFxImages());
                        oneal.setMoveAnimation(Direction.LEFT, Animation.enemy_1_random_speed_left.getFxImages());
                        oneal.setMoveAnimation(Direction.UP, Animation.enemy_1_random_speed_up.getFxImages());
                        oneal.setMoveAnimation(Direction.DOWN, Animation.enemy_1_random_speed_down.getFxImages());
                        oneal.setDeadAnimation(Animation.enemy_1_random_speed_dead.getFxImages());
                        object = oneal;
                        break;

                    case '3':
                        Enemy_3 enemy_3  = new Enemy_3(x, y, Animation.enemy_3_left.getFxImages());
                        enemy_3.setMoveAnimation(Direction.RIGHT, Animation.enemy_3_right.getFxImages());
                        enemy_3.setMoveAnimation(Direction.LEFT, Animation.enemy_3_left.getFxImages());
                        enemy_3.setMoveAnimation(Direction.UP, Animation.enemy_3_up.getFxImages());
                        enemy_3.setMoveAnimation(Direction.DOWN, Animation.enemy_3_down.getFxImages());
                        enemy_3.setDeadAnimation(Animation.enemy_3_dead.getFxImages());
                        object = enemy_3;
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
                            next = next + height + 1;
                            next = (next > 70 ? 1 : next);
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
