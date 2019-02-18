package com.wasyl.bijatykaKlient.textures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Textures {

    private BufferedImage spriteSheet = null;
    private BufferedImage level1Image;
    private BufferedImage lukaszPrawoImage;
    private BufferedImage lukaszLewoImage;
    private BufferedImage maciekLewoImage;
    private BufferedImage maciekPrawoImage;
    private BufferedImage botLewoImage;
    public BufferedImage botPrawoImage;
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
        pociskPrawo = ss.grabImage(1,1,53,33);
        pociskLewo = ss.grabImage(2,1,53,33);
        mieczSwietlnyPionowoLewoImage = ss.grabImage(15,1,15,128);
        mieczSwietlnyPionowoPrawoImage = ss.grabImage(14,1,15,128);
        mieczSwietlnyAtakPrawoImage = ss.grabImage(2,2,90,90);
        mieczSwietlnyAtakLewoImage = ss.grabImage(1,2,90,90);
        botLewoImage = ss.grabImage(4, 2, 93,128);
        botPrawoImage = ss.grabImage(3,2,93,128);
    }

    public BufferedImage getLevel1Image() {
        return level1Image;
    }

    public Image getLukaszPrawoImage() {
        Image bufImage = lukaszPrawoImage.getScaledInstance((int) (0.022396 * 1920), (int) (0.059259 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getLukaszLewoImage() {
        Image bufImage = lukaszLewoImage.getScaledInstance((int) (0.022396 * 1920), (int) (0.059259 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMaciekLewoImage() {
        Image bufImage = maciekLewoImage.getScaledInstance((int) (0.022396 * 1920), (int) (0.059259 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMaciekPrawoImage() {
        Image bufImage = maciekPrawoImage.getScaledInstance((int) (0.022396 * 1920), (int) (0.059259 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczLewoImage() {
        Image bufImage = mieczLewoImage.getScaledInstance((int) (0.066666 * 1920), (int) (0.060185185 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczPrawoImage() {
        Image bufImage = mieczPrawoImage.getScaledInstance((int) (0.066666 * 1920), (int) (0.060185185 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczPionowoImage() {
        Image bufImage = mieczPionowoImage.getScaledInstance((int) (0.03385416 * 1920), (int) (0.1185185 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getPistoletPrawo() {
        Image bufImage = pistoletPrawo.getScaledInstance((int) (0.0520833 * 1920), (int) (0.063888 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getPistoletLewo() {
        Image bufImage = pistoletLewo.getScaledInstance((int) (0.0520833 * 1920), (int) (0.063888 * 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getPociskPrawo() {
        Image bufImage = pociskPrawo.getScaledInstance((int)(0.0078125*1920), (int)(0.008333*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getPociskLewo() {
        Image bufImage = pociskLewo.getScaledInstance((int)(0.0078125*1920), (int)(0.008333*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczSwietlnyAtakLewoImage() {
        Image bufImage = mieczSwietlnyAtakLewoImage.getScaledInstance((int)(0.098958*1920), (int)(0.05864*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczSwietlnyAtakPrawoImage() {
        Image bufImage = mieczSwietlnyAtakPrawoImage.getScaledInstance((int)(0.098958*1920), (int)(0.05864*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczSwietlnyPionowoLewoImage() {
        Image bufImage = mieczSwietlnyPionowoLewoImage.getScaledInstance((int)(0.0130208*1920), (int)(0.197222*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getMieczSwietlnyPionowoPrawoImage() {
        Image bufImage = mieczSwietlnyPionowoPrawoImage.getScaledInstance((int)(0.0130208*1920), (int)(0.197222*1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getBotLewoImage() {
        Image bufImage = botLewoImage.getScaledInstance((int) (0.02421875 * 1920), (int) (0.059259* 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }

    public Image getBotPrawoImage() {
        Image bufImage = botPrawoImage.getScaledInstance((int) (0.02421875 * 1920), (int) (0.059259* 1080), Image.SCALE_DEFAULT);
        return bufImage;
    }
}
