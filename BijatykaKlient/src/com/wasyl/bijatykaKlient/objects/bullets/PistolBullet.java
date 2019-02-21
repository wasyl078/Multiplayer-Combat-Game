package com.wasyl.bijatykaKlient.objects.bullets;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.objects.GameObject;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;

public class PistolBullet extends GameObject {

    private Image bulletImage;
    private int defVel = 20;
    private int direction;
    private LinkedList<GameObject> objects;

    public PistolBullet(int x, int y, int direction, Textures textures, LinkedList<GameObject>objects) {
        super(x, y);
        this.direction = direction;
        if (direction == 1) {
            defVel = -defVel;
            this.bulletImage = textures.getPociskLewo();
        } else this.bulletImage = textures.getPociskPrawo();
        this.objects = objects;
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {

        gc.drawImage(bulletImage,getPositionX() + cpx, getPositionY() + cpy);

        setPositionX(getPositionX() + defVel);
        if(getPositionX() > Game.screenWidth*2 || getPositionX()< Game.screenWidth*-2)
            objects.remove(this);
    }


    public int getDirection() {
        return direction;
    }
}
