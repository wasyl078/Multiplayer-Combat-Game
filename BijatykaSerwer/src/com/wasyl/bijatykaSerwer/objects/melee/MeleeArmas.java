package com.wasyl.bijatykaSerwer.objects.melee;

import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.scene.image.Image;

public abstract class MeleeArmas extends GameObject {

    private Textures textures;
    private Player ownerPlayer;
    private Image verLeft;
    private Image verRigh;
    private Image attLeft;
    private Image attRight;

    public MeleeArmas(double x, double y, ID id, Player ownerPlayer , Textures textures) {
        super(x, y, id);
        this.ownerPlayer = ownerPlayer;
        this.textures = textures;
    }


    //gettery i settery
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

    public Player getOwnerPlayer() {
        return ownerPlayer;
    }

    public Textures getTextures() {
        return textures;
    }
}
