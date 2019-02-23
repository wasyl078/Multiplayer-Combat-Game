package com.wasyl.bijatykaKlient.objects.gameobjects.distance.throwablethings;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.objects.gameobjects.GameObject;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

public class PistolBullet extends GameObject {

    private Image bulletImage;
    private double defVel;
    private int direction;
    private LinkedList<GameObject> objects;
    private int individualBulletNumber;

    public PistolBullet(int x, int y, int direction,int individualBulletNumber ,Textures textures, LinkedList<GameObject>objects) {
        super(x, y);
        defVel = 0.03888*Game.screenWidth;
        this.direction = direction;
        this.individualBulletNumber = individualBulletNumber;
        if (direction == 1) {
            defVel = -defVel;
            this.bulletImage = textures.getPociskLewo();
        } else this.bulletImage = textures.getPociskPrawo();
        this.objects = objects;
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {

        gc.drawImage(bulletImage,getPositionX() + cpx, getPositionY() + cpy);

        setPositionX(getPositionX() + (int)defVel);
        if(getPositionX() > Game.screenWidth*2 || getPositionX()< Game.screenWidth*-2)
            objects.remove(this);
    }


    public int getDirection() {
        return direction;
    }

    public int getIndividualBulletNumber() {
        return individualBulletNumber;
    }
}
