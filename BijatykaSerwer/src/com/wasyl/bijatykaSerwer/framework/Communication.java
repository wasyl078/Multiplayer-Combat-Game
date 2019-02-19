package com.wasyl.bijatykaSerwer.framework;

import com.wasyl.bijatykaSerwer.objects.UpdateHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Communication {

    //zmienne
    private ArrayList strumienieWejsciowe;
    private Msg msg;
    private UpdateHandler updateHandler;
    private int iloscGraczy = 0;


    //konstruktor klasy
    public Communication(Msg msg, UpdateHandler updateHandler) {
        this.msg = msg;
        this.updateHandler = updateHandler;
    }


    //metoda wywołująca wysłanie wiadmości do wszystkich klientów
    public void powysylajWiadomosci() {
        String msdsdad = msg.stworzJednaDlugaWiadomosc();
        if (msdsdad.equals("0") || Integer.parseInt(msdsdad.substring(0, 1)) != iloscGraczy) return;
        System.out.println(msdsdad);
        rozeslijDoWszystkich(msdsdad);
    }


    //(pod)klasa do obsługiwania klientów (NIE RUSZAĆ)
    public class ObslugaKlientow implements Runnable {
        BufferedReader czytelnik;
        Socket gniazdo;

        public ObslugaKlientow(Socket clientSocket) {
            try {
                gniazdo = clientSocket;
                InputStreamReader isReader = new InputStreamReader(gniazdo.getInputStream());
                czytelnik = new BufferedReader(isReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //metoda w (pod)klasie do odbierania wiadomości od pojedyńczych klientów
        public void run() {
            String wiadomOdKlientow;
            try {
                while ((wiadomOdKlientow = czytelnik.readLine()) != null || (wiadomOdKlientow = czytelnik.readLine()).equals("")) {
                    msg.interpretujWiadomoscOdKlientow(wiadomOdKlientow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //sprawdzanie czy są jacyś nowi klienci
    public void doRoboty() {
        strumienieWejsciowe = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(5001);

            while (true) {
                Socket gniazdoKlienta = serverSock.accept();
                PrintWriter pisarz = new PrintWriter(gniazdoKlienta.getOutputStream());
                strumienieWejsciowe.add(pisarz);
                Thread t = new Thread(new ObslugaKlientow(gniazdoKlienta));
                t.start();
                System.out.println("mamy polaczenie z nowym klientem");
                iloscGraczy++;
                updateHandler.addPlayer(iloscGraczy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wysyłanie danej wiadomości do wszystkich dostępnych osób
    public void rozeslijDoWszystkich(String message) {

        Iterator it = strumienieWejsciowe.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter pisarz = (PrintWriter) it.next();
                pisarz.println(message);
                pisarz.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}