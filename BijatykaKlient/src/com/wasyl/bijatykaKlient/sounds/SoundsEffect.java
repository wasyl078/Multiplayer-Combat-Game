package com.wasyl.bijatykaKlient.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Random;

public class SoundsEffect {

    private static String soundName = "sounds\\jump 1.wav";
    private static Clip clip;
    private static Random generator = new Random();
    private static String[] pistolSoundsList = {"sounds\\pistol 1.wav","sounds\\pistol 2.wav","sounds\\pistol 3.wav"};

    public static void makeLightSaberSound() {
        soundName = "sounds\\light saber.wav";
        getSounds();
        clip.start();
    }

    public static void makeLightSaberHitSound() {
        if (generator.nextBoolean()) soundName = "sounds\\LSwall01.wav";
        else soundName = "sounds\\LSwall02.wav";
        getSounds();
        clip.start();
    }

    public static void makePistolSound() {
        int randomInt = generator.nextInt(3);
        soundName = pistolSoundsList[randomInt];
        getSounds();
        clip.start();
    }

    public static void makeBulletImactSound() {
        soundName = "sounds\\bullet impact sound.wav";
        getSounds();
        clip.start();
    }

    public static void makeAxeSwingSound() {
        soundName = "sounds\\sword swing 1.wav";
        getSounds();
        clip.start();
    }

    public static void makeForceShieldSound() {
        soundName = "sounds\\force shield 1.wav";
        getSounds();
        clip.start();
    }

    public static void makeGrenadeThrowSound() {
        soundName = "sounds\\granat rzut.wav";
        getSounds();
        clip.start();
    }

    public static void makeExplosionSound() {
        soundName = "sounds\\granat wybuch.wav";
        getSounds();
        clip.start();
    }

    public static void makeGrenadeBounceSound() {
        soundName = "sounds\\granat odbicie.wav";
        getSounds();
        clip.start();
    }

    private static void getSounds() {
        try {
            File file = new File(soundName);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            //  Thread.sleep(clip.getMicrosecondLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}