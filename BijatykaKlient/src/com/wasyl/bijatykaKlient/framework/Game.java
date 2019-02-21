package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.Background;
import com.wasyl.bijatykaKlient.objects.Bot;
import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;


public class Game extends Application {

    //związane z okienkiem
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //public static double screenWidth = screenSize.getWidth();
    //public static double screenHeight = screenSize.getHeight();
    public static double screenWidth = 900;
    public static double screenHeight = 506;


    private Canvas canvas;
    private GraphicsContext gc;
    private Image lukaszPrawoImage;
    private Image maciekLewoImage;
    private Image botPrawoImage;

    //związane z obiektami w grze
    private ArrayList<String> input = new ArrayList<String>();
    private DrawHandler drawHandler;
    private Textures textures;
    private Camera camera;
    private Background background;
    public static Player actualPlayer;
    private Bot bot;


    //związane z identyfikacją grtacza
    private int playerLastAction = 0;
    public static int thisIndividualPlayerNumber = 0;
    public static int isChoosen = 0;
    private int attack = 0;
    private int weaponNumber = 1;
    private boolean ableWeaponChange = true;


    //main() wywołujący launch(), czyli start()
    public static void main(String[] args) {
        launch(args);
    }


    //start() == launch()
    @Override
    public void start(Stage stage) {

        stage.setTitle("Gra");

        //stworzenie ważnych objektów
        textures = new Textures();
        drawHandler = new DrawHandler(textures);
        background = new Background(0, 0, textures);
        drawHandler.addObject(background);
        drawHandler.makeFirstLevel();
        camera = new Camera(drawHandler.players);
        bot = new Bot(0, 0, Game.thisIndividualPlayerNumber, textures, this);
        drawHandler.addObject(bot);

        //stworzenie grupy
        Group root = new Group();

        //wybór gracza
        this.lukaszPrawoImage = textures.getLukaszPrawoImage();
        this.maciekLewoImage = textures.getMaciekLewoImage();
        this.botPrawoImage = textures.getBotPrawoImage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        //obsługa zdarzeń (myszki)
        scene.setOnMouseClicked(
                e -> {
                    if (isChoosen == 0) {
                        double posX = e.getX();
                        double posY = e.getY();
                        if (posX >= screenWidth / 4 - lukaszPrawoImage.getWidth() / 2 && posX <= screenWidth / 4 + lukaszPrawoImage.getWidth() / 2) {
                            if (posY >= screenHeight / 2 - lukaszPrawoImage.getHeight() / 2 && posY <= screenHeight / 2 + lukaszPrawoImage.getHeight() / 2) {
                                isChoosen = 1;
                            }
                        } else if (posX >= screenWidth * 2 / 4 - maciekLewoImage.getWidth() / 2 && posX <= screenWidth * 2 / 4 + maciekLewoImage.getWidth() / 2) {
                            if (posY >= screenHeight / 2 - maciekLewoImage.getHeight() / 2 && posY <= screenHeight / 2 + maciekLewoImage.getHeight() / 2) {
                                isChoosen = 2;
                            }
                        } else if (posX >= screenWidth * 3 / 4 - botPrawoImage.getWidth() / 2 && posX <= screenWidth * 3 / 4 + maciekLewoImage.getWidth() / 2) {
                            if (posY >= screenHeight / 2 - botPrawoImage.getHeight() / 2 && posY <= screenHeight / 2 + maciekLewoImage.getHeight() / 2) {
                                isChoosen = 3;
                            }
                        }
                    }
                });


        //Scene theScene = new Scene(root, screenWidth,screenHeight,true, SceneAntialiasing.BALANCED);


        //utworzEnie komunikacji z serwerem
        Msg msg = new Msg(this, textures);
        Communication communication = new Communication(this, msg);

        //obsługa zdarzeń z klawiatury

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) input.add(code);
                    if (code.equals("UP")) setPlayerLastAction(1);
                    else if (code.equals("RIGHT")) setPlayerLastAction(2);
                    else if (code.equals("DOWN")) setPlayerLastAction(3);
                    else if (code.equals("LEFT")) setPlayerLastAction(4);
                    else if (code.equals("Z")) setAttack(1);
                    else if (code.equals("X") && isAbleWeaponChange()) {
                        changeWeapon();
                        setAbleWeaponChange(false);
                    }
                    if (code.equals("ESCAPE")) System.exit(0);
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                    if (input.isEmpty() || code.equals("LEFT") || code.equals("RIGHT")) setPlayerLastAction(0);
                    if (!input.contains("Z")) setAttack(0);
                    if (code.equals("X")) setAbleWeaponChange(true);
                });

        //stworzenie płótna
        canvas = new Canvas(Game.screenWidth, Game.screenHeight);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
        //stage.setMaximized(true);
        //stage.setFullScreen(true);

        //wyświetlenie wszystkiego
        communication.update();
        stage.show();
    }

    public void draw() {

        if (isChoosen == 0) {
            //narysowanie wszystkiego
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, screenWidth, screenHeight);

            //Łukasz obrazek
            gc.setFill(Color.GREEN);
            gc.fillRect(screenWidth / 4 - lukaszPrawoImage.getWidth() / 2, screenHeight / 2 - lukaszPrawoImage.getHeight() / 2, lukaszPrawoImage.getWidth(), lukaszPrawoImage.getHeight());
            gc.drawImage(lukaszPrawoImage, screenWidth / 4 - lukaszPrawoImage.getWidth() / 2, screenHeight / 2 - lukaszPrawoImage.getHeight() / 2);

            //Maciek obrazek
            gc.setFill(Color.RED);
            gc.fillRect(screenWidth * 2 / 4 - maciekLewoImage.getWidth() / 2, screenHeight / 2 - maciekLewoImage.getHeight() / 2, maciekLewoImage.getWidth(), maciekLewoImage.getHeight());
            gc.drawImage(maciekLewoImage, screenWidth * 2 / 4 - maciekLewoImage.getWidth() / 2, screenHeight / 2 - maciekLewoImage.getHeight() / 2);

            //Bot obrazek
            gc.setFill(Color.BLUE);
            gc.fillRect(screenWidth * 3 / 4 - botPrawoImage.getWidth() / 2, screenHeight / 2 - maciekLewoImage.getHeight() / 2, maciekLewoImage.getWidth(), maciekLewoImage.getHeight());
            gc.drawImage(botPrawoImage, screenWidth * 3 / 4 - maciekLewoImage.getWidth() / 2, screenHeight / 2 - maciekLewoImage.getHeight() / 2);
        } else {
            // gc.setFill(Color.BLACK);
            //gc.fillRect(0, 0, Game.screenWidth, Game.screenHeight);
            camera.update();
            drawHandler.draw(gc, (int) camera.getPositionX(), (int) camera.getPositionY());
        }
    }

    //gettery
    public DrawHandler getDrawHandler() {
        return drawHandler;
    }

    public int getPlayerLastAction() {
        return playerLastAction;
    }

    public void setPlayerLastAction(int playerLastAction) {
        this.playerLastAction = playerLastAction;
    }

    public int getThisIndividualPlayerNumber() {
        return thisIndividualPlayerNumber;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void changeWeapon() {
        if (getWeaponNumber() == 1) setWeaponNumber(2);
        else if (getWeaponNumber() == 2) setWeaponNumber(3);
        else if (getWeaponNumber() == 3) setWeaponNumber(1);

    }

    public int getWeaponNumber() {
        return weaponNumber;
    }

    public void setWeaponNumber(int weaponNumber) {
        this.weaponNumber = weaponNumber;
    }

    public boolean isAbleWeaponChange() {
        return ableWeaponChange;
    }

    public void setAbleWeaponChange(boolean ableWeaponChange) {
        this.ableWeaponChange = ableWeaponChange;
    }
}
