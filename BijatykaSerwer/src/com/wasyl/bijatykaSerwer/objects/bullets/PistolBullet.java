package com.wasyl.bijatykaSerwer.objects.bullets;

import com.wasyl.bijatykaSerwer.framework.Game;
import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.image.Image;

public class PistolBullet extends GameObject {

    public static int bullets = 0;
    private int individualBulletNumber;
    private Textures textures;
    private Image leftImage;
    private Image rightImage;
    private int defVel = 75;
    private int direction;
    private String active = "t";


    public PistolBullet(double x, double y, ID id, int direction, Textures textures) {
        super(x, y, id);
        this.leftImage = textures.getPociskLewo();
        this.rightImage = textures.getPociskPrawo();
        this.direction = direction;
        if (direction == 1) defVel = -defVel;
        bullets++;
        individualBulletNumber = bullets;
    }


    @Override
    public void update(LinkedList<GameObject> objects, ArrayList<Integer>sounds) {
        setPositionX(getPositionX() + defVel);
        collisions(objects, sounds);
        if (getPositionX() > Game.screenWidth * 2 || getPositionX() < Game.screenWidth * -2)
            objects.remove(this);
    }


    private void collisions(LinkedList<GameObject> objects,ArrayList<Integer>sounds) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId().equals(ID.Player)) {
                Player bufPlayer = (Player) objects.get(i);
                if (getBounds().intersects(bufPlayer.getBoundsCentral())) {
                    if ((direction == 1 && bufPlayer.getLastWeapon() != 16) || (direction != 1 && bufPlayer.getLastWeapon() != 15)){
                        if (bufPlayer.getHittedCounter() <= 0) {
                            bufPlayer.setHittedCounter(198);
                            if (defVel > 0) bufPlayer.setDirectionToRecoil(1);
                            else bufPlayer.setDirectionToRecoil(-1);
                            bufPlayer.setGivePenalty(true);
                            bufPlayer.setPenaltyHPcounter(400);
                            sounds.add(-3);
                        }
                    }
                    setActive("n");
                    Game.bufPistolBullet = this;
                    objects.remove(this);
                }
            }
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D((int) getPositionX() - leftImage.getWidth(), (int) getPositionY(), leftImage.getWidth() * 3, rightImage.getHeight());
    }

    public int getDirection() {
        return direction;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getIndividualBulletNumber() {
        return individualBulletNumber;
    }
}
