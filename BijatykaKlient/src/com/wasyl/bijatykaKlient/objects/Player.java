package com.wasyl.bijatykaKlient.objects;

import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;

public class Player extends GameObject {

    //ważne obiekty
    private Textures textures;
    private Image imageLeft;
    private Image imageRight;

    //związane ze stanem obiektu
    private int playerNumber;
    private int direction = 1;      //1 - left   2 - right
    private int characterImageNumber = 0;

    //konstruktor klasy
    public Player(int x, int y, int characterNumber, int playerNumber, Textures textures) {
        super(x, y);
        this.playerNumber = playerNumber;
        this.textures = textures;
        setCharacterImageNumber(characterNumber);
    }


    //rysowanie odpoiwedniego obrazka postaci
    @Override
    public void draw(GraphicsContext gc) {
        if (direction == 1) gc.drawImage(imageLeft, getPositionX(), getPositionY());
        else gc.drawImage(imageRight, getPositionX(), getPositionY());
    }


    //ustalenie jakim obrazkiem jest ten gracz
    public void setCharacterImageNumber(int imageNumber) {
        if (characterImageNumber == imageNumber) return;
        if (imageNumber == 1) {
            imageLeft = textures.getLukaszLewoImage();
            imageRight = textures.getLukaszPrawoImage();
        } else if (imageNumber == 2) {
            imageLeft = textures.getMaciekLewoImage();
            imageRight = textures.getMaciekPrawoImage();
        } else if (imageNumber == 3) {
            imageLeft = textures.getBotLewoImage();
            imageRight = textures.getBotPrawoImage();
        }
        characterImageNumber = imageNumber;
    }


    //getttery
    public int getPlayerNumber() {
        return playerNumber;
    }
}
