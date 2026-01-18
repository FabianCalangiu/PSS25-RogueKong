package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Level;

import java.util.List;

public class LevelHandler {
    private static final int TOTAL_NUMBERS_OF_LEVEL = 5;

    private final List<Level> LEVELS;
    private int currentLevel;

    public LevelHandler(List<Level> levels) {
        this.LEVELS = levels;
        this.currentLevel = 0;
    }

    /**
     * Method that returns the current level
     */
    public Level getCurrentLevel() {
        return LEVELS.get(this.currentLevel);
    }

    /**
     *Methods that check if there is another level after the current one
     */
    public boolean isThereAnotherLevel() {
        return this.currentLevel < this.LEVELS.size() - 1;
    }

    /**
     * Method that reset the index if the player loose or quit the game
     */
    public void reset(){
        this.currentLevel = 0;
    }

    /*public void nextLevel(){
        Level level = getCurrentLevel();

        if(level.isLevelComplete() && isThereAnotherLevel()){
            this.currentLevel++;
            Level newLevel = getCurrentLevel();
            newLevel.init();
        }
    }*/
}
