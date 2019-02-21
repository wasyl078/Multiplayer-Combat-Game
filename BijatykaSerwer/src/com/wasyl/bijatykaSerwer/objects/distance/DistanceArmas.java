package com.wasyl.bijatykaSerwer.objects.distance;

import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.scene.image.Image;

public abstract class DistanceArmas extends GameObject {

    private Textures textures;
    private Player ownerPlayer;
    private Image leftImage;
    private Image rightImage;

    public DistanceArmas(double x, double y, ID id, Player ownerPlayer, Textures textures) {
        super(x, y, id);
        this.ownerPlayer = ownerPlayer;
        this.textures = textures;
    }

    public Image getRightImage() {
        return rightImage;
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }

    public Image getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(Image leftImage) {
        this.leftImage = leftImage;
    }

    public Player getOwnerPlayer() {
        return ownerPlayer;
    }

    public void setOwnerPlayer(Player ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }

    public Textures getTextures() {
        return textures;
    }

    public void setTextures(Textures textures) {
        this.textures = textures;
    }
}
