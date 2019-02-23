package com.wasyl.bijatykaSerwer.framework;


import com.wasyl.bijatykaSerwer.objects.GameObject;
import com.wasyl.bijatykaSerwer.objects.ID;
import com.wasyl.bijatykaSerwer.objects.Player;
import com.wasyl.bijatykaSerwer.objects.UpdateHandler;
import com.wasyl.bijatykaSerwer.objects.bullets.Grenade;

import java.util.ArrayList;
import java.util.LinkedList;

public class Msg {

    //zmienne i obiekty
    private UpdateHandler updateHandler;
    private LinkedList<Player> playersList;
    private ArrayList<Integer>soundsList;
    private LinkedList<GameObject>objects;


    //konstruktor klasy
    public Msg(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
        playersList = updateHandler.players;
        soundsList = updateHandler.sounds;
        objects = updateHandler.objects;
    }

    //metoda twrząca jedną długą wiadomość do wysłania każdemu klientowi
    public String stworzJednaDlugaWiadomosc() {

        //ilosc graczy
        String bufWiadom = "";
        int players = playersList.size();
        bufWiadom += players;

        //dzwięki
        bufWiadom+=".";
        bufWiadom+=soundsList.size();
        for(int i = 0 ; i < soundsList.size();i++){
            bufWiadom+=".";
            bufWiadom+=soundsList.get(i);
        }
        soundsList.clear();
        updateHandler.sounds.clear();

        //nowy PistolBullet
        if (Game.bufPistolBullet != null) {
            bufWiadom += "_";
            if (Game.bufPistolBullet.getActive().equals("t")) {
                bufWiadom += 1;
            } else {
                bufWiadom += 2;
            }
            bufWiadom += ".";
            bufWiadom += Game.bufPistolBullet.getIndividualBulletNumber();
            bufWiadom += ".";
            bufWiadom += Game.bufPistolBullet.getDirection();
            bufWiadom += ".";
            bufWiadom += (int) Game.bufPistolBullet.getPositionX();
            bufWiadom += ".";
            bufWiadom += (int) Game.bufPistolBullet.getPositionY();
            Game.bufPistolBullet = null;
        } else bufWiadom += "_0.x.x.x.x";

        //granaty
        bufWiadom += "_";
        bufWiadom +=Grenade.grenades;
        for(int i = 0 ; i < objects.size(); i++){
            if(objects.get(i).getId().equals(ID.Grenade)){
                Grenade bufGrenade = (Grenade) objects.get(i);
                bufWiadom += ".";
                if (bufGrenade.getActive().equals("t")) {
                    bufWiadom += 1;
                } else {
                    bufWiadom += 2;
                }
                bufWiadom += ".";
                bufWiadom += bufGrenade.getIndividualGrenadeNumber();
                bufWiadom += ".";
                bufWiadom += bufGrenade.getDirection();
                bufWiadom += ".";
                bufWiadom += (int) bufGrenade.getPositionX();
                bufWiadom += ".";
                bufWiadom += (int) bufGrenade.getPositionY();
            }
        }
        for(int i = 0 ; i< objects.size();i ++){
            if(objects.get(i).getId().equals(ID.Grenade))
                if(((Grenade)objects.get(i)).getActive().equals("n")){
                    Grenade.grenades--;
                    objects.remove(objects.get(i));
                }
        }


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

