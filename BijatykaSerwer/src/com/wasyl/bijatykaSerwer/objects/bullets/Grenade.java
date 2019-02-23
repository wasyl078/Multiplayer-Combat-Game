package com.wasyl.bijatykaSerwer.objects.bullets;

import com.wasyl.bijatykaSerwer.framework.Game;
import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Platform;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grenade extends GameObject {

    private GameObject bufGameObject;
    private int defVel = 10;
    public static int grenades = 0;
    public static int grenadesInGeneral = 0;
    private int individualGrenadeNumber;
    private int direction;
    private Textures textures;
    private Image leftImage;
    private Image rightImage;
    private double imageWidth;
    private double imageHeight;
    private String active = "t";
    private final double gravity = 0.5;
    private double air = 0.05;
    private final double maxSpeed = 30;
    private boolean falling = true;
    private boolean jumping = true;
    private int countdown = 500;
    private double jumpDivider = 1;
    private double lastPosX = 0;

    public Grenade(double x, double y, ID id, int direction, Textures textures) {
        super(x, y, id);
        if (direction == 1) setVelocityX(-defVel);
        else setVelocityX(defVel);
        setVelocityY(-defVel * 1.75);
        this.direction = direction;
        this.leftImage = textures.getGrenadeLeft();
        this.rightImage = textures.getGrenadeRight();
        this.imageWidth = leftImage.getWidth();
        this.imageHeight = rightImage.getHeight();
        grenades++;
        grenadesInGeneral++;
        individualGrenadeNumber = grenadesInGeneral;
    }

    @Override
    public void update(LinkedList<GameObject> objects, ArrayList<Integer> sounds) {
        setPositionX(getPositionX() + getVelocityX());
        setPositionY(getPositionY() + getVelocityY());
        //grawitacja
        if (falling || jumping) {
            setVelocityY(getVelocityY() + gravity);
            if (getVelocityY() > maxSpeed) setVelocityY(maxSpeed);
        }

        //opór powietrza XD
        if (getVelocityX() > 0) setVelocityX(getVelocityX() - air);
        else if (getVelocityX() < 0) setVelocityX(getVelocityX() +air);
        countdown--;
        air+=0.00000075;
        if (countdown < 0) countdown = 0;

        collisions(objects, sounds);

        if (getPositionX() - lastPosX < 0) direction = 1;
        else if (getPositionX() - lastPosX > 0) direction = 2;
        lastPosX = (int) getPositionX();


        if (getPositionX() > Game.screenWidth * 2 || getPositionX() < -Game.screenWidth || getPositionY()>Game.screenHeight - 2*imageHeight) {
            setActive("n");
        }
    }


    private void collisions(LinkedList<GameObject> objects, ArrayList<Integer> sounds) {

        //kolizja z platformami
        for (int i = 0; i < objects.size(); i++) {
            bufGameObject = objects.get(i);

            if (bufGameObject.getId() == ID.Platform) {

                //kolizja z podłogą
                if (getBounds().intersects(bufGameObject.getBounds())) {
                    setPositionY(bufGameObject.getPositionY() - imageHeight);
                    setVelocityY(-1.75 * defVel*jumpDivider);
                    jumpDivider*=0.7;
                    if(jumpDivider>0.02)sounds.add(-4);
                    else if(jumpDivider<0.001)setActive("n");
                    setFalling(false);
                    setJumping(true);
                } else setFalling(true);

                //kolizja z sufitem
                if (getBoundsTop().intersects(bufGameObject.getBounds())) {
                    setPositionY(bufGameObject.getPositionY() + ((Platform) bufGameObject).getHeight());
                    setVelocityY(0);
                    sounds.add(-4);
                } else setFalling(true);

                //kolizja ze ścianą (od lewej strony)
                if (getBoundsLeft().intersects(bufGameObject.getBounds())) {
                    setPositionX(getPositionX() + getVelocityX());
                    setVelocityX(-getVelocityX());
                    sounds.add(-4);
                }

                //kolizja ze ścianą (od prawej strony)
                if (getBoundsRight().intersects(bufGameObject.getBounds())) {
                    setPositionX(getPositionX() - getVelocityX());
                    setVelocityX(-getVelocityX());
                    sounds.add(-4);
                }
            }
        }

        //kolizja z graczami
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId().equals(ID.Player)) {
                Player bufPlayer = (Player) objects.get(i);
                if (getBoundsCentral().intersects(bufPlayer.getBoundsRight())) { //w lewo
                    if (bufPlayer.getHittedCounter() <= 0) {
                        bufPlayer.setHittedCounter(198);
                        bufPlayer.setDirectionToRecoil(-1);
                        bufPlayer.setGivePenalty(true);
                        bufPlayer.setPenaltyHPcounter(200);
                        sounds.add(-5);
                    }
                    setActive("n");
                } else if(getBoundsCentral().intersects(bufPlayer.getBoundsLeft()))  {  //w prawo
                    if (bufPlayer.getHittedCounter() <= 0) {
                        bufPlayer.setHittedCounter(198);
                        bufPlayer.setDirectionToRecoil(1);
                        bufPlayer.setGivePenalty(true);
                        bufPlayer.setPenaltyHPcounter(200);
                        sounds.add(-5);
                    }
                    setActive("n");
                }
            }
        }
    }

    //bounds
    @Override
    public Rectangle2D getBounds() /*Bottom */ {
        return new Rectangle2D((int) getPositionX() + imageWidth / 5, (int) getPositionY() + imageHeight/ 2, imageWidth * 3 / 5, imageHeight / 2);
    }

    public Rectangle2D getBoundsTop() {
        return new Rectangle2D((int) getPositionX() + imageWidth / 5, (int) getPositionY(), imageWidth * 3 / 5, imageHeight / 4);
    }

    public Rectangle2D getBoundsLeft() {
        return new Rectangle2D((int) getPositionX(), (int) getPositionY() + imageHeight / 4, imageWidth / 5, imageHeight / 2);
    }

    public Rectangle2D getBoundsRight() {
        return new Rectangle2D((int) getPositionX() + imageWidth * 4 / 5, (int) getPositionY() + imageHeight / 4, imageWidth / 5, imageHeight / 2);
    }

    public Rectangle2D getBoundsCentral() {
        return new Rectangle2D((int) getPositionX(), (int) getPositionY(), imageWidth, imageHeight);
    }


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getIndividualGrenadeNumber() {
        return individualGrenadeNumber;
    }

    public int getDirection() {
        return direction;
    }
}
