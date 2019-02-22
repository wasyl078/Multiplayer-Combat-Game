package com.wasyl.bijatykaKlient.objects.gameobjects.melee;

import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Player;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

public class ForceShield extends MeleeArmas {

    private int addPosX1;       //lewo, włączona
    private int addPosX2;       //prawo,włączona
    private int addPosY1;       //lewo, włączona
    private int addPosY2;       //prawo,włączona

    public ForceShield(int x, int y, Player ownerPlayer, Textures textures) {
        super(x, y, ownerPlayer);
        setAttLeft(textures.getForceShieldLeft());
        setAttRight(textures.getForceShieldRight());
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        int lastWeapon = getOwnerPlayer().getLastWeapon();
        if (lastWeapon == 15) gc.drawImage(getAttLeft(), getOwnerPlayer().getPositionX() + addPosX1+cpx, getOwnerPlayer().getPositionY() + addPosY1+cpy);
        else if (lastWeapon == 16) gc.drawImage(getAttRight(),  getOwnerPlayer().getPositionX() + addPosX2+cpx,getOwnerPlayer().getPositionY() + addPosY2+cpy);
    }

    //korekta pozycji
    public void calculateWeaponPosition() {
        addPosX1 = -(int) getAttLeft().getWidth();
        addPosX2 = (int) getOwnerPlayer().getPlayerImageWidth();
        addPosY1 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getAttLeft().getHeight()*3/4;
        addPosY2 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getAttRight().getHeight()*3/4;
    }
}
