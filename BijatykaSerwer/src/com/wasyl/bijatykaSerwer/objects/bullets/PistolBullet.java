package com.wasyl.bijatykaSerwer.objects.bullets;

import com.wasyl.bijatykaSerwer.framework.Game;
import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.LinkedList;

import javafx.scene.image.Image;

public class PistolBullet extends GameObject {

    private Textures textures;
    private Image leftImage;
    private Image rightImage;
    private int defVel = 75;
    private int direction;


    public PistolBullet(double x, double y, ID id, int direction, Textures textures) {
        super(x, y, id);
        this.leftImage = textures.getPociskLewo();
        this.rightImage = textures.getPociskPrawo();
        this.direction = direction;
        if (direction == 1) defVel = -defVel;
    }


    @Override
    public void update(LinkedList<GameObject> objects) {
        setPositionX(getPositionX() + defVel);
        collisions(objects);
        if(getPositionX() > Game.screenWidth*2 || getPositionX()< Game.screenWidth*-2)
            objects.remove(this);
    }


    private void collisions(LinkedList<GameObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId().equals(ID.Player)) {
                Player bufPlayer = (Player) objects.get(i);
                if (getBounds().intersects(bufPlayer.getBoundsCentral())) {
                    if (bufPlayer.getHittedCounter() <= 0) {
                        bufPlayer.setHittedCounter(198);
                        if(defVel > 0) bufPlayer.setDirectionToRecoil(1);
                        else bufPlayer.setDirectionToRecoil(-1);
                    }
                    objects.remove(this);
                }
            }
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D((int) getPositionX() - leftImage.getWidth(), (int) getPositionY(), leftImage.getWidth()*3, rightImage.getHeight());
    }

    public int getDirection() {
        return direction;
    }
}
