package uet.oop.bomberman.entities.DynamicObject.Movable;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
    private int flameSize;
    private int numberBombs;
    private int numberLives;
    private int score;
    private final List<Bomb> bombList = new ArrayList<>();

    public Bomber(int x, int y, Image... img) {
        super( x, y, img);
    }

    public Bomber(int flameSize, int numberBombs, int numberLives, int score, double SPF, int moveSpeed) {
        super();
        this.flameSize = flameSize;
        this.numberBombs = numberBombs;
        this.numberLives = numberLives;
        this.score = score;
        this.SPF = SPF;
        this.moveSpeed = moveSpeed;
    }

    public void setAll(Bomber other) {
        this.flameSize = other.getFlameSize();
        this.numberBombs = other.getNumberBombs();
        this.numberLives = other.getNumberLives();
        this.score = other.getScore();
        this.SPF = other.getSPF();
        this.moveSpeed = other.getMoveSpeed();
    }

    @Override
    public void update(List<Entity> entities, long now) {
        if (!alive) {
            timer += 0.15;
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
            if (entity instanceof Enemy && checkCollision(entity, cheatDistance)
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
                if (checkCollision(newX, newY, entity, 0)) {
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
            if (entity instanceof Bomb && !((Bomb) entity).isBombPass()
                    && checkCollision(newX, newY, entity, 0)) {
                return new Pair<>(x, y);
            }
        }
        return new Pair<>(newX, newY);
    }

    public void addNewBomb(List<Entity> entities, long now) {
        newBomb = false;
        int Left = getXUnit() * Sprite.DEFAULT_SIZE;
        int Right = Left + Sprite.DEFAULT_SIZE;
        int Top = getYUnit() * Sprite.DEFAULT_SIZE;
        int Bot = Top + Sprite.DEFAULT_SIZE;
        int posX = getXUnit();
        int posY = getYUnit();

        if (Right - x < Sprite.DEFAULT_SIZE / 2 + 3) {
            posX++;
        }
        if (Bot - y < Sprite.DEFAULT_SIZE / 2 + 3) {
            posY++;
        }

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
        if (numberLives < BombermanGame.maxNumberLives)
             numberLives++;
    }

    public void increaseSpeed(int value) {
        if (moveSpeed < BombermanGame.maxSpeed) {
            moveSpeed += value;
            SPF += 0.1;
        }
    }

    public void increaseFlameSize() {
        if (flameSize < BombermanGame.maxFlameSizes) {
            flameSize++;
        }
    }

    public void increaseNumberBombs() {
        if (numberBombs < BombermanGame.maxNumberBombs) {
            numberBombs++;
        }
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void setScore(int value) {
        score = value;
    }

    public int getScore() {
        return score;
    }

    public int getFlameSize() {
        return flameSize;
    }

    public int getNumberBombs() {
        return numberBombs;
    }

    public void press(List<KeyEvent> keyEvents) {
        //Sound.playFootsteps();
        moving = true;
        for (KeyEvent keyEvent : keyEvents) {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                direction = Direction.LEFT;
                moveSpeedX = -moveSpeed;
            }

            if (keyEvent.getCode() == KeyCode.UP) {
                direction = Direction.UP;
                moveSpeedY = -moveSpeed;
            }
            if (keyEvent.getCode() == KeyCode.DOWN) {
                direction = Direction.DOWN;
                moveSpeedY = moveSpeed;
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                direction = Direction.RIGHT;
                moveSpeedX = moveSpeed;
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                newBomb = true;
            }
        }
    }

    public void release(List<KeyEvent> keyEvents, List<KeyEvent> keyEventsRelease) {
        boolean check = true;
        boolean checkLeft = true;
        boolean checkRight = true;
        boolean checkUp = true;
        boolean checkDown = true;
        for (KeyEvent keyEvent : keyEvents) {
            if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT
                    || keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP) {
                check = false;
            }
            if (keyEvent.getCode() == KeyCode.LEFT) {
                checkRight = false;
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                checkLeft = false;
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                checkDown = false;
            }
            if (keyEvent.getCode() == KeyCode.DOWN) {
                checkUp = false;
            }
        }
        for (KeyEvent keyEvent : keyEventsRelease) {
            //System.out.println(keyEvent.getCode().toString());
            if ((keyEvent.getCode() == KeyCode.LEFT && checkLeft)||(keyEvent.getCode() == KeyCode.RIGHT && checkRight)) {
                if (!checkDown) {
                    direction = Direction.UP;
                }
                if (!checkUp) {
                    direction = Direction.DOWN;
                }
                moveSpeedX = 0;
            }
            if ((keyEvent.getCode() == KeyCode.DOWN && checkDown) || (keyEvent.getCode() == KeyCode.UP && checkUp)) {
                if (!checkLeft) {
                    direction = Direction.RIGHT;
                }
                if(!checkRight) {
                    direction = Direction.LEFT;
                }
                moveSpeedY = 0;
            }
            if (check) {
                moving = false;
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                newBomb = false;
            }
        }
    }
}