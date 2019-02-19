package com.wasyl.bijatykaKlient.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Communication {

    String wiadom;
    BufferedReader czytelnik;
    PrintWriter pisarz;
    Socket gniazdo;
    private Msg msg;
    private Game game;

    public Communication(Game game, Msg msg) {
        this.msg = msg;
        this.game = game;
        konfigurujKomunikacje();
    }

    public void update() {

        String wiadom;
        try {
            wiadom = msg.createMessageToSendToServer();
            if(wiadom != null) {
                pisarz.println(wiadom);
                pisarz.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread watekOdbiorcy = new Thread(new OdbiorcaKomunikatow());
        watekOdbiorcy.start();
    }

    //metoda, która ma tylko nawiązać komunikacje z serwerem
    private void konfigurujKomunikacje() {
        try {
            gniazdo = new Socket("127.0.0.1", 5001);
            InputStreamReader czytelnikStrm = new InputStreamReader(gniazdo.getInputStream());
            czytelnik = new BufferedReader(czytelnikStrm);
            pisarz = new PrintWriter(gniazdo.getOutputStream());
            System.out.println("obsluga sieci przygotowana");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class OdbiorcaKomunikatow implements Runnable {

        @Override
        public void run() {
            try {
                wiadom = czytelnik.readLine();
                msg.interpretujJednaWiadomoscZSerwera(wiadom);
                update();
                game.draw();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}