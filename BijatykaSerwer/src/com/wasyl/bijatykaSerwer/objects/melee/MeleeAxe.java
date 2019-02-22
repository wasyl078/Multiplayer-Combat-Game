package com.wasyl.bijatykaSerwer.objects.melee;

import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.LinkedList;


public class MeleeAxe extends MeleeArmas {

    private int attackCounter = 100;


    public MeleeAxe(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id, ownerPlayer, textures);
        setVerLeft(textures.getToporPionowoLewoImage());
        setVerRigh(textures.getToporPionowoPrawoImage());
        setAttLeft(textures.getToporAtakLewoImage());
        setAttRight(textures.getToporAtakPrawoImage());
    }


    @Override
    public void update(LinkedList<GameObject> objects) {
        Player player = getOwnerPlayer();
        if (player.getDirection() == 1) {
            if (player.getAttacking() == 1 && attackCounter > 0) {
                player.setLastWeapon(7);
                attackCounter--;
            } else {
                player.setLastWeapon(5);
                if (player.getAttacking() == 0) attackCounter = 25;
            }

        } else {
            if (player.getAttacking() == 1 && attackCounter > 0) {
                player.setLastWeapon(8);
                attackCounter--;
            } else {
                player.setLastWeapon(6);
                if (player.getAttacking() == 0) attackCounter = 25;
            }
        }

        if (player.getLastWeapon() == 7 || player.getLastWeapon() == 8) collisions(objects);
    }


    private void collisions(LinkedList<GameObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId().equals(ID.Player)) {
                Player anotherPlayer = (Player) objects.get(i);
                if (anotherPlayer.getHittedCounter() <= 0) {
                    if (getBounds().intersects(anotherPlayer.getBoundsCentral())) {
                        anotherPlayer.setHittedCounter(210);
                        if(getOwnerPlayer().getDirection() == 1) anotherPlayer.setDirectionToRecoil(-1);
                        else anotherPlayer.setDirectionToRecoil(1);
                        anotherPlayer.setGivePenalty(true);
                        anotherPlayer.setPenaltyHPcounter(75);
                    }
                }
            }
        }

    }


    @Override
    public Rectangle2D getBounds() {
        Rectangle2D swordBounds;
        if (getOwnerPlayer().getLastWeapon() == 7 || getOwnerPlayer().getLastWeapon() == 8) {
            if (getOwnerPlayer().getLastWeapon() == 7)
                swordBounds = new Rectangle2D((int) (getOwnerPlayer().getPositionX() - getAttLeft().getWidth()), (int) (getOwnerPlayer().getPositionY() + getOwnerPlayer().getPlayerImageHeight() - getAttLeft().getHeight()), getAttLeft().getWidth(), getAttLeft().getHeight());
            else
                swordBounds = new Rectangle2D((int) (getOwnerPlayer().getPositionX() + getOwnerPlayer().getPlayerImageWidth()), (int) (getOwnerPlayer().getPositionY() + getOwnerPlayer().getPlayerImageHeight() - getAttRight().getHeight()), getAttRight().getWidth(), getAttRight().getHeight());
        } else swordBounds = null;
        return swordBounds;
    }
}