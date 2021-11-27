package uet.oop.bomberman.entities.DynamicObject.Movable;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.GameMap;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Movable {
    private static final int safeDistance = 20;
    private boolean newBomb = false;
    private int flameSize = 1;
    private int numberBombs = 1;
    private int numberLives = BombermanGame.numberLives;
    private List<Bomb> bombList = new ArrayList<Bomb>();

    public Bomber(int x, int y, Image... img) {
        super( x, y, img);
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if (!alive) {
            timer += 0.2;
            currentImg = (int) (timer >= 0 ? timer : 0) % deadAnimation.size();
            img = deadAnimation.get(currentImg);
            if (currentImg == deadAnimation.size() - 1) {
                decreaseLive();
                
                //System.out.println(numberLives);
            }
            return;
        }
        if (newBomb && bombList.size() < numberBombs) {
            addNewBomb(entities, now);
        }
        if (moving) {
            move(entities);
            timer += SPF;
            currentImg = (int) timer % moveAnimation.get(direction.getValue()).size();
        } else {
            if (currentImg != 0) {
                timer += SPF;
                currentImg = (int) timer % moveAnimation.get(direction.getValue()).size();
            }
        }
        img = moveAnimation.get(direction.getValue()).get(currentImg);
        for (Entity entity : entities) {
            if (entity instanceof Enemy && checkCollision(entity)) {
                dead();
                return;
            }
        }
    }

    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        for (Entity entity : entities) {
            if (entity instanceof Brick || entity instanceof Wall) {
                int otherLeft = entity.getX();
                int otherRight = otherLeft + Sprite.SCALED_SIZE;
                int otherTop = entity.getY();
                int otherBottom = otherTop + Sprite.SCALED_SIZE;
                int curLeft = x, curRight = curLeft + Sprite.SCALED_SIZE;
                int curTop = y, curBottom = curTop + Sprite.SCALED_SIZE;
                if (checkCollision(newX, newY, entity)) {
                    switch (direction) {
                        case LEFT:
                        case RIGHT:
                            if (otherTop - curTop >= safeDistance) {
                                if (getAt(entity.getXUnit(), entity.getYUnit() - 1, entities) == null) {
                                    return new Pair<>(newX, otherTop - Sprite.SCALED_SIZE);
                                }
                            }
                            if (curBottom - otherBottom >= safeDistance) {
                                if (getAt(entity.getXUnit(), entity.getYUnit() + 1, entities) == null) {
                                    return new Pair<>(newX, otherTop + Sprite.SCALED_SIZE);
                                }
                            }
                            break;
                        case UP:
                        case DOWN:
                            if (otherLeft - curLeft >= safeDistance) {
                                if (getAt(entity.getXUnit() - 1, entity.getYUnit(), entities) == null) {
                                    return new Pair<>(otherLeft - Sprite.SCALED_SIZE, newY);
                                }
                            }
                            if (curRight - otherRight >= safeDistance) {
                                if (getAt(entity.getXUnit() + 1, entity.getYUnit(), entities) == null) {
                                    return new Pair<>(otherLeft + Sprite.SCALED_SIZE, newY);
                                }
                            }
                            break;
                    }
                    return new Pair<>(x, y);
                }
            }
        }
        return new Pair<>(newX, newY);
    }

    public void addNewBomb(List<Entity> entities, long now) {
        newBomb = false;
        int Left = this.getXUnit() * Sprite.SCALED_SIZE;
        int Right = Left + Sprite.SCALED_SIZE;
        int Top = this.getYUnit() * Sprite.SCALED_SIZE;
        int Bot = Top + Sprite.SCALED_SIZE;
        int posX = x / Sprite.SCALED_SIZE;
        int posY = y / Sprite.SCALED_SIZE;
        switch (direction) {
            case LEFT:
            case RIGHT:
                if (Right - x < Sprite.SCALED_SIZE / 2) {
                    posX++;
                }
                break;
            case UP:
            case DOWN:
                if (Bot - y < Sprite.SCALED_SIZE / 2) {
                    posY++;
                }
        }
        //System.out.printf("%d %d %d %d\n", x, y, Left, Right);
        //System.out.printf("%d %d\n\n", posX, posY);
        for (Entity entity : entities) {
            if (entity instanceof Bomb) {
                if (entity.getXUnit() == posX && entity.getYUnit() == posY) {
                    return;
                }
            }
            if (entity instanceof Enemy) {
                if (entity.getXUnit() == posX && entity.getYUnit() == posY && ((Enemy) entity).isAlive()) {
                    return;
                }
            }
        }
        Bomb newBomb = new Bomb(posX, posY, now, flameSize, this, Animation.bomb.getFxImages());
        bombList.add(newBomb);
        entities.add(newBomb);
    }

    public void removeBomb(List<Entity> entities, Bomb bomb) {
        bombList.remove(bomb);
        entities.remove(bomb);
    }

    public int getNumberLives() {
        return numberLives;
    }

    public void setNumberLives(int numberLives) {
        this.numberLives = numberLives;
    }

    public void decreaseLive() {
        numberLives--;
    }

    public void increaseLive() {
        numberLives++;
    }

    public void increaseSpeed(int value) {
        moveSpeed += value;
    }

    public void increaseFlameSize() {
        flameSize++;
    }

    public void increaseNumberBombs() {
        numberBombs++;
    }

    public void press(KeyEvent event) {
        moving = true;
        switch (event.getCode()) {
            case LEFT:
                direction = Direction.LEFT;
                moveSpeedX = -moveSpeed;
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                moveSpeedX = moveSpeed;
                break;
            case UP:
                direction = Direction.UP;
                moveSpeedY = -moveSpeed;
                break;
            case DOWN:
                direction = Direction.DOWN;
                moveSpeedY = moveSpeed;
                break;
            case SPACE:
                newBomb = true;
            default:
                moving = false;
        }
    }

    public void release(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT:
            case RIGHT:
                moving = false;
                moveSpeedX = 0;
                break;
            case UP:
            case DOWN:
                moving = false;
                moveSpeedY = 0;
                break;
            case SPACE:
                newBomb = false;
                break;
        }
    }
}
