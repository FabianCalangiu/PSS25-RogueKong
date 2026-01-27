package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.game.impl.ScoreRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ScoreManager {
    /*
     * Gets path relative to the project root
     * Path type is a better choice in this case since the class writes, other than read
     * on the target file
     */
    private static final Path SCORE_FILE = Paths.get("saves","scores.txt");

    /*
     * This executes once when the ScoreManager class gets loaded
     * Creates directory if there is none
     */
    static {
        try {
            Files.createDirectories(SCORE_FILE.getParent());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Writes on the given file the score entry
     */
    public void saveScore(ScoreRecord entry){
        try {
            Files.writeString(
                    SCORE_FILE,
                    entry.name() + ";" + entry.score() + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
