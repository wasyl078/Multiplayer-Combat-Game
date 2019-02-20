package com.wasyl.bijatykaKlient.objects;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.framework.Msg;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class DrawHandler {

    public LinkedList<GameObject> objects;
    public LinkedList<Player> players;
    private Textures textures;

    public DrawHandler(Textures textures) {
        this.textures = textures;
        objects = new LinkedList<GameObject>();
        players = new LinkedList<Player>();
    }

    public void draw(GraphicsContext gc,int cpx,int cpy) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(gc,cpx,cpy);
        }
    }

    public void makeFirstLevel(){
        double wdth = Game.screenWidth;
        double hght = Game.screenHeight;

        addObject(new Platform(wdth / 6, hght * 10 / 12, (int) wdth * 2 / 3, (int) hght / 48));
        addObject(new Platform(wdth / 8, hght * 8 / 12,  (int) wdth / 4, (int) hght / 48));
        addObject(new Platform(wdth * 5 / 8, hght * 8 / 12, (int) wdth / 4, (int) hght / 48));

    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void addPlayer(int characterNumber,int playerNumber){
        Player bufPlayer = new Player(0,0,characterNumber,playerNumber,textures);
        objects.add(bufPlayer);
        players.add(bufPlayer);
        if(playerNumber == Game.thisIndividualPlayerNumber) Game.actualPlayer = bufPlayer;
    }
}
