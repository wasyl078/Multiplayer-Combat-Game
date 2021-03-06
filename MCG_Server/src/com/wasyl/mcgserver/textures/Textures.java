package com.wasyl.mcgserver.textures;

import com.wasyl.mcgserver.general.Game;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

import static javafx.embed.swing.SwingFXUtils.toFXImage;

public class Textures {

    private BufferedImage spriteSheet = null;
    private BufferedImage level1Image;
    private BufferedImage lukaszPrawoImage;
    private BufferedImage lukaszLewoImage;
    private BufferedImage maciekLewoImage;
    private BufferedImage maciekPrawoImage;
    private BufferedImage botLewoImage;
    private BufferedImage botPrawoImage;
    private BufferedImage mieczLewoImage;
    private BufferedImage mieczPrawoImage;
    private BufferedImage mieczPionowoImage;
    private BufferedImage pistoletPrawo;
    private BufferedImage pistoletLewo;
    private BufferedImage pociskPrawo;
    private BufferedImage pociskLewo;
    private BufferedImage mieczSwietlnyAtakLewoImage;
    private BufferedImage mieczSwietlnyAtakPrawoImage;
    private BufferedImage mieczSwietlnyPionowoLewoImage;
    private BufferedImage mieczSwietlnyPionowoPrawoImage;
    private BufferedImage toporPionowoPrawo;
    private BufferedImage toporPionowoLewo;
    private BufferedImage toporAtakLewo;
    private BufferedImage toporAtakPrawo;
    private BufferedImage healthBar1;
    private BufferedImage forceShieldLeft;
    private BufferedImage forceShieldRight;
    private BufferedImage grenadeLeft;
    private BufferedImage grenadeRight;
    private BufferedImage explosionFrame0;
    private BufferedImage explosionFrame1;
    private BufferedImage explosionFrame2;
    private BufferedImage explosionFrame3;
    private BufferedImage explosionFrame4;
    private BufferedImage explosionFrame5;

    public Textures() {
        SpriteSheetLoader loader = new SpriteSheetLoader();
        try {
            spriteSheet = loader.loadImage("obrazki/sprite.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        //TUTAJ PISAĆ JAKIE OBRAZKI TRZEBA WYCIĄĆ
        level1Image = ss.grabImage(4, 1, 128, 128);
        lukaszPrawoImage = ss.grabImage(5, 1, 91, 128);
        lukaszLewoImage = ss.grabImage(6, 1, 91, 128);
        maciekLewoImage = ss.grabImage(7, 1, 94, 128);
        maciekPrawoImage = ss.grabImage(8, 1, 94, 128);
        mieczLewoImage = ss.grabImage(10, 1, 128, 65);
        mieczPrawoImage = ss.grabImage(9, 1, 128, 65);
        mieczPionowoImage = ss.grabImage(11, 1, 65, 128);
        pistoletPrawo = ss.grabImage(12, 1, 128, 89);
        pistoletLewo = ss.grabImage(13, 1, 128, 89);
        pociskPrawo = ss.grabImage(1, 1, 53, 33);
        pociskLewo = ss.grabImage(2, 1, 53, 33);
        mieczSwietlnyPionowoLewoImage = ss.grabImage(15, 1, 18, 128);
        mieczSwietlnyPionowoPrawoImage = ss.grabImage(14, 1, 18, 128);
        mieczSwietlnyAtakPrawoImage = ss.grabImage(2, 2, 114, 38);
        mieczSwietlnyAtakLewoImage = ss.grabImage(1, 2, 114, 38);
        botLewoImage = ss.grabImage(4, 2, 93, 128);
        botPrawoImage = ss.grabImage(3, 2, 93, 128);
        toporPionowoPrawo = ss.grabImage(5, 2, 30, 117);
        toporPionowoLewo = ss.grabImage(6, 2, 30, 117);
        toporAtakPrawo = ss.grabImage(7, 2, 121, 66);
        toporAtakLewo = ss.grabImage(8, 2, 121, 66);
        healthBar1 = ss.grabImage(9,2,340,42);
        forceShieldLeft = ss.grabImage(13,2,52,128);
        forceShieldRight = ss.grabImage(12,2,52,128);
        grenadeLeft = ss.grabImage(15,2,65,108);
        grenadeRight = ss.grabImage(14,2,65,108);
        explosionFrame0 =ss.grabImage(1,3,68,63);
        explosionFrame1 =ss.grabImage(2,3,72,65);
        explosionFrame2 =ss.grabImage(3,3,96,72);
        explosionFrame3 =ss.grabImage(4,3,122,80);
        explosionFrame4 =ss.grabImage(5,3,126,85);
        explosionFrame5 =ss.grabImage(6,3,128,87);
    }


    public Image getLukaszPrawoImage() {
        Image bufImage = toFXImage(scale(lukaszPrawoImage, (int) (0.022396 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getLukaszLewoImage() {
        Image bufImage = toFXImage(scale(lukaszLewoImage, (int) (0.022396 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMaciekLewoImage() {
        Image bufImage = toFXImage(scale(maciekLewoImage, (int) (0.022396 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMaciekPrawoImage() {
        Image bufImage = toFXImage(scale(maciekPrawoImage, (int) (0.022396 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczLewoImage() {
        Image bufImage = toFXImage(scale(mieczLewoImage, (int) (0.066666 * Game.screenWidth), (int) (0.060185185 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczPrawoImage() {
        Image bufImage = toFXImage(scale(mieczPrawoImage, (int) (0.066666 * Game.screenWidth), (int) (0.060185185 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczPionowoImage() {
        Image bufImage = toFXImage(scale(mieczPionowoImage, (int) (0.03385416 * Game.screenWidth), (int) (0.1185185 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getPistoletPrawo() {
        Image bufImage = toFXImage(scale(pistoletPrawo, (int) (0.0520833 * Game.screenWidth), (int) (0.063888 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getPistoletLewo() {
        Image bufImage = toFXImage(scale(pistoletLewo, (int) (0.0520833 * Game.screenWidth), (int) (0.063888 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getPociskPrawo() {
        Image bufImage = toFXImage(scale(pociskPrawo, (int) (0.0078125 * Game.screenWidth), (int) (0.008333 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getPociskLewo() {
        Image bufImage = toFXImage(scale(pociskLewo, (int) (0.0078125 * Game.screenWidth), (int) (0.008333 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczSwietlnyAtakLewoImage() {
        Image bufImage = toFXImage(scale(mieczSwietlnyAtakLewoImage, (int) (0.098958 * Game.screenWidth), (int) (0.05864 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczSwietlnyAtakPrawoImage() {
        Image bufImage = toFXImage(scale(mieczSwietlnyAtakPrawoImage, (int) (0.098958 * Game.screenWidth), (int) (0.05864 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczSwietlnyPionowoLewoImage() {
        Image bufImage = toFXImage(scale(mieczSwietlnyPionowoLewoImage, (int) (0.0130208 * Game.screenWidth), (int) (0.197222 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getMieczSwietlnyPionowoPrawoImage() {
        Image bufImage = toFXImage(scale(mieczSwietlnyPionowoPrawoImage, (int) (0.0130208 * Game.screenWidth), (int) (0.197222 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getToporAtakLewoImage() {
        Image bufImage = toFXImage(scale(toporAtakLewo, (int) (0.06302 * Game.screenWidth), (int) (0.06111 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getToporAtakPrawoImage() {
        Image bufImage = toFXImage(scale(toporAtakPrawo, (int) (0.06302 * Game.screenWidth), (int) (0.06111 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getToporPionowoLewoImage() {
        Image bufImage = toFXImage(scale(toporPionowoLewo, (int) (0.015625 * Game.screenWidth), (int) (0.108333 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getToporPionowoPrawoImage() {
        Image bufImage = toFXImage(scale(toporPionowoPrawo, (int) (0.015625 * Game.screenWidth), (int) (0.108333 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getBotLewoImage() {
        Image bufImage = toFXImage(scale(botLewoImage, (int) (0.02421875 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getBotPrawoImage() {
        Image bufImage = toFXImage(scale(botPrawoImage, (int) (0.02421875 * Game.screenWidth), (int) (0.059259 * Game.screenHeight)), null);
        return bufImage;
    }

    public Image getHealthbar1(){
        Image bufImage = toFXImage(scale(healthBar1, (int)(0.177083 * Game.screenWidth), (int)(0.03888*Game.screenHeight)),null);
        return bufImage;
    }

    public Image getForceShieldLeft(){
        Image bufImage = toFXImage(scale(forceShieldLeft, (int)(0.027083*Game.screenWidth), (int)(0.118518*Game.screenHeight)),null);
        return bufImage;
    }

    public Image getForceShieldRight(){
        Image bufImage = toFXImage(scale(forceShieldRight, (int)(0.027083*Game.screenWidth), (int)(0.118518*Game.screenHeight)),null);
        return bufImage;
    }

    public Image getGrenadeLeft(){
        Image bufImage = toFXImage(scale(grenadeLeft, (int)(0.0114583*Game.screenWidth), (int)(0.033333*Game.screenHeight)),null);
        return bufImage;
    }
    public Image getGrenadeRight(){
        Image bufImage = toFXImage(scale(grenadeRight, (int)(0.0114583*Game.screenWidth), (int)(0.033333*Game.screenHeight)),null);
        return bufImage;
    }

    public Image[] getExplosions(){
        Image bufImage0 = toFXImage(scale(explosionFrame0, (int)(0.0708332*Game.screenWidth), (int)(0.1166666*Game.screenHeight)),null);
        Image bufImage1 = toFXImage(scale(explosionFrame1, (int)(0.075*Game.screenWidth), (int)(0.1203702*Game.screenHeight)),null);
        Image bufImage2 = toFXImage(scale(explosionFrame2, (int)(0.1*Game.screenWidth), (int)(0.1333332*Game.screenHeight)),null);
        Image bufImage3 = toFXImage(scale(explosionFrame3, (int)(0.1270832*Game.screenWidth), (int)(0.148148*Game.screenHeight)),null);
        Image bufImage4 = toFXImage(scale(explosionFrame4, (int)(0.13125*Game.screenWidth), (int)(0.1574074*Game.screenHeight)),null);
        Image bufImage5 = toFXImage(scale(explosionFrame5, (int)(0.1333332*Game.screenWidth), (int)(0.161111*Game.screenHeight)),null);

        Image []explosions = {bufImage5,bufImage4,bufImage3,bufImage2,bufImage1,bufImage0};
        return explosions;
    }

    //metoda skalująca obrazki (BufferedImage ---> JavaFx Image)
    public static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }
}
