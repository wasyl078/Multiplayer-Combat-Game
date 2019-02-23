package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Player;
import com.wasyl.bijatykaKlient.objects.gameobjects.distance.throwablethings.Grenade;
import com.wasyl.bijatykaKlient.objects.gameobjects.distance.throwablethings.PistolBullet;
import com.wasyl.bijatykaKlient.sounds.SoundsEffect;
import com.wasyl.bijatykaKlient.textures.Textures;

import java.util.ArrayList;

public class Msg {

    //ważne zmienne
    private Game game;
    private DrawHandler drawHandler;
    private Textures textures;
    private int lastPlayersNumber = 0;
    private int messagesCounterToDown = 10;


    //konstruktor klasy
    public Msg(Game game, Textures textures) {
        this.game = game;
        this.drawHandler = game.getDrawHandler();
        this.textures = textures;
    }


    //metoda interpretująca wiadomość z serwera
    public void interpretujJednaWiadomoscZSerwera(String wiadom) {

        //wstępne rozdzielenie wiadomośći z serwera
        String[] parts = wiadom.split("_");
        int posX;
        int posY;
        int direction;
        String[] numberOfPlayersAndSounds = parts[0].split("\\.");
        int numberOfPlayers = Integer.valueOf(numberOfPlayersAndSounds[0]);
        int characterImageNumber;
        int lastWeapon;
        int HP;
        String alive;

        //interpretacja dzwięków
        for (int i = 2; i <= Integer.parseInt(numberOfPlayersAndSounds[1]) + 1; i++) {
            int bufsounds = Integer.parseInt(numberOfPlayersAndSounds[i]);
            if (bufsounds == 1) SoundsEffect.makeLightSaberSound();
            else if (bufsounds == -1) SoundsEffect.makeLightSaberHitSound();
            else if (bufsounds == 2) SoundsEffect.makeAxeSwingSound();
            else if (bufsounds == -2) SoundsEffect.makeAxeSwingSound();            //TODO
            else if (bufsounds == 3) SoundsEffect.makePistolSound();
            else if (bufsounds == -3) SoundsEffect.makeBulletImactSound();
            else if (bufsounds == 4) SoundsEffect.makeForceShieldSound();
        }


        //odczekanie dziesięciu pierwszych wiadomości, żeby mieć pewność, że pierwsza będzie prawidłowa
        if (messagesCounterToDown != 0) {
            messagesCounterToDown--;
            if (messagesCounterToDown == 1) Game.thisIndividualPlayerNumber = numberOfPlayers;
            return;
        }

        //pododawnie wszystkich obecnych graczy
        while (numberOfPlayers != lastPlayersNumber) {
            lastPlayersNumber++;
            drawHandler.addPlayer(Integer.parseInt(parts[lastPlayersNumber + 2].substring(2, 3)), lastPlayersNumber);
        }


        //nowe PistolBullety
        String[] bufPistolBullet = parts[1].split("\\.");
        if (bufPistolBullet[0].equals("1")) {
            int bufIndBulNumr = Integer.parseInt(bufPistolBullet[1]);
            int bufDir = Integer.parseInt(bufPistolBullet[2]);
            int bufPosX = Integer.parseInt(bufPistolBullet[3]);
            int bufPosY = Integer.parseInt(bufPistolBullet[4]);
            bufPosX = Game.screenWidth * bufPosX / 1920;
            bufPosY = Game.screenHeight * bufPosY / 1080;
            game.getDrawHandler().objects.add(new PistolBullet(bufPosX, bufPosY, bufDir, bufIndBulNumr, textures, game.getDrawHandler().objects));
        } else if (bufPistolBullet[0].equals("2")) {
            int bufNmbr = Integer.parseInt(bufPistolBullet[1]);
            for (int i = 0; i < game.getDrawHandler().objects.size(); i++) {
                if (game.getDrawHandler().objects.get(i).getClass().equals(PistolBullet.class)) {
                    PistolBullet bufPB = (PistolBullet) game.getDrawHandler().objects.get(i);
                    if (bufPB.getIndividualBulletNumber() == bufNmbr)
                        game.getDrawHandler().objects.remove(bufPB);
                }
            }
        }

        //nowe granaty
        String[] bufGrenade = parts[2].split("\\.");
        int grenades = Integer.parseInt(bufGrenade[0]);
        for (int i = 1; i <= grenades; i++) {
            int bufActive = Integer.parseInt(bufGrenade[(i - 1) * 5 + 1]);
            int bufIndividualGrenadeNumber = Integer.parseInt(bufGrenade[(i - 1) * 5 + 2]);
            int bufDirection = Integer.parseInt(bufGrenade[(i - 1) * 5 + 3]);
            int bufX = Integer.parseInt(bufGrenade[(i - 1) * 5 + 4]);
            int bufY = Integer.parseInt(bufGrenade[(i - 1) * 5 + 5]);
            bufX = Game.screenWidth * bufX / 1920;
            bufY = Game.screenHeight * bufY / 1080;
            if (!Grenade.listOfGrenadesNumbers.contains(bufIndividualGrenadeNumber)) {
                game.getDrawHandler().objects.add(new Grenade(bufX, bufY, bufIndividualGrenadeNumber, bufDirection, textures));
            }
            for (int u = 0; u < game.getDrawHandler().objects.size(); u++) {
                if (game.getDrawHandler().objects.get(u).getClass().equals(Grenade.class)) {
                    Grenade bufGr = (Grenade) game.getDrawHandler().objects.get(u);
                    if(bufGr.getIndividualGrenadeNumber() == bufIndividualGrenadeNumber){
                        bufGr.setPositionX(bufX);
                        bufGr.setPositionY(bufY);
                        bufGr.setDirection(bufDirection);
                        if(bufActive == 2) game.getDrawHandler().objects.remove(bufGr);
                    }
                }
            }

        }

        //poustawianie pozycji wszystkich graczy na ekranie
        for (int i = 3; i <= numberOfPlayers + 2; i++) {
            String[] buf = parts[i].split("\\.");

            alive = buf[0];
            characterImageNumber = Integer.parseInt(buf[1]);
            direction = Integer.parseInt(buf[2]);
            lastWeapon = Integer.parseInt(buf[3]);
            HP = Integer.parseInt(buf[4]);
            posX = Integer.parseInt(buf[5]);
            posY = Integer.parseInt(buf[6]);


            //korekta pozycji względem rozmiarów ekranu
            posX = (int) Game.screenWidth * posX / 1920;
            posY = (int) Game.screenHeight * posY / 1080;

            for (int u = 0; u < game.getDrawHandler().objects.size(); u++) {
                if (game.getDrawHandler().objects.get(u).getClass().equals(Player.class)) {
                    Player bufPlayer = (Player) game.getDrawHandler().objects.get(u);
                    if (i - 2 == ((Player) game.getDrawHandler().objects.get(u)).getPlayerNumber()) {
                        bufPlayer.setAlive(alive);
                        bufPlayer.setLastWeapon(lastWeapon);
                        bufPlayer.setDirection(direction);
                        bufPlayer.setCharacterImageNumber(characterImageNumber);
                        bufPlayer.setHealth(HP);
                        bufPlayer.setPositionX(posX);
                        bufPlayer.setPositionY(posY);
                    }
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