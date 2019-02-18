package com.wasyl.bijatykaKlient.objects;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Player extends GameObject{

    private ArrayList<String>input;
    private Textures textures;
    private Image image;

    public Player(int x, int y, ArrayList<String>input, Textures textures) {
        super(x,y);
        this.input = input;
        this.textures = textures;
        this.image = toFXImage(textures.botPrawoImage,null);
    }

    @Override
    public void update(LinkedList<GameObject> object) {
        if(input.contains("LEFT")) setVelocityX(-10);
        if(input.contains("RIGHT")) setVelocityX(10);
        if(input.contains("DOWN")) setVelocityY(10);
        if(input.contains("UP")) setVelocityY(-10);

        if(input.contains("ESCAPE"))System.exit(0);

        if(!input.contains("UP") && !input.contains("DOWN")) setVelocityY(0);
        if(!input.contains("LEFT") && !input.contains("RIGHT")) setVelocityX(0);


        setPositionX(getPositionX()+getVelocityX());
        setPositionY(getPositionY()+getVelocityY());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.drawImage(image,getPositionX(),getPositionY());
    }
}
