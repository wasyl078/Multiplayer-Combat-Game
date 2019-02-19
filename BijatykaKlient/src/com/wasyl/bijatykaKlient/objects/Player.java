package com.wasyl.bijatykaKlient.objects;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Player extends GameObject{

    private Textures textures;
    private Image image;
    private int playerNumber;
    private int lastAction;

    public Player(int x, int y,int playerNumber ,Textures textures) {
        super(x,y);
        this.playerNumber = playerNumber;
        this.textures = textures;
        this.image = toFXImage(textures.botPrawoImage,null);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image,getPositionX(),getPositionY());
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
