package it.unibo.roguekong.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class will be used to play sounds in the game loop
 */

public class SoundManager {
    public static void play(String musicPath) {
        try{
            File sound = new File(musicPath);

            if(sound.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.setFramePosition(0);
                clip.start();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
