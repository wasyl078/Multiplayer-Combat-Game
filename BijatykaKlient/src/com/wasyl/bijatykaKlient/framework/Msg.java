package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.objects.GameObject;
import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.objects.bullets.PistolBullet;
import com.wasyl.bijatykaKlient.textures.Textures;

import java.util.LinkedList;

public class Msg {

    //ważne zmienne
    private Game game;
    private DrawHandler drawHandler;
    private LinkedList<Player> players;
    private LinkedList<GameObject> objects;
    private Textures textures;
    private int lastPlayersNumber = 0;
    private int messagesCounterToDown = 10;


    //konstruktor klasy
    public Msg(Game game, Textures textures) {
        this.game = game;
        this.drawHandler = game.getDrawHandler();
        players = game.getDrawHandler().players;
        objects = game.getDrawHandler().objects;
        this.textures = textures;
    }


    //metoda interpretująca wiadomość z serwera
    public void interpretujJednaWiadomoscZSerwera(String wiadom) {



        //wstępne rozdzielenie wiadomośći z serwera
        String[] parts = wiadom.split("_");
        int posX;
        int posY;
        int direction;
        int numberOfPlayers = Integer.valueOf(parts[0]);
        int characterImageNumber;
        int lastWeapon;

        //odczekanie dziesięciu pierwszych wiadomości, żeby mieć pewność, że pierwsza będzie prawidłowa
        if (messagesCounterToDown != 0) {
            messagesCounterToDown--;
            if (messagesCounterToDown == 1) Game.thisIndividualPlayerNumber = numberOfPlayers;
            return;
        }

        //pododawnie wszystkich obecnych graczy
        while (numberOfPlayers != lastPlayersNumber) {
            lastPlayersNumber++;
            drawHandler.addPlayer(Integer.parseInt(parts[lastPlayersNumber+1].substring(0, 1)), lastPlayersNumber);
        }


        //nowe PistolBullety
        String[] bufPistolBullet = parts[1].split("\\.");
        if (bufPistolBullet[0].equals("1")) {
            int bufDir = Integer.parseInt(bufPistolBullet[1]);
            int bufPosX = Integer.parseInt(bufPistolBullet[2]);
            int bufPosY = Integer.parseInt(bufPistolBullet[3]);
            bufPosX = (int) Game.screenWidth * bufPosX / 1920;
            bufPosY = (int) Game.screenHeight * bufPosY / 1080;
            objects.add(new PistolBullet(bufPosX, bufPosY, bufDir, textures, objects));
        }

        //poustawianie pozycji wszystkich graczy na ekranie
        for (int i = 2; i <= numberOfPlayers + 1; i++) {
            String[] buf = parts[i].split("\\.");

            characterImageNumber = Integer.parseInt(buf[0]);
            direction = Integer.parseInt(buf[1]);
            lastWeapon = Integer.parseInt(buf[2]);
            posX = Integer.parseInt(buf[3]);
            posY = Integer.parseInt(buf[4]);


            //korekta pozycji względem rozmiarów ekranu
            posX = (int) Game.screenWidth * posX / 1920;
            posY = (int) Game.screenHeight * posY / 1080;

            for (int j = 0; j < players.size(); j++) {
                Player bufPlayer = players.get(j);
                if (i-1 == players.get(j).getPlayerNumber()) {
                    bufPlayer.setLastWeapon(lastWeapon);
                    bufPlayer.setDirection(direction);
                    bufPlayer.setCharacterImageNumber(characterImageNumber);
                    bufPlayer.setPositionX(posX);
                    bufPlayer.setPositionY(posY);
                }
            }
        }
    }


    //metoda tworząca wiadomość na serwer
    public String createMessageToSendToServer() {
        String bufWiadom = "";
        int bufPlayerLastAction = game.getPlayerLastAction();
        if (bufPlayerLastAction == -1) return null;

        int bufThisIndividualPlayerNumber = game.getThisIndividualPlayerNumber();

        bufWiadom += bufThisIndividualPlayerNumber + "_" + Game.isChoosen + "_" + game.getAttack() + "_" + game.getWeaponNumber() + "_" + bufPlayerLastAction;

        //System.out.println(bufWiadom);
        return bufWiadom;
    }
}