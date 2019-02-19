package com.wasyl.bijatykaSerwer.objects;


import com.wasyl.bijatykaSerwer.textures.Textures;

import java.util.LinkedList;

public class UpdateHandler {

    public LinkedList<GameObject> objects;
    public LinkedList<Player> players;
    private Textures textures;

    public UpdateHandler(Textures textures) {
        this.textures = textures;
        objects = new LinkedList<GameObject>();
        players = new LinkedList<Player>();
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(objects);
        }
    }

    public void addPlayer(int playerNumber){
        Player bufPlayer = new Player(69,200,playerNumber,textures);
        objects.add(bufPlayer);
        players.add(bufPlayer);
    }
}
