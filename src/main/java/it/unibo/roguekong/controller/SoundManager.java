package it.unibo.roguekong.controller;

import javax.sound.sampled.*;

/**
 * This class will be used to play sounds in the game loop
 */
public class SoundManager {
    private final float volume;
    private Clip clip;
    private final String soundPath;

    public SoundManager(final String musicPath, final float volume) {
        this.soundPath = musicPath;
        this.volume = volume;
    }

    /**
     * Use this to play the music for the first time
     */
    public void play() {
        try{
            /**
             * Check if the sound is already running
             */
            if (clip != null && clip.isRunning()) {
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundManager.class.getResource(this.soundPath));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            /**
             * This part right below, must be used to change the volume sound
             */
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gain.setValue(this.volume);
            }

            clip.setFramePosition(0);

            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the game loop is set on pause, even the music need to be set on pause
     */
    public void stop() {
        this.clip.stop();
    }

    /**
     * When the game loop is set on resume, even the music need to be set on resume. So restart the sound where it got interrupted
     */
    public void restart() {
        this.clip.start();
    }
}
