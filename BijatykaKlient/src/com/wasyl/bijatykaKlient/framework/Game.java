package com.wasyl.bijatykaKlient.framework;
import com.wasyl.bijatykaKlient.objects.Handler;
import com.wasyl.bijatykaKlient.objects.Player;
import com.wasyl.bijatykaKlient.textures.Textures;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class Game extends Application {

    private ArrayList<String>input = new ArrayList<String>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Gra");

        //stworzenie ważnych objektów
        Textures textures = new Textures();
        Handler handler = new Handler();
        Player player = new Player(40, 40, input, textures);
        handler.addObject(player);

        //stworzenie grupy
        Group root = new Group();
        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        //obsługa zdarzeń z klawiatury

        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if (!input.contains(code))
                        input.add(code);
                });

        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });


        //stworzenie płótna
        Canvas canvas = new Canvas(1920, 1080);
        root.getChildren().add(canvas);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        //stworzenie pędzelka
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //stworzenie gameloopa
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        final long timeStart = System.currentTimeMillis();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, 1920, 1080);

                    handler.update();
                    handler.draw(gc);
                    System.out.println(input);
                });

        //odpalenie gameloopa
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        //wyświetlenie wszystkiego
        stage.show();
    }
}
