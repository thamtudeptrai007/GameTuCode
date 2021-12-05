package uet.oop.bomberman.entities.DynamicObject;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DynamicObject extends Entity {

    protected List<Image> animation = new ArrayList<>();
    protected int currentImg = 0;
    protected double timer = 0;

    public DynamicObject() {}

    public DynamicObject(int xUnit, int yUnit, Image... img) {
        super(xUnit, yUnit, img[0]);
        setAnimation(img);
    }

    public void setAnimation(Image... images) {
        Collections.addAll(animation, images);
    }
}
