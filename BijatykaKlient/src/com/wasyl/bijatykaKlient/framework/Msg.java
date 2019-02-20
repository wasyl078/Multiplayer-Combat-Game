package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.objects.Player;

import java.util.LinkedList;

public class Msg {

    //ważne zmienne
    private Game game;
    private DrawHandler drawHandler;
    private LinkedList<Player> players;
    private int lastPlayersNumber = 0;
    private int messagesCounterToDown = 10;

    //konstruktor klasy
    public Msg(Game game) {
        this.game = game;
        this.drawHandler = game.getDrawHandler();
        players = game.getDrawHandler().players;
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

        //odczekanie dziesięciu pierwszych wiadomości, żeby mieć pewność, że pierwsza będzie prawidłowa
        if (messagesCounterToDown != 0) {
            messagesCounterToDown--;
            if (messagesCounterToDown == 1) game.setThisIndividualPlayerNumber(numberOfPlayers);
            return;
        }

        //pododawnie wszystkich obecnych graczy
        while (numberOfPlayers != lastPlayersNumber) {
            lastPlayersNumber++;
            drawHandler.addPlayer(Integer.parseInt(parts[lastPlayersNumber].substring(0, 1)), lastPlayersNumber);
            System.out.println(Integer.parseInt(parts[lastPlayersNumber].substring(0, 1)));
        }

        //poustawianie pozycji wszystkich graczy na ekranie
        for (int i = 1; i <= numberOfPlayers; i++) {
            String[] buf = parts[i].split("\\.");

            characterImageNumber = Integer.parseInt(buf[0]);
            direction = Integer.parseInt(buf[1]);
            posX = Integer.parseInt(buf[2]);
            posY = Integer.parseInt(buf[3]);

            //korekta pozycji względem rozmiarów ekranu
            posX = (int) Game.screenWidth * posX / 1920;
            posY = (int) Game.screenHeight * posY / 1080;


            for (int j = 0; j < players.size(); j++) {
                Player bufPlayer = players.get(j);
                if (i == players.get(j).getPlayerNumber()) {
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

        bufWiadom += bufThisIndividualPlayerNumber + "_" + Game.isChoosen + "_" + bufPlayerLastAction;

        System.out.println(bufWiadom);
        return bufWiadom;
    }
}