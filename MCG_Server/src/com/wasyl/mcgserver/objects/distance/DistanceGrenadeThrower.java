package com.wasyl.mcgserver.objects.distance;

import com.wasyl.mcgserver.objects.GameObject;
import com.wasyl.mcgserver.objects.ID;
import com.wasyl.mcgserver.objects.Player;
import com.wasyl.mcgserver.objects.bullets.Grenade;
import com.wasyl.mcgserver.textures.Textures;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.LinkedList;

public class DistanceGrenadeThrower extends DistanceArmas {

    private int countdown = 0;

    public DistanceGrenadeThrower(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id, ownerPlayer, textures);
        setRightImage(textures.getGrenadeRight());
        setLeftImage(textures.getGrenadeLeft());
    }


    @Override
    public void update(LinkedList<GameObject> objects, ArrayList<Integer> sounds) {
        Player player = getOwnerPlayer();

        if(player.getDirection() == 1){
            if(countdown>0){
                player.setLastWeapon(19);
            } else if(player.getAttacking() == 1 && countdown==0){
                Grenade bufGrenade = new Grenade((int) getOwnerPlayer().getPositionX() - getLeftImage().getWidth(), (int) getOwnerPlayer().getPositionY(), ID.Grenade, 1, getTextures());
                objects.add(bufGrenade);
                sounds.add(5);
                player.setLastWeapon(19);
                countdown = 200;
            } else player.setLastWeapon(17);

        } else {
            if(countdown>0){
                player.setLastWeapon(20);
            }else if(player.getAttacking() == 1 && countdown==0){
                Grenade bufGrenade = new Grenade((int) getOwnerPlayer().getPositionX() + getOwnerPlayer().getPlayerImageWidth(), (int) getOwnerPlayer().getPositionY(), ID.Grenade, 2, getTextures());
                objects.add(bufGrenade);
                sounds.add(5);
                player.setLastWeapon(20);
                countdown = 200;
            } else player.setLastWeapon(18);
        }

        countdown--;
        if (countdown < 0) countdown = 0;
    }


    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
