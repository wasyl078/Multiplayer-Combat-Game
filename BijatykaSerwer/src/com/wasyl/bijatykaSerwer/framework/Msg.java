package com.wasyl.bijatykaSerwer.framework;


import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.objects.UpdateHandler;

import java.util.LinkedList;

public class Msg {

    //zmienne i obiekty
    private UpdateHandler updateHandler;
    private LinkedList<Player> playersList;


    //konstruktor klasy
    public Msg(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
        playersList = updateHandler.players;
    }

    //metoda twrząca jedną długą wiadomość do wysłania każdemu klientowi
    public String stworzJednaDlugaWiadomosc() {

        //ilosc graczy
        String bufWiadom = "";
        int players = playersList.size();
        bufWiadom += players;

        //nowy PistolBullet
        if (Game.bufPistolBullet != null) {
            bufWiadom += "_";
            bufWiadom += 1;
            bufWiadom += ".";
            bufWiadom += Game.bufPistolBullet.getDirection();
            bufWiadom += ".";
            bufWiadom += (int)Game.bufPistolBullet.getPositionX();
            bufWiadom += ".";
            bufWiadom += (int)Game.bufPistolBullet.getPositionY();
            Game.bufPistolBullet = null;
        } else bufWiadom += "_0.x.x.x";


        //stan graczy
        for (int i = 0; i < playersList.size(); i++) {
            Player bpla = playersList.get(i);
            bufWiadom += "_";
            bufWiadom += bpla.getAlive();
            bufWiadom += ".";
            bufWiadom += (int) bpla.getCharacterImageNumber();
            bufWiadom += ".";
            bufWiadom += bpla.getDirection();
            bufWiadom += ".";
            bufWiadom += bpla.getLastWeapon();
            bufWiadom += ".";
            bufWiadom += bpla.getHP();
            bufWiadom += ".";
            bufWiadom += (int) bpla.getPositionX();
            bufWiadom += ".";
            bufWiadom += (int) bpla.getPositionY();
        }
        return bufWiadom;
    }


    //metoda interpretująca wiadomości od każdego klienta
    public void interpretujWiadomoscOdKlientow(String wiadom) {

        String[] parts = wiadom.split("_");
        int bufNumerGracza = Integer.parseInt(parts[0]);
        int bufNumerObrazkaGracza = Integer.parseInt(parts[1]);
        int bufCzyAtakuje = Integer.parseInt(parts[2]);
        int bufNumerBroni = Integer.parseInt(parts[3]);
        int bufAkcjaGracza = Integer.parseInt(parts[4]);

        int bufVel = 10;

        for (int i = 0; i < playersList.size(); i++) {
            Player bufPlayer = playersList.get(i);
            if (bufPlayer.getPlayerNumber() == bufNumerGracza) {

                bufPlayer.setCharacterImageNumber(bufNumerObrazkaGracza);
                bufPlayer.setAttacking(bufCzyAtakuje);
                bufPlayer.setWhichWeapon(bufNumerBroni);

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

                if (bufPlayer.getHittedCounter() >= 198) {
                    bufPlayer.setVelocityY(-bufVel - 10);
                    bufPlayer.setJumping(true);
                }
                if (bufPlayer.getHittedCounter() > 0) {
                    bufPlayer.setVelocityX(bufPlayer.getVelocityX() + (int) (bufPlayer.getDirectionToRecoil() * bufPlayer.getHittedCounter() * bufPlayer.getHittedCounter() / 5000));
                }
                if (bufPlayer.getVelocityX() > 10) bufPlayer.setVelocityX(10);
                else if (bufPlayer.getVelocityX() < -10) bufPlayer.setVelocityX(-10);

                bufPlayer.setHittedCounter(bufPlayer.getHittedCounter() - 1);


            }
        }
    }
}


//1 - up
//2 - right
//3 - down
//4 - left

