package com.wasyl.bijatykaSerwer.objects;

import com.wasyl.bijatykaSerwer.textures.Textures;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedList;

public class Explosion extends GameObject {

    Textures textures;
    Image grenadeImage;
    Image[] explosionsImage;
    private int timeCounter = 120;
    private int bufDivide;
    private String active = "t";


    public Explosion(double x, double y, ID id, Textures textures) {
        super(x, y, id);
        this.textures = textures;
        this.explosionsImage = textures.getExplosions();
        this.grenadeImage = textures.getGrenadeLeft();
    }


    @Override
    public void update(LinkedList<GameObject> objects, ArrayList<Integer> sounds) {
        timeCounter--;
        if (timeCounter < 0) {
            timeCounter = 0;
            setActive("n");
        }
        bufDivide = timeCounter / 20;

        collisions(objects,sounds);
    }

    private void collisions(LinkedList<GameObject> objects,ArrayList<Integer>sounds) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getId().equals(ID.Player)) {
                Player bufPlayer = (Player) objects.get(i);
                if (getBounds().intersects(bufPlayer.getBoundsRight())) { //w lewo
                    if (bufPlayer.getHittedCounter() <= 0) {
                        bufPlayer.setHittedCounter(198);
                        bufPlayer.setDirectionToRecoil(-1);
                        bufPlayer.setGivePenalty(true);
                        bufPlayer.setPenaltyHPcounter(200);
                    }
                } else if(getBounds().intersects(bufPlayer.getBoundsLeft()))  {  //w prawo
                    if (bufPlayer.getHittedCounter() <= 0) {
                        bufPlayer.setHittedCounter(198);
                        bufPlayer.setDirectionToRecoil(1);
                        bufPlayer.setGivePenalty(true);
                        bufPlayer.setPenaltyHPcounter(200);
                    }
                }
            }
        }
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(getPositionX() - explosionsImage[bufDivide].getWidth()/2 + textures.getGrenadeRight().getWidth()/2,getPositionY() - explosionsImage[bufDivide].getHeight()/2 + textures.getGrenadeRight().getHeight()/2,explosionsImage[bufDivide].getWidth(),explosionsImage[bufDivide].getHeight());
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
