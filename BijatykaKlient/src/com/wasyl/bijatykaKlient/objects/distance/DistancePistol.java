package com.wasyl.bijatykaKlient.objects.distance;

import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.sounds.SoundsEffect;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

public class DistancePistol extends DistanceArmas {

    private int addPosX1;       //lewo
    private int addPosX2;       //prawo
    private int addPosY1;       //lewo
    private int addPosY2;       //prawo
    private boolean soundSwitch = true;

    public DistancePistol(double x, double y, Player ownerPlayer, Textures textures) {
        super(x, y, ownerPlayer, textures);
        setRightImage(textures.getPistoletPrawo());
        setLeftImage(textures.getPistoletLewo());
        calculateWeaponPosition();
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {

        int lastWeapon = getOwnerPlayer().getLastWeapon();
        if (lastWeapon == 9) {

            gc.drawImage(getLeftImage(), (int) getOwnerPlayer().getPositionX() + addPosX1 +cpx, (int) (getOwnerPlayer().getPositionY() + addPosY1 + cpy));
            soundSwitch = true;
        } else if (lastWeapon == 10) {
            gc.drawImage(getRightImage(), (int) getOwnerPlayer().getPositionX() + addPosX2+cpx, (int) (getOwnerPlayer().getPositionY() + addPosY2+cpy));
            soundSwitch = true;
        } else if (lastWeapon == 11) {
            gc.drawImage(getLeftImage(), (int) getOwnerPlayer().getPositionX() + addPosX1+cpx, (int) (getOwnerPlayer().getPositionY() + addPosY1+cpy));
            if (soundSwitch) {
                soundSwitch = false;
                SoundsEffect.makePistolSound();
            }
        } else if (lastWeapon == 12) {
            gc.drawImage(getRightImage(), (int) getOwnerPlayer().getPositionX() + addPosX2+cpx, (int) (getOwnerPlayer().getPositionY() + addPosY2+cpy));
            if (soundSwitch) {
                soundSwitch = false;
                SoundsEffect.makePistolSound();
            }
        }
    }

    public void calculateWeaponPosition() {
        addPosX2 = (int) getOwnerPlayer().getPlayerImageWidth();
        addPosX1 = -(int) getRightImage().getWidth();
        addPosY1 = (int) (getOwnerPlayer().getPlayerImageHeight() - getRightImage().getHeight());
        addPosY2 = (int) (getOwnerPlayer().getPlayerImageHeight() - getRightImage().getHeight());
    }
}
