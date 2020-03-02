package com.wasyl.mcgclient.objects.gameobjects.distance;

import com.wasyl.mcgclient.objects.gameobjects.characters.Player;
import com.wasyl.mcgclient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

public class DistanceGrenadeThrower extends DistanceArmas {


    private int addPosX1;       //lewo
    private int addPosX2;       //prawo
    private int addPosY1;       //lewo
    private int addPosY2;       //prawo


    public DistanceGrenadeThrower(int x, int y, Player ownerPlayer, Textures textures) {
        super(x, y, ownerPlayer);
        setRightImage(textures.getGrenadeRight());
        setLeftImage(textures.getGrenadeLeft());
        calculateWeaponPosition();
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        int lastWeapon = getOwnerPlayer().getLastWeapon();
        if (lastWeapon == 17) gc.drawImage(getLeftImage(), getOwnerPlayer().getPositionX() + addPosX1 + cpx, getOwnerPlayer().getPositionY() + cpy);
        else if (lastWeapon == 18) gc.drawImage(getRightImage(), getOwnerPlayer().getPositionX() + addPosX2 + cpx, getOwnerPlayer().getPositionY() + cpy);
      //  else if (lastWeapon == 19) gc.drawImage(getLeftImage(), getOwnerPlayer().getPositionX() + addPosX1 + cpx, getOwnerPlayer().getPositionY() + addPosY1 + cpy);
      //  else if (lastWeapon == 20) gc.drawImage(getRightImage(), getOwnerPlayer().getPositionX() + addPosX2 + cpx, getOwnerPlayer().getPositionY() + addPosY2 + cpy);
    }

    public void calculateWeaponPosition() {
        addPosX2 = (int) getOwnerPlayer().getPlayerImageWidth();
        addPosX1 = -(int) getRightImage().getWidth();
        addPosY1 = (int) (getOwnerPlayer().getPlayerImageHeight() - getRightImage().getHeight());
        addPosY2 = (int) (getOwnerPlayer().getPlayerImageHeight() - getRightImage().getHeight());
    }

}
