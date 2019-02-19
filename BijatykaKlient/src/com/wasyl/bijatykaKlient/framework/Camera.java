package com.wasyl.bijatykaKlient.framework;


import com.wasyl.bijatykaKlient.objects.Player;

import java.util.LinkedList;

public class Camera {

    private double positionX = 0;
    private double positionY = 0;
    private LinkedList<Player> players;


    public Camera(LinkedList<Player> players){
        this.players = players;
    }


    public void update() {

        int numberOfPlayers = 0;
        double bufPos = 0;
        for (int i = 0; i < players.size(); i++) {
            Player bufPlayer = players.get(i);
            if (bufPlayer.getPositionY() < 90000) {
                bufPos += -bufPlayer.getPositionX() + Game.screenWidth / 2;
                numberOfPlayers++;
            }
        }
        bufPos = bufPos / numberOfPlayers;
        setPositionX(bufPos);
    }


    //gettery i settery
    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}