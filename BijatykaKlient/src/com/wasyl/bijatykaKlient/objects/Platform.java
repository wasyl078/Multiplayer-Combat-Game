package com.wasyl.bijatykaKlient.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform extends GameObject {

    private int sizeWidth;
    private int sizeHeight;

    public Platform(double x, double y, int sizeWidth, int sizeHeight) {
        super(x, y);
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
    }
    @Override
    public void draw(GraphicsContext gc,int cpx,int cpy) {
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        gc.fillRect(getPositionX() + cpx,getPositionY() + cpy,sizeWidth,sizeHeight);
    }
}