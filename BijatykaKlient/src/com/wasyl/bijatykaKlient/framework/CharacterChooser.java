package com.wasyl.bijatykaKlient.framework;

import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.awt.*;


public class CharacterChooser extends Stage {

    //związane z okienkiem
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = screenSize.getWidth();
    public static double screenHeight = screenSize.getHeight();

    //związane z całym tym płótnem
    private Canvas canvas;
    private Image lukaszPrawoImage;
    private Image maciekLewoImage;
    private Image botPrawoImage;
    public static int isChoosen = 0;

    //konstruktor klasy
    public CharacterChooser(Textures textures) {
        this.lukaszPrawoImage = textures.getLukaszPrawoImage();
        this.maciekLewoImage = textures.getMaciekLewoImage();
        this.botPrawoImage = textures.getBotPrawoImage();
        start(this);
    }

    //launch() == start()
    public void start(Stage stage) {

        stage.setTitle("Gra");

        //stworzenie grupy
        Group root = new Group();
        //Scene theScene = new Scene(root, screenWidth,screenHeight,true, SceneAntialiasing.BALANCED);
        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        //TODO obsługa zdarzeń (myszki)
        theScene.setOnMouseClicked(
                e -> {

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
                    stage.close();
                });


        //stworzenie płótna
        canvas = new Canvas(Game.screenWidth, Game.screenHeight);
        root.getChildren().add(canvas);
        stage.setMaximized(true);
        stage.setFullScreen(true);

        //wyświetlenie wszystkiego
        GraphicsContext gc = canvas.getGraphicsContext2D();

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

        stage.showAndWait();
    }
}
