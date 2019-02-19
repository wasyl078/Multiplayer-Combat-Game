package com.wasyl.bijatykaKlient.objects;

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

    public void draw(GraphicsContext gc) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).draw(gc);
        }
    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void addPlayer(int playerNumber){
        Player bufPlayer = new Player(0,0,playerNumber,textures);
        objects.add(bufPlayer);
        players.add(bufPlayer);
    }
}
