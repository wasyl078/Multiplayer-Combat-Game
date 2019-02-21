package com.wasyl.bijatykaKlient.objects;

import com.wasyl.bijatykaKlient.objects.distance.DistancePistol;
import com.wasyl.bijatykaKlient.objects.melee.MeleeAxe;
import com.wasyl.bijatykaKlient.objects.melee.MeleeLightSaber;
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
    private double PlayerImageWidth;
    private double PlayerImageHeight;

    //związane z broniami
    private MeleeLightSaber meleeLightSaber;
    private MeleeAxe meleeAxe;
    private DistancePistol distancePistol;
    private int lastWeapon = 0;


    //konstruktor klasy
    public Player(int x, int y, int characterNumber, int playerNumber, Textures textures) {
        super(x, y);
        this.playerNumber = playerNumber;
        this.textures = textures;
        meleeLightSaber = new MeleeLightSaber(0, 0, this, textures);
        meleeAxe = new MeleeAxe(0, 0, this, textures);
        distancePistol = new DistancePistol(0,0,this,textures);
        setCharacterImageNumber(characterNumber);
    }


    //rysowanie odpoiwedniego obrazka postaci
    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        //setPositionX(getPositionX() + cpx);
        //setPositionY(getPositionY() + cpy);

        if (direction == 1) {
            gc.drawImage(imageLeft, getPositionX()+cpx, getPositionY()+cpy);
            System.out.println("PLAYER " + playerNumber + " x = " +getPositionX()+cpx);
        }
        else {
            gc.drawImage(imageRight, getPositionX()+cpx, getPositionY()+cpy);
            System.out.println("PLAYER " + playerNumber + " x = " +getPositionX()+cpx);
        }

        meleeLightSaber.draw(gc, cpx, cpy);
        meleeAxe.draw(gc, cpx, cpy);
        distancePistol.draw(gc, cpx, cpy);
    }


    //ustalenie jakim obrazkiem jest ten gracz
    public void setCharacterImageNumber(int imageNumber) {
        if (characterImageNumber == imageNumber) return;
        if (imageNumber == 1) {
            imageLeft = textures.getLukaszLewoImage();
            imageRight = textures.getLukaszPrawoImage();
            PlayerImageHeight = textures.getLukaszLewoImage().getHeight();
            PlayerImageWidth = textures.getLukaszPrawoImage().getWidth();
        } else if (imageNumber == 2) {
            imageLeft = textures.getMaciekLewoImage();
            imageRight = textures.getMaciekPrawoImage();
            PlayerImageHeight = textures.getMaciekLewoImage().getHeight();
            PlayerImageWidth = textures.getMaciekPrawoImage().getWidth();
        } else if (imageNumber == 3) {
            imageLeft = textures.getBotLewoImage();
            imageRight = textures.getBotPrawoImage();
            PlayerImageHeight = textures.getBotLewoImage().getHeight();
            PlayerImageWidth = textures.getBotPrawoImage().getWidth();
        }
        characterImageNumber = imageNumber;
        if (characterImageNumber != 0) {
            meleeLightSaber.calculateWeaponPosition();
            meleeAxe.calculateWeaponPosition();
            distancePistol.calculateWeaponPosition();
        }
    }


    //getttery i settery
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getPlayerImageWidth() {
        return PlayerImageWidth;
    }

    public double getPlayerImageHeight() {
        return PlayerImageHeight;
    }

    public int getLastWeapon() {
        return lastWeapon;
    }

    public void setLastWeapon(int lastWeapon) {
        this.lastWeapon = lastWeapon;
    }

    public int getDirection() {
        return direction;
    }

}
