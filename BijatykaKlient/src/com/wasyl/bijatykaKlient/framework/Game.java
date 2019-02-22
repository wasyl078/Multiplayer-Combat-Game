package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Background;
import com.wasyl.bijatykaKlient.objects.gameobjects.characters.Bot;
import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;


public class Game extends Application {

    //związane z okienkiem
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //public final static int screenWidth = (int)screenSize.getWidth();
    //public final static int screenHeight = (int)screenSize.getHeight();
    public final static int screenWidth = 900;
    public final static int screenHeight = 506;

    private final Canvas canvas = new Canvas(Game.screenWidth, Game.screenHeight);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private Image lukaszPrawoImage;
    private Image maciekLewoImage;
    private Image botPrawoImage;

    //związane z obiektami w grze
    private ArrayList<String> input = new ArrayList<String>();
    private final Textures textures = new Textures();
    private final DrawHandler drawHandler = new DrawHandler(textures);
    private Camera camera;
    private Background background;
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
        background = new Background(0, 0, textures);
        drawHandler.addObject(background);
        drawHandler.makeFirstLevel();
        camera = new Camera(drawHandler.players);
        bot = new Bot(0, 0, textures, this);
        drawHandler.addObject(bot);

        //stworzenie grupy
        Group root = new Group();

        //wybór gracza
        this.lukaszPrawoImage = textures.getLukaszPrawoImage();
        this.maciekLewoImage = textures.getMaciekLewoImage();
        this.botPrawoImage = textures.getBotPrawoImage();
        Scene scene = new Scene(root, screenWidth, screenHeight, true, SceneAntialiasing.BALANCED);
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


        //utworzEnie komunikacji z serwerem
        final Msg msg = new Msg(this, textures);
        final Communication communication = new Communication(msg);

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
        root.getChildren().add(canvas);
        //stage.setMaximized(true);
        //stage.setFullScreen(true);

        //wyświetlenie wszystkiego
        communication.update();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.0085),                // 60 FPS
                ae -> draw());

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
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
            drawHandler.draw(gc, camera.getPositionX(), camera.getPositionY());
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
        else if (getWeaponNumber() == 3) setWeaponNumber(4);
        else if (getWeaponNumber() == 4) setWeaponNumber(1);

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
