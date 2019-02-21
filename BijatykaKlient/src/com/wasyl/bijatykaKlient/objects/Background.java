package com.wasyl.bijatykaKlient.objects;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background extends GameObject {


    //ważne obiekty
    private Textures textures;
    private Image image;


    public Background(double x, double y, Textures textures) {
        super(x, y);
        this.textures = textures;
        this.image = textures.getBackground2();
    }

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        setPositionX(-Game.screenWidth/2 + cpx/4);
        gc.drawImage(image,getPositionX(),getPositionY());
    }
}
