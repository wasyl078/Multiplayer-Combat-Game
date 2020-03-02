package com.wasyl.mcgclient.objects.gameobjects.characters;

import com.wasyl.mcgclient.general.Game;
import com.wasyl.mcgclient.objects.gameobjects.GameObject;
import com.wasyl.mcgclient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends GameObject {


    //ważne obiekty;
    private Image image;


    public Background(int x, int y, Textures textures) {
        super(x, y);
        this.image = textures.getBackground2();
    }


    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        setPositionX(-Game.screenWidth/2 + cpx/4);
        gc.drawImage(image,getPositionX(),getPositionY());
    }
}
