package uet.oop.bomberman.entities.DynamicObject.Movable;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.entities.DynamicObject.Bomb.Bomb;
import uet.oop.bomberman.entities.DynamicObject.Movable.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.DynamicObject.Brick;
import uet.oop.bomberman.entities.StaticObject.Wall;
import uet.oop.bomberman.graphics.Animation;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Support.Sound;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Movable {
    private static final int safeDistance = 10;
    private boolean newBomb = false;
    private int flameSize = 3;
    private int numberBombs = 5;
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
            if (entity instanceof Enemy && checkCollision(entity)
                    && ((Enemy) entity).isAlive()) {
                dead();
                return;
            }
        }
    }

    public Pair<Integer, Integer> trueCoordinates(List<Entity> entities, int newX, int newY) {
        //Sound.playFootsteps();
        for (Entity entity : entities) {
            if (entity instanceof Brick || entity instanceof Wall) {
                int otherLeft = entity.getX();
                int otherRight = otherLeft + Sprite.DEFAULT_SIZE;
                int otherTop = entity.getY();
                int otherBottom = otherTop + Sprite.DEFAULT_SIZE;
                int curLeft = x, curRight = curLeft + Sprite.DEFAULT_SIZE;
                int curTop = y, curBottom = curTop + Sprite.DEFAULT_SIZE;
                if (checkCollision(newX, newY, entity)) {
                    switch (direction) {
                        case LEFT:
                        case RIGHT:
                            if (otherTop - curTop >= safeDistance) {
                                if (getAt(entity.getXUnit(), entity.getYUnit() - 1, entities) == null) {
                                    return new Pair<>(newX, otherTop - Sprite.DEFAULT_SIZE);
                                }
                            }
                            if (curBottom - otherBottom >= safeDistance) {
                                if (getAt(entity.getXUnit(), entity.getYUnit() + 1, entities) == null) {
                                    return new Pair<>(newX, otherTop + Sprite.DEFAULT_SIZE);
                                }
                            }
                            break;
                        case UP:
                        case DOWN:
                            if (otherLeft - curLeft >= safeDistance) {
                                if (getAt(entity.getXUnit() - 1, entity.getYUnit(), entities) == null) {
                                    return new Pair<>(otherLeft - Sprite.DEFAULT_SIZE, newY);
                                }
                            }
                            if (curRight - otherRight >= safeDistance) {
                                if (getAt(entity.getXUnit() + 1, entity.getYUnit(), entities) == null) {
                                    return new Pair<>(otherLeft + Sprite.DEFAULT_SIZE, newY);
                                }
                            }
                            break;
                    }
                    return new Pair<>(x, y);
                }
            }
            if (entity instanceof Bomb && !((Bomb) entity).isBombPass() && checkCollision(newX, newY, entity)) {
                return new Pair<>(x, y);
            }
        }
        return new Pair<>(newX, newY);
    }

    public void addNewBomb(List<Entity> entities, long now) {
        newBomb = false;
        int Left = this.getXUnit() * Sprite.DEFAULT_SIZE;
        int Right = Left + Sprite.DEFAULT_SIZE;
        int Top = this.getYUnit() * Sprite.DEFAULT_SIZE;
        int Bot = Top + Sprite.DEFAULT_SIZE;
        int posX = x / Sprite.DEFAULT_SIZE;
        int posY = y / Sprite.DEFAULT_SIZE;
        switch (direction) {
            case LEFT:
            case RIGHT:
                if (Right - x < Sprite.DEFAULT_SIZE / 2 + 4) {
                    posX++;
                }
                break;
            case UP:
            case DOWN:
                if (Bot - y < Sprite.DEFAULT_SIZE / 2 + 4) {
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

        //add am thanh dat bom
        Sound.playPlaceNewBomb();
    }

    public void removeBomb(List<Entity> entities, Bomb bomb) {
        bombList.remove(bomb);
        entities.remove(bomb);

        //add am thanh bom no
        Sound.playBombExplose();
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
        if (numberLives < BombermanGame.numberLives)
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
