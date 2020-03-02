package com.wasyl.mcgclient.objects.gameobjects.distance;

import com.wasyl.mcgclient.objects.gameobjects.GameObject;
import com.wasyl.mcgclient.objects.gameobjects.characters.Player;
import javafx.scene.image.Image;

public abstract class DistanceArmas extends GameObject {

    private Player ownerPlayer;
    private Image leftImage;
    private Image rightImage;

    public DistanceArmas(int x, int y,Player ownerPlayer) {
        super(x, y);
        this.ownerPlayer = ownerPlayer;
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
