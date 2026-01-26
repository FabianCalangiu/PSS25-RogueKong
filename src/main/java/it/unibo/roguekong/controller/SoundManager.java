package it.unibo.roguekong.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * This class will be used to play sounds in the game loop
 */

public class SoundManager {
    private static final float SOUND_VOLUME = -40.0f;

    public static void play(String musicPath) {
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundManager.class.getResource(musicPath));

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            /**
             * This part right below, must be used to change the volume sound
             */
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gain.setValue(SOUND_VOLUME);
            }

            /**
             * Restart every each call, the sound to zero
             */
            clip.setFramePosition(0);

            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
