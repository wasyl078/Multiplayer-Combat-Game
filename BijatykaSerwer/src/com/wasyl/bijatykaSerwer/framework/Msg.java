package com.wasyl.bijatykaSerwer.framework;


import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.objects.UpdateHandler;

import java.util.LinkedList;

public class Msg {

    //zmienne i obiekty
    private UpdateHandler updateHandler;
    private LinkedList<Player> playersList;


    //konstruktor klasy
    public Msg(UpdateHandler updateHandler){
        this.updateHandler = updateHandler;
        playersList = updateHandler.players;
    }

    //metoda twrząca jedną długą wiadomość do wysłania każdemu klientowi
    public String stworzJednaDlugaWiadomosc() {
        String bufWiadom = "";
        int players =playersList.size();
        bufWiadom += players;

        for (int i = 0; i < playersList.size(); i++) {
            bufWiadom += "_";
            bufWiadom += (int) playersList.get(i).getPositionX();
            bufWiadom += ".";
            bufWiadom += (int) playersList.get(i).getPositionY();
        }
        return bufWiadom;
    }


    //metoda interpretująca wiadomości od każdego klienta
    public void interpretujWiadomoscOdKlientow(String wiadom) {

        String[] parts = wiadom.split("_");
        int bufNumerGracza = Integer.parseInt(parts[0]);
        int bufAkcjaGracza = Integer.parseInt(parts[1]);

        int bufVel = 10;
        for (int i = 0; i < playersList.size(); i++) {
            Player bufPlayer = playersList.get(i);
            if (bufPlayer.getPlayerNumber() == bufNumerGracza) {

                /*
                if (bufAkcjaGracza == 1) {
                    bufPlayer.setVelocityY(-20);
                } else if (bufAkcjaGracza == 2) {
                    bufPlayer.setVelocityX(20);
                } else if (bufAkcjaGracza == 3) {
                    bufPlayer.setVelocityY(20);
                } else if (bufAkcjaGracza == 4) {
                    bufPlayer.setVelocityX(-20);
                } else if (bufAkcjaGracza == 0) {
                    bufPlayer.setVelocityX(0);
                    bufPlayer.setVelocityY(0);
                }
                */

                if (bufAkcjaGracza == 1 && !bufPlayer.isJumping()) {
                    bufPlayer.setVelocityY(-bufVel - 10);
                    bufPlayer.setJumping(true);
                } else if (bufAkcjaGracza == 2) {
                    bufPlayer.setVelocityX(bufVel);
                } else if (bufAkcjaGracza == 3) {
                    bufPlayer.setVelocityY(bufVel);
                } else if (bufAkcjaGracza == 4) {
                    bufPlayer.setVelocityX(-bufVel);
                } else if (bufAkcjaGracza == 0) {
                    if (bufPlayer.isFalling())
                        bufPlayer.setVelocityX(0);
                }

            }
        }
    }
}


//1 - up
//2 - right
//3 - down
//4 - left

