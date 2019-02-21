package com.wasyl.bijatykaKlient.objects.gameobjects.characters;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.objects.gameobjects.GameObject;
import com.wasyl.bijatykaKlient.textures.Textures;
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
