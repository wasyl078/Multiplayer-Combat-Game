package com.wasyl.mcgclient.objects.gameobjects.characters;

import com.wasyl.mcgclient.general.Game;
import com.wasyl.mcgclient.objects.gameobjects.GameObject;
import com.wasyl.mcgclient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Healthbar extends GameObject {

    private Image barImage;
    private Player ownerPlayer;
    private Image playerImage;
    private double lastHP = -1;
    private Color HPclr;

    public Healthbar(int x, int y, Player ownerPlayer, Textures textures) {
        super(x, y);
        this.barImage = textures.getHealthbar1();
        this.ownerPlayer = ownerPlayer;
        setPositionX((int)(0.0364583*Game.screenWidth + 0.24114583*Game.screenWidth*((ownerPlayer.getPlayerNumber() - 1)%4)));
        setPositionY((int)(0.05*Game.screenHeight + (int)((ownerPlayer.getPlayerNumber()-1)/4)*0.08*Game.screenHeight));
        this.playerImage = textures.getMieczSwietlnyAtakPrawoImage();
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        double HP = ownerPlayer.getHealth();
        double r;
        double g;
        double b = 0;

        if (lastHP != HP && HP>=0) {

            if (HP > 500) {
                g = 1000;
                r = 2000 - 2 * HP;
            } else {
                r = 1000;
                g = 2 * HP;
            }
            HPclr = new Color(r / 1000, g / 1000, b / 1000, 1);
        }

        if(HP != 0){
            gc.setFill(HPclr);
            gc.fillRect((int) (getPositionX() + 0.0294117 * barImage.getWidth()), (int) (getPositionY() + 0.2325581 * barImage.getHeight()), (int) (0.9511764 * barImage.getWidth() * HP / 1000), (int) (0.697619 * barImage.getHeight()));

            gc.drawImage(playerImage,(int) (getPositionX() + 1.05*barImage.getWidth()),(int) (getPositionY() + 0.2325581 * barImage.getHeight()) - 0.3*playerImage.getHeight());

            gc.drawImage(barImage, getPositionX(), getPositionY());

            lastHP = HP;
        }
    }

    public void setPlayerImageAtHealthBar(Image playerImage){
        this.playerImage = playerImage;
    }
}
