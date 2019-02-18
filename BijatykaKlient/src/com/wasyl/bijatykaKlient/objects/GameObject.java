package com.wasyl.bijatykaKlient.objects;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public abstract class GameObject {

    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;

    public GameObject(int x, int y) {
        setPositionX(x);
        setPositionY(y);
    }

    //update()
    public abstract void update(LinkedList<GameObject> object);


    //draw()
    public abstract void draw(GraphicsContext gc);

    //gettery i settery
    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}
