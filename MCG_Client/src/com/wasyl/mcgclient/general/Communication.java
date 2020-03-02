package com.wasyl.mcgclient.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Communication {


    //WAŻNE
    private String host = "127.0.0.1";
    //!!!!

    String wiadom;
    BufferedReader czytelnik;
    PrintWriter pisarz;
    Socket gniazdo;
    private Msg msg;

    public Communication(Msg msg) {
        this.msg = msg;
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
            gniazdo = new Socket(host, 5001);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}