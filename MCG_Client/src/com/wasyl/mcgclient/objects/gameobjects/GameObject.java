package com.wasyl.mcgclient.objects.gameobjects;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private boolean delete = false;

    public GameObject(int x, int y) {
        setPositionX(x);
        setPositionY(y);
    }

    //draw()
    public abstract void draw(GraphicsContext gc, int cpx, int cpy);

    //gettery i settery
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public boolean shouldDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
