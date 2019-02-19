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
        int numberOfPlayers = Integer.valueOf(parts[0]);

        //odczekanie dziesięciu pierwszych wiadomości, żeby mieć pewność, że pierwsza będzie prawidłowa
        if(messagesCounterToDown!=0){
            messagesCounterToDown--;
            if(messagesCounterToDown == 1) game.setThisIndividualPlayerNumber(numberOfPlayers);
            return;
        }

        //pododawnie wszystkich obecnych graczy
        while(numberOfPlayers != lastPlayersNumber){
            lastPlayersNumber++;
            drawHandler.addPlayer(lastPlayersNumber);
        }

        //poustawianie pozycji wszystkich graczy na ekranie
        for (int i = 1; i <= numberOfPlayers; i++) {
            String[] buf = parts[i].split("\\.");
            posX = Integer.parseInt(buf[0]);
            posY = Integer.parseInt(buf[1]);

            //korekta pozycji względem rozmiarów ekranu
            posX = (int) Game.screenWidth * posX / 1920;
            posY = (int) Game.screenHeight * posY / 1080;


            for (int j = 0; j < players.size(); j++) {
                if (i == players.get(j).getPlayerNumber()) {
                    players.get(j).setPositionX(posX);
                    players.get(j).setPositionY(posY);
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

        bufWiadom += bufThisIndividualPlayerNumber + "_" + bufPlayerLastAction;

        System.out.println(bufWiadom);
        return bufWiadom;
    }
}