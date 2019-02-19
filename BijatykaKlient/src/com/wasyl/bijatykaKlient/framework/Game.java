package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.objects.DrawHandler;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;


public class Game extends Application {

    //związane z okienkiem
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = screenSize.getWidth();
    public static double screenHeight = screenSize.getHeight();
    private Canvas canvas;


    //związane z obiektami w grze
    private ArrayList<String> input = new ArrayList<String>();
    private DrawHandler drawHandler;
    private Textures textures;


    //związane z identyfikacją grtacza
    private int playerLastAction = 0;
    private int thisIndividualPlayerNumber = 0;

    //main() wywołujący launch(), czyli start()
    public static void main(String[] args) {
        launch(args);
    }


    //start() == launch()
    @Override
    public void start(Stage stage) {

        stage = new CharacterChooser(new Textures());

        stage = new Stage();

        stage.setTitle("Gra");

        //stworzenie ważnych objektów
        textures = new Textures();
        drawHandler = new DrawHandler(textures);
        drawHandler.makeFirstLevel();

        //utworzEnie komunikacji z serwerem
        Msg msg = new Msg(this);
        Communication communication = new Communication(this, msg);

        //stworzenie grupy
        Group root = new Group();
        //Scene theScene = new Scene(root, screenWidth,screenHeight,true, SceneAntialiasing.BALANCED);
        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        //obsługa zdarzeń z klawiatury

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code)) input.add(code);

                    if (code.equals("UP")) setPlayerLastAction(1);
                    else if (code.equals("RIGHT")) setPlayerLastAction(2);
                    else if (code.equals("DOWN")) setPlayerLastAction(3);
                    else if (code.equals("LEFT")) setPlayerLastAction(4);
                    else if (code.equals("ESCAPE")) System.exit(0);
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                    if (input.isEmpty()) setPlayerLastAction(0);
                });

        //stworzenie płótna
        canvas = new Canvas(Game.screenWidth, Game.screenHeight);
        root.getChildren().add(canvas);
        stage.setMaximized(true);
        stage.setFullScreen(true);

        //wyświetlenie wszystkiego
        communication.update();
        stage.show();
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1920, 1080);

        drawHandler.draw(gc);
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

    public void setThisIndividualPlayerNumber(int thisIndividualPlayerNumber) {
        this.thisIndividualPlayerNumber = thisIndividualPlayerNumber;
    }
}
