package com.wasyl.bijatykaSerwer.objects;


import com.wasyl.bijatykaSerwer.textures.Textures;

import java.util.ArrayList;
import java.util.LinkedList;

public class UpdateHandler {

    public LinkedList<GameObject> objects;
    public LinkedList<Player> players;
    public ArrayList<Integer>sounds;
    private Textures textures;

    public UpdateHandler(Textures textures) {
        this.textures = textures;
        objects = new LinkedList<GameObject>();
        players = new LinkedList<Player>();
        sounds = new ArrayList<Integer>();
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getAlive().equals("t"))
                objects.get(i).update(objects, sounds);
        }
    }

    public void makeFirstLevel() {
        double wdth = 1920;
        double hght = 1080;

        addObject(new Platform(wdth / 6, hght * 10 / 12, ID.Platform, (int) wdth * 2 / 3, (int) hght / 48));
        addObject(new Platform(wdth / 8, hght * 8 / 12, ID.Platform, (int) wdth / 4, (int) hght / 48));
        addObject(new Platform(wdth * 5 / 8, hght * 8 / 12, ID.Platform, (int) wdth / 4, (int) hght / 48));

    }

    public void addPlayer(int playerNumber) {
        Player bufPlayer = new Player(999999, -999999, playerNumber, ID.Player, textures, 3);
        objects.add(bufPlayer);
        players.add(bufPlayer);
    }

    public void addObject(GameObject bufObject) {
        objects.add(bufObject);

    }
}
