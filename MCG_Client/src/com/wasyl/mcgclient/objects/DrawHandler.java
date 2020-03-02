package com.wasyl.mcgclient.objects;

import com.wasyl.mcgclient.general.Game;
import com.wasyl.mcgclient.objects.gameobjects.GameObject;
import com.wasyl.mcgclient.objects.gameobjects.characters.Platform;
import com.wasyl.mcgclient.objects.gameobjects.characters.Player;
import com.wasyl.mcgclient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class DrawHandler {

    public final LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public final LinkedList<Player> players = new LinkedList<Player>();
    private Textures textures;
    private GameObject buforGameObject;

    public DrawHandler(Textures textures) {
        this.textures = textures;
    }

    public void draw(GraphicsContext gc,int cpx,int cpy) {
        for (int i = 0; i < objects.size(); i++) {
            buforGameObject = objects.get(i);
            buforGameObject.draw(gc,cpx,cpy);
            if(buforGameObject.shouldDelete()) objects.remove(buforGameObject);
        }
    }

    public void makeFirstLevel(){
        int wdth = Game.screenWidth;
        int hght = Game.screenHeight;

        addObject(new Platform(wdth / 6, hght * 10 / 12,  wdth * 2 / 3,  hght / 48));
        addObject(new Platform(wdth / 8, hght * 8 / 12,   wdth / 4,  hght / 48));
        addObject(new Platform(wdth * 5 / 8, hght * 8 / 12, wdth / 4,  hght / 48));

    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void addPlayer(int characterNumber,int playerNumber){
        Player bufPlayer = new Player(0,0,characterNumber,playerNumber,textures);
        objects.add(bufPlayer);
        players.add(bufPlayer);
    }
}
