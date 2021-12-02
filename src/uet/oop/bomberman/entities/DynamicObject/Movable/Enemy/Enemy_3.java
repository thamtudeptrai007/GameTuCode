package uet.oop.bomberman.entities.DynamicObject.Movable.Enemy;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.Support.Direction;
import uet.oop.bomberman.Support.Info;
import uet.oop.bomberman.entities.DynamicObject.Movable.Bomber;
import uet.oop.bomberman.entities.Entity;

import java.util.*;

/// Boss thong minh
public class Enemy_3 extends Enemy {

    private boolean visited[][] = new boolean[50][50];
    private int f[][] = new int[50][50];
    private Info last[][][] = new Info[50][50][4];

    public Enemy_3(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void enemyUpdate(List<Entity> entities, long now) {
        moving = true;
        for (Entity entity : entities) {
            if (entity instanceof Bomber) {
                BFS((Bomber) entity, entities);
                //System.out.printf("%d %d\n", entity.getXUnit(), entity.getYUnit());
                break;
            }
        }
    }

    public void trace(Info curInfo, Bomber bomber) {
        int curXUnit = curInfo.getInfoXUnit();
        int curYUnit = curInfo.getInfoYUnit();
        Direction curDir = curInfo.getDirection();

        while (true) {
            //System.out.printf("%d %d %d\n", curXUnit, curYUnit, curDir.getValue());
            switch (curDir.getValue()) {
                case 0:
                    direction = Direction.RIGHT;
                    moveSpeedX = moveSpeed;
                    break;
                case 1:
                    direction = Direction.LEFT;
                    moveSpeedX = -moveSpeed;
                    break;
                case 2:
                    direction = Direction.DOWN;
                    moveSpeedY = moveSpeed;
                    break;
                case 3:
                    direction = Direction.UP;
                    moveSpeedY = -moveSpeed;
                    break;
            }
            if (curXUnit == bomber.getXUnit() && curYUnit == bomber.getYUnit()) {
                return;
            }
            int lastXUnit = last[curXUnit][curYUnit][curDir.getValue()].getInfoXUnit();
            int lastYUnit = last[curXUnit][curYUnit][curDir.getValue()].getInfoYUnit();
            Direction lastDir = last[curXUnit][curYUnit][curDir.getValue()].getDirection();

            curXUnit = lastXUnit;
            curYUnit = lastYUnit;
            curDir = lastDir;


        }
    }

    public void BFS(Bomber bomber, List<Entity> entities) {
        Queue <Info> q = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                visited[i][j] = false;
                f[i][j] = 0;
                for (int k = 0; k < 4; k++) {
                    last[i][j][k] = new Info();
                }
            }
        }
        //System.out.printf("%d %d\n",bomber.getXUnit(), bomber.getYUnit());
        q.offer(new Info(bomber.getXUnit(), bomber.getYUnit(), Direction.RIGHT));
        visited[bomber.getXUnit()][bomber.getYUnit()] = true;
        while (!q.isEmpty()) {
            int curXUnit = q.element().getInfoXUnit();
            int curYUnit = q.element().getInfoYUnit();
            Direction curDir = q.element().getDirection();

            if (curXUnit == getXUnit() && curYUnit == getYUnit()) {
                trace(q.element(), bomber);
                //System.out.printf("%d %d %d %d\n", curXUnit, curYUnit, bomber.getXUnit(), bomber.getYUnit());
                //System.exit(0);
                return;
            }

            q.remove();
            for (int i = 0; i < 4; i++) {
                int newXUnit = curXUnit + listNewXUnit[i];
                int newYUnit = curYUnit + listNewYUnit[i];
                Direction newDir = Direction.getDirection(i);
                if (getAt(newXUnit, newYUnit, entities) == null) {
                    if (!visited[newXUnit][newYUnit]) {
                        visited[newXUnit][newYUnit] = true;
                        q.offer(new Info(newXUnit, newYUnit, newDir));
                        f[newXUnit][newYUnit] = f[curXUnit][curYUnit] + 1;
                        last[newXUnit][newYUnit][newDir.getValue()] = new Info(curXUnit, curYUnit, curDir);
                    }
                }
            }
        }
    }
}
