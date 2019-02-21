package com.wasyl.bijatykaKlient.objects.melee;

import com.wasyl.bijatykaKlient.objects.GameObject;
import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.image.Image;

public abstract class MeleeArmas extends GameObject {

    //związane z obrazkami
    private Player ownerPlayer;
    private Image verLeft;
    private Image verRigh;
    private Image attLeft;
    private Image attRight;


    //konstruktor klasy
    public MeleeArmas(int x, int y, Player ownerPlayer) {
        super(x, y);
        this.ownerPlayer = ownerPlayer;
    }

    public Player getOwnerPlayer() {
        return ownerPlayer;
    }

    public void setOwnerPlayer(Player ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }

    public Image getVerLeft() {
        return verLeft;
    }

    public void setVerLeft(Image verLeft) {
        this.verLeft = verLeft;
    }

    public Image getVerRigh() {
        return verRigh;
    }

    public void setVerRigh(Image verRigh) {
        this.verRigh = verRigh;
    }

    public Image getAttLeft() {
        return attLeft;
    }

    public void setAttLeft(Image attLeft) {
        this.attLeft = attLeft;
    }

    public Image getAttRight() {
        return attRight;
    }

    public void setAttRight(Image attRight) {
        this.attRight = attRight;
    }
}
