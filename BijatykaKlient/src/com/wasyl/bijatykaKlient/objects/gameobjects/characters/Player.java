package com.wasyl.bijatykaKlient.objects.gameobjects.characters;

import com.wasyl.bijatykaKlient.objects.gameobjects.GameObject;
import com.wasyl.bijatykaKlient.objects.gameobjects.distance.DistancePistol;
import com.wasyl.bijatykaKlient.objects.gameobjects.melee.MeleeAxe;
import com.wasyl.bijatykaKlient.objects.gameobjects.melee.MeleeLightSaber;
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
    private Healthbar healthbar;
    private double health;

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
        distancePistol = new DistancePistol(0, 0, this, textures);
        healthbar = new Healthbar(0,0,this, textures);
        setCharacterImageNumber(characterNumber);
    }


    //rysowanie odpoiwedniego obrazka postaci
    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {

        if (direction == 1) gc.drawImage(imageLeft, getPositionX() + cpx, getPositionY() + cpy);
        else gc.drawImage(imageRight, getPositionX() + cpx, getPositionY() + cpy);

        meleeLightSaber.draw(gc, cpx, cpy);
        meleeAxe.draw(gc, cpx, cpy);
        distancePistol.draw(gc, cpx, cpy);
        healthbar.draw(gc, cpx, cpy);
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
            healthbar.setPlayerImageAtHealthBar(imageRight);
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

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
