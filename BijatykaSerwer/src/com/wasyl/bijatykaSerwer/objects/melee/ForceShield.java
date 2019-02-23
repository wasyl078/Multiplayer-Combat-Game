package com.wasyl.bijatykaSerwer.objects.melee;

import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.LinkedList;

public class ForceShield extends MeleeArmas {

    private boolean soundSwitch = true;

    public ForceShield(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id, ownerPlayer, textures);
        setAttLeft(textures.getForceShieldLeft());
        setAttRight(textures.getForceShieldRight());
    }

    @Override
    public void update(LinkedList<GameObject> objects, ArrayList<Integer> sounds) {

        Player player = getOwnerPlayer();
        if (player.getDirection() == 1) {
            if (player.getAttacking() == 1) {
                player.setLastWeapon(15);
                if(soundSwitch){
                    sounds.add(4);
                    soundSwitch= false;
                }
            } else {
                player.setLastWeapon(13);
                soundSwitch=true;
            }
        } else {
            if (player.getAttacking() == 1) {
                player.setLastWeapon(16);
                if(soundSwitch){
                    sounds.add(4);
                    soundSwitch= false;
                }
            } else {
                player.setLastWeapon(14);
                soundSwitch = true;
            }
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
