package com.wasyl.bijatykaSerwer.objects;

import javafx.geometry.Rectangle2D;

import java.util.LinkedList;

public abstract class GameObject {

    //zmienne
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private ID id;
    private String alive = "t";

    //konstruktor klasy
    public GameObject(double x, double y, ID id) {
        setPositionX(x);
        setPositionY(y);
        setId(id);
    }


    //update()
    public abstract void update(LinkedList<GameObject> objects);


    //bounds
    public abstract Rectangle2D getBounds();


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

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }
}
