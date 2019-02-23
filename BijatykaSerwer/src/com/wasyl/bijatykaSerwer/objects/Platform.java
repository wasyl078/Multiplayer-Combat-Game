package com.wasyl.bijatykaSerwer.objects;

import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.LinkedList;

public class Platform extends GameObject {

    private int sizeWidth;
    private int sizeHeight;

    public Platform(double x, double y, ID id, int sizeWidth, int sizeHeight) {
        super(x, y, id);
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
    }

    @Override
    public void update(LinkedList<GameObject> object, ArrayList<Integer> sounds) {
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(getPositionX(), getPositionY() - 1, sizeWidth, sizeHeight);
    }

    //gettery
    public int getHeight(){
        return sizeHeight;
    }

    public int getWidth(){
        return sizeWidth;
    }

}