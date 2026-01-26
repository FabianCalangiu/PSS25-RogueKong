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
    private static Clip clip;

    public static void play(String musicPath) {
        try{
            /**
             * Check if the sound is already running
             */
            if (clip != null && clip.isRunning()) {
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundManager.class.getResource(musicPath));

            clip = AudioSystem.getClip();
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
