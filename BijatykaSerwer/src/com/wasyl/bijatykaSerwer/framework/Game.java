package com.wasyl.bijatykaSerwer.framework;


import com.wasyl.bijatykaSerwer.objects.UpdateHandler;
import com.wasyl.bijatykaSerwer.objects.bullets.PistolBullet;
import com.wasyl.bijatykaSerwer.textures.Textures;


public class Game implements Runnable {

    //zmienne dla rozmiarów "wirtualnego okienka"
    public static int screenWidth = 1920;
    public static int screenHeight = 1080;

    //zmienne dla ważnych obiektów
    private boolean running = false;
    private Thread thread;
    private UpdateHandler updateHandler;
    private Communication communication;
    private Textures textures;
    private Msg msg;
    public static PistolBullet bufPistolBullet;

    //konstruktor gry, wywołuje kilka podstawowych metod na początek
    public Game() {
        makeImportantObjects();
        start();
        communication.doRoboty();
    }

    //TUTAJ PISAĆ JAKIE WAŻNE OBIEKTY MUSZĄ SIĘ UTWORZYĆ
    private void makeImportantObjects() {
        textures = new Textures();
        updateHandler = new UpdateHandler(textures);
        updateHandler.makeFirstLevel();
        msg = new Msg(updateHandler);
        communication = new Communication(msg, updateHandler);
    }


    //TUTAJ PISAĆ CO MA SIĘ UPDATE'OWAĆ
    private void update() {
        communication.powysylajWiadomosci();
        updateHandler.update();
    }


    //run
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 120.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
        }
    }


    //metoda zaczynająca program
    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    //metoda zatrzymująca program
    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public UpdateHandler getUpdateHandler() {
        return updateHandler;
    }

    //metoda main rozpoczynająca program
    public static void main(String[] args) {
        final Game game = new Game();
    }
}
