package com.wasyl.mcgclient.objects.gameobjects.characters;

import com.wasyl.mcgclient.general.Game;
import com.wasyl.mcgclient.objects.gameobjects.GameObject;
import com.wasyl.mcgclient.textures.Textures;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.Random;

public class Bot extends GameObject {

    private Game game;
    private Textures textures;
    private double theClosestDistanceZ;
    private int attackCounter = 10;
    private boolean work = false;
    private int respawnCounter;
    private int direction;
    private double odlDoZatrzymania;
    private boolean ableToCorrectPosition = true;
    private int panicMode = 0;
    private Random random = new Random();

    public Bot(int x, int y, Textures textures, Game game) {
        super(x, y);
        this.game = game;
        this.textures = textures;
        this.odlDoZatrzymania = textures.getMieczSwietlnyAtakPrawoImage().getWidth() * 0.9;
    }


    private void update() {

        LinkedList<GameObject> object = game.getDrawHandler().objects;
        Player player = null;
        for (int i = 0; i < object.size(); i++) {
            if (object.get(i).getClass().equals(Player.class)) {
                if (((Player) object.get(i)).getPlayerNumber() == Game.thisIndividualPlayerNumber) {
                    player = (Player) object.get(i);
                }
            }
        }

        if (player == null) return;

        if (player.getHealth() < 100) game.setWeaponNumber(4);
        else if (player.getHealth() > 500) game.setWeaponNumber(1);

        if (game.getWeaponNumber() == 1) odlDoZatrzymania = textures.getMieczSwietlnyAtakPrawoImage().getWidth() * 0.9;
        else if (game.getWeaponNumber() == 2) odlDoZatrzymania = textures.getToporAtakLewoImage().getWidth() * 0.9;
        else if (game.getWeaponNumber() == 3) odlDoZatrzymania = textures.getPistoletLewo().getWidth() * 3;
        else if (game.getWeaponNumber() == 4)
            odlDoZatrzymania = textures.getMieczSwietlnyAtakPrawoImage().getWidth() * 1.3;
        else if (game.getWeaponNumber() == 5) odlDoZatrzymania = (double) Game.screenWidth * 0.5;

        direction = player.getDirection();
        Player playerToFollow = null;

        //znalezienie najbliższego gracza
        theClosestDistanceZ = 1000000;
        for (int i = 0; i < object.size(); i++) {
            if (object.get(i).getClass().equals(Player.class)) {
                Player bufPlayer = (Player) object.get(i);
                if (bufPlayer.getPlayerNumber() != player.getPlayerNumber()) {
                    if (bufPlayer.getPositionX() > 0.0973958 * Game.screenWidth && bufPlayer.getPositionX() < 0.8765625 * Game.screenWidth && bufPlayer.getPositionY() < 0.9759259 * Game.screenHeight) {
                        int distanceX = bufPlayer.getPositionX() - player.getPositionX();
                        int distanceY = bufPlayer.getPositionY() - player.getPositionY();
                        double distanceZ = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                        if (theClosestDistanceZ > distanceZ) {
                            theClosestDistanceZ = distanceZ;
                            playerToFollow = bufPlayer;
                        }
                    }

                }
            }
        }

        //obliczenie jaka akcja ma być wykonana
        if (work) {
            if (game.getWeaponNumber() != 4 && panicMode == 0) {
                if (playerToFollow != null) {
                    double odl = playerToFollow.getPositionX() - player.getPositionX();
                    double bezwzOdl = odl;

                    if (odl < 0) bezwzOdl *= -1;

                    if (bezwzOdl >= odlDoZatrzymania) {
                        if (odl < 0) {
                            game.setPlayerLastAction(4);
                        } else {
                            game.setPlayerLastAction(2);
                        }
                    } else if (bezwzOdl < odlDoZatrzymania) {


                        if (ableToCorrectPosition) {
                            if (player.getPositionX() > playerToFollow.getPositionX()) game.setPlayerLastAction(4);
                            else if (player.getPositionX() < playerToFollow.getPositionX()) game.setPlayerLastAction(2);
                            ableToCorrectPosition = false;
                        } else game.setPlayerLastAction(0);


                        if (attackCounter == 0) {
                            attackCounter = 30;
                            game.setAttack(1);
                            ableToCorrectPosition = true;
                        } else game.setAttack(0);

                        attackCounter--;
                        if (attackCounter < 0) attackCounter = 0;
                    }

                    if (playerToFollow.getPositionY() < player.getPositionY()) game.setPlayerLastAction(1);

                } else {
                    if (player.getPositionX() < 0.0973958 * Game.screenWidth) game.setPlayerLastAction(2);
                    else if (player.getPositionX() > 0.8765625 * Game.screenWidth) game.setPlayerLastAction(4);
                    else if (player.getPositionY() > 0.9759259 * Game.screenHeight) game.setPlayerLastAction(1);
                    // else game.setPlayerLastAction(0);
                }
            } else if (game.getWeaponNumber() == 4 && panicMode == 0) {
                game.setAttack(1);
                if (playerToFollow != null) {
                    double odl = playerToFollow.getPositionX() - player.getPositionX();
                    double bezwzOdl = odl;
                    if (odl < 0) bezwzOdl *= -1;
                    if (bezwzOdl < odlDoZatrzymania) {
                        if (odl < 0) {
                            game.setPlayerLastAction(2);
                        } else {
                            game.setPlayerLastAction(4);
                        }
                        ableToCorrectPosition = true;
                        System.out.println(game.getPlayerLastAction());
                    } else if (bezwzOdl > odlDoZatrzymania * 2) {
                        if (ableToCorrectPosition || player.getDirection() == playerToFollow.getDirection()) {
                            if (player.getPositionX() > playerToFollow.getPositionX()) game.setPlayerLastAction(4);
                            else if (player.getPositionX() < playerToFollow.getPositionX()) game.setPlayerLastAction(2);
                            ableToCorrectPosition = false;

                            System.out.println("korekta " + game.getPlayerLastAction());

                        } else game.setPlayerLastAction(0);
                    } else game.setPlayerLastAction(0);
                }

                if (player.getPositionX() < 0.16 * Game.screenWidth) panicMode = 2;
                else if (player.getPositionX() > 0.82 * Game.screenWidth) panicMode = 1;

            } else if (panicMode != 0) {
                if(panicMode == 1){
                    game.setPlayerLastAction(4);
                    if(player.getPositionX() < Game.screenWidth * 0.25) panicMode = 0;
                }else {
                    game.setPlayerLastAction(2);
                    if(player.getPositionX() > Game.screenWidth * 0.75) panicMode = 0;
                }
                if(random.nextInt(100) >90) game.setPlayerLastAction(1);
                game.setWeaponNumber(4);
                game.setAttack(1);
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

    @Override
    public void draw(GraphicsContext gc, int cpx, int cpy) {
        if (Game.isChoosen == 3) update();
    }
}