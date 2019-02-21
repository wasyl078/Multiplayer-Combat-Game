package com.wasyl.bijatykaKlient.objects.gameobjects.melee;

import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Player;
import com.wasyl.bijatykaKlient.sounds.SoundsEffect;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

public class MeleeLightSaber extends MeleeArmas {


    //związane z korektą pozycji
    private int addPosX1;       //lewo,pionowo
    private int addPosX2;       //prawo,pionowo
    private int addPosX3;       //lewo, atak
    private int addPosX4;       //prawo,atak
    private int addPosY1;       //lewo,pinowo
    private int addPosY2;       //prawo,pionowo
    private int addPosY3;       //lewo,atak
    private int addPosY4;       //prawo,atak
    private boolean soundSwitch = true;


    public MeleeLightSaber(int x, int y, Player ownerPlayer, Textures textures) {
        super(x, y, ownerPlayer);
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
            gc.drawImage(getVerLeft(),  player.getPositionX() + addPosX1 + cpx,player.getPositionY() + addPosY1 + cpy);
            soundSwitch = true;
        } else if (lastWeapon == 2) {
            gc.drawImage(getVerRigh(), player.getPositionX() + addPosX2 + cpx,player.getPositionY() + addPosY2 + cpy);
            soundSwitch = true;
        } else if (lastWeapon == 3) {
            gc.drawImage(getAttLeft(), player.getPositionX() + addPosX3 + cpx, player.getPositionY() + addPosY3 + cpy);
            if (soundSwitch) {
                SoundsEffect.makeLightSaberSound();
                soundSwitch = false;
            }
        } else if (lastWeapon == 4) {
            gc.drawImage(getAttRight(), player.getPositionX() + addPosX4 + cpx, player.getPositionY() + addPosY4 + cpy);
            if (soundSwitch) {
                SoundsEffect.makeLightSaberSound();
                soundSwitch = false;
            }
        }
    }


    //korekta pozycji
    public void calculateWeaponPosition() {
        addPosX1 = -(int) getVerLeft().getWidth();
        addPosX2 = (int) getOwnerPlayer().getPlayerImageWidth();
        addPosX3 = -(int) getAttLeft().getWidth();
        addPosX4 = addPosX2;
        addPosY1 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getVerLeft().getHeight();
        addPosY2 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getVerRigh().getHeight();
        addPosY3 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getAttLeft().getHeight();
        addPosY4 = (int) getOwnerPlayer().getPlayerImageHeight() - (int) getAttRight().getHeight();
    }
}
