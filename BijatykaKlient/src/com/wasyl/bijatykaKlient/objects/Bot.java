package com.wasyl.bijatykaKlient.objects;

import com.wasyl.bijatykaKlient.framework.Game;
import com.wasyl.bijatykaKlient.textures.Textures;

import java.util.LinkedList;

public class Bot {

    private Player player;
    private Textures textures;
    private Game game;
    private double theClosestDistanceZ;
    private int attackCounter = 10;
    private boolean work = false;
    private int respawnCounter;
    private int direction;

    public Bot(Player player, Textures textures, Game game) {
        this.player = player;
        this.textures = textures;
        this.game = game;
    }


    public void update(LinkedList<GameObject> object) {

        direction = player.getDirection();
        Player playerToFollow = null;

        //znalezienie najbliższego gracza
        theClosestDistanceZ = 1000000;
        for (int i = 0; i < object.size(); i++) {
            if (object.get(i).getClass().equals(Player.class)) {
                Player bufPlayer = (Player) object.get(i);
                if (bufPlayer.getPlayerNumber() != player.getPlayerNumber()) {
                    int distanceX = (int) (bufPlayer.getPositionX() - player.getPositionX());
                    int distanceY = (int) (bufPlayer.getPositionY() - player.getPositionY());
                    double distanceZ = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                    if (theClosestDistanceZ > distanceZ) {
                        theClosestDistanceZ = distanceZ;
                        playerToFollow = bufPlayer;
                    }
                }
            }
        }

        //obliczenie jaka akcja ma być wykonana
        if (work) {
            if (playerToFollow != null) {
                double odl = playerToFollow.getPositionX() - player.getPositionX();
                double bezpOdl = odl;

                if (odl < 0) bezpOdl *= -1;

                if (bezpOdl >= playerToFollow.getPlayerImageWidth() * 2) {
                    if (odl < 0) {
                        game.setPlayerLastAction(4);
                    } else {
                        game.setPlayerLastAction(2);
                    }
                } else game.setPlayerLastAction(0);

                int odlDoZatrzymania = (int) (textures.getMieczSwietlnyAtakPrawoImage().getWidth() * 0.9);

                if (bezpOdl < odlDoZatrzymania && attackCounter < 20) {
                    if (odl > 0 && direction == 1) game.setPlayerLastAction(2);
                    else if (odl < 0 && direction == 2) game.setPlayerLastAction(4);
                    else game.setPlayerLastAction(0);

                    game.setAttack(1);
                    if (attackCounter == 0) {
                        attackCounter = 70;
                        game.setAttack(0);
                    }
                    attackCounter--;
                    if (attackCounter < 0) attackCounter = 0;
                } else {
                    game.setAttack(0);
                    attackCounter--;
                    if (attackCounter < 0) attackCounter = 0;
                }

                if (playerToFollow.getPositionY() < player.getPositionY()) game.setPlayerLastAction(1);
            }
        } else game.setPlayerLastAction(0);

        if (player.getPositionY() > 1.111 * Game.screenHeight) {
            work = false;
            respawnCounter = 10;
        } else if (respawnCounter == 0) {
            work = true;
        }
        respawnCounter--;
        if (respawnCounter < 0) respawnCounter = 0;
    }
}