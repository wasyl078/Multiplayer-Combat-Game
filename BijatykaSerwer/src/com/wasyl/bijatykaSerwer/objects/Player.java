package com.wasyl.bijatykaSerwer.objects;

import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.scene.image.Image;

import java.util.LinkedList;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Player extends GameObject{

    private Textures textures;
    private Image image;
    private int playerNumber;
    private int lastAction;

    public Player(int x, int y, int playerNumber , Textures textures) {
        super(x,y);
        this.textures = textures;
        this.image = toFXImage(textures.botPrawoImage,null);
        setPlayerNumber(playerNumber);
    }



    @Override
    public void update(LinkedList<GameObject> objects) {
        setPositionX(getPositionX() + getVelocityX());
        setPositionY(getPositionY() + getVelocityY());
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getLastAction() {
        return lastAction;
    }


}
