package com.wasyl.bijatykaSerwer.objects.melee;

import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.LinkedList;

public class ForceShield extends MeleeArmas {

    public ForceShield(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id, ownerPlayer, textures);
        setAttLeft(textures.getForceShieldLeft());
        setAttRight(textures.getForceShieldRight());
    }

    @Override
    public void update(LinkedList<GameObject> objects) {

        Player player = getOwnerPlayer();
        if (player.getDirection() == 1) {
            if (player.getAttacking() == 1) {
                player.setLastWeapon(15);
            } else {
                player.setLastWeapon(13);
            }
        } else {
            if (player.getAttacking() == 1) {
                player.setLastWeapon(16);
            } else {
                player.setLastWeapon(14);
            }
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
