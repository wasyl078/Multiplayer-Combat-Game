package com.wasyl.bijatykaKlient.objects.gameobjects.distance.throwablethings;

import com.wasyl.bijatykaKlient.objects.gameobjects.GameObject;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Grenade extends GameObject {

    private Image grenadeImageLeft;
    private Image grenadeImageRight;
    private int direction;
    private int individualGrenadeNumber;
    public static ArrayList<Integer> listOfGrenadesNumbers = new ArrayList<>();

    public Grenade(int x, int y, int individualGrenadeNumber, int direction, Textures textures) {
        super(x, y);
        this.individualGrenadeNumber = individualGrenadeNumber;
        listOfGrenadesNumbers.add(individualGrenadeNumber);
        this.grenadeImageLeft = textures.getGrenadeLeft();
        this.grenadeImageRight = textures.getGrenadeRight();
        this.direction = direction;
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        if (direction == 1) gc.drawImage(grenadeImageLeft, getPositionX() + cpx, getPositionY() + cpy);
        else gc.drawImage(grenadeImageRight, getPositionX() + cpx, getPositionY() + cpy);
    }

    public int getIndividualGrenadeNumber() {
        return individualGrenadeNumber;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }
}
