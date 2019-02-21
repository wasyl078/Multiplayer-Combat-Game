package com.wasyl.bijatykaKlient.objects.distance;

import com.wasyl.bijatykaKlient.objects.GameObject;
import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.image.Image;

public abstract class DistanceArmas extends GameObject {

    private Textures textures;
    private Player ownerPlayer;
    private Image leftImage;
    private Image rightImage;

    public DistanceArmas(double x, double y,Player ownerPlayer ,Textures textures) {
        super(x, y);
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

}
