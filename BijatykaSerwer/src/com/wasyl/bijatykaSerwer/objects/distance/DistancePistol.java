package com.wasyl.bijatykaSerwer.objects.distance;

import com.wasyl.bijatykaSerwer.framework.Game;
import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.objects.bullets.PistolBullet;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.LinkedList;

public class DistancePistol extends DistanceArmas {

    private int attackCounter = 30;


    public DistancePistol(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id, ownerPlayer, textures);
        setRightImage(textures.getPistoletPrawo());
        setLeftImage(textures.getPistoletLewo());
    }

    @Override
    public void update(LinkedList<GameObject> objects) {
        if (getOwnerPlayer().getDirection() == 1) {
            if (getOwnerPlayer().getAttacking() == 1 && attackCounter == 0) {
                getOwnerPlayer().setLastWeapon(11);
                attackCounter = 50;
                PistolBullet bufPistolBullet = new PistolBullet((int) getOwnerPlayer().getPositionX() - getLeftImage().getWidth(), (int) getOwnerPlayer().getPositionY() + getOwnerPlayer().getPlayerImageHeight() - getLeftImage().getHeight() + 0.5 * getTextures().getPociskLewo().getHeight() / 2, ID.PistolBullet, 1, getTextures());
                objects.add(bufPistolBullet);
                Game.bufPistolBullet = bufPistolBullet;
            } else {
                getOwnerPlayer().setLastWeapon(9);
                attackCounter--;
            }

        } else {
            if (getOwnerPlayer().getAttacking() == 1 && attackCounter == 0) {
                getOwnerPlayer().setLastWeapon(12);
                attackCounter = 50;
                PistolBullet bufPistolBullet = new PistolBullet((int) getOwnerPlayer().getPositionX() + getRightImage().getWidth() + getTextures().getPociskPrawo().getWidth(), (int) getOwnerPlayer().getPositionY() + getOwnerPlayer().getPlayerImageHeight() - getLeftImage().getHeight() + 0.5 * getTextures().getPociskLewo().getHeight() / 2, ID.PistolBullet, 2, getTextures());
                objects.add(bufPistolBullet);
                Game.bufPistolBullet = bufPistolBullet;
            } else {
                getOwnerPlayer().setLastWeapon(10);
                attackCounter--;
            }
        }
        if (attackCounter < 0) attackCounter = 0;
    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
