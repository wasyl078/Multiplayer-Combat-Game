package com.wasyl.bijatykaKlient.framework;


import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Player;

import java.util.LinkedList;

public class Camera {

    private int positionX = 0;
    private int positionY = 0;
    private LinkedList<Player> players;

    public Camera(LinkedList<Player> players) {
        this.players = players;
    }


    public void update() {
        int numberOfPlayers = 0;
        int bufPos = 0;

        for (int i = 0; i < players.size(); i++) {
            Player bufPlayer = players.get(i);
            if (bufPlayer.getAlive().equals("t")) {
                bufPos += -bufPlayer.getPositionX() + Game.screenWidth / 2;
                numberOfPlayers++;
            }
        }
        if (numberOfPlayers != 0) {
            bufPos = bufPos / numberOfPlayers;
            setPositionX(bufPos);
        }
    }


    //gettery i settery
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}