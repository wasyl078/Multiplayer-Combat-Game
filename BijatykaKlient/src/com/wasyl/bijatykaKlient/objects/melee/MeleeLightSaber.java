package com.wasyl.bijatykaKlient.objects.melee;

import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.sounds.SoundsEffect;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

public class MeleeLightSaber extends MeleeArmas {


    //związane z korektą pozycji
    private double addPosX1;       //lewo,pionowo
    private double addPosX2;       //prawo,pionowo
    private double addPosX3;       //lewo, atak
    private double addPosX4;       //prawo,atak
    private double addPosY1;       //lewo,pinowo
    private double addPosY2;       //prawo,pionowo
    private double addPosY3;       //lewo,atak
    private double addPosY4;       //prawo,atak
    private boolean soundSwitch = true;


    public MeleeLightSaber(double x, double y, Player ownerPlayer, Textures textures) {
        super(x, y, ownerPlayer, textures);
        setVerLeft(textures.getMieczSwietlnyPionowoLewoImage());
        setVerRigh(textures.getMieczSwietlnyPionowoPrawoImage());
        setAttLeft(textures.getMieczSwietlnyAtakLewoImage());
        setAttRight(textures.getMieczSwietlnyAtakPrawoImage());
        calculateWeaponPosition();
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        Player player = getOwnerPlayer();
        int lastWeapon = player.getLastWeapon();
        if (lastWeapon == 1) {
            gc.drawImage(getVerLeft(), (int) player.getPositionX() + addPosX1, (int) (player.getPositionY() + addPosY1));
            soundSwitch = true;
        }
        else if (lastWeapon == 2) {
            gc.drawImage(getVerRigh(), (int) player.getPositionX() + addPosX2, (int) (player.getPositionY() + addPosY2));
            soundSwitch = true;
        }
        else if (lastWeapon == 3) {
            gc.drawImage(getAttLeft(), (int) player.getPositionX() + addPosX3, (int) (player.getPositionY() + addPosY3));
            if(soundSwitch){
                SoundsEffect.makeLightSaberSound();
                soundSwitch = false;
            }
        }
        else if (lastWeapon == 4) {
            gc.drawImage(getAttRight(), (int) player.getPositionX() + addPosX4, (int) (player.getPositionY() + addPosY4));
            if(soundSwitch){
                SoundsEffect.makeLightSaberSound();
                soundSwitch = false;
            }        }
    }


    //korekta pozycji
    public void calculateWeaponPosition() {
        addPosX1 = -getVerLeft().getWidth();
        addPosX2 = getOwnerPlayer().getPlayerImageWidth();
        addPosX3 = -getAttLeft().getWidth();
        addPosX4 = addPosX2;
        addPosY1 = getOwnerPlayer().getPlayerImageHeight() - getVerLeft().getHeight();
        addPosY2 = getOwnerPlayer().getPlayerImageHeight() - getVerRigh().getHeight();
        addPosY3 = getOwnerPlayer().getPlayerImageHeight() - getAttLeft().getHeight();
        addPosY4 = getOwnerPlayer().getPlayerImageHeight() - getAttRight().getHeight();
    }
}
