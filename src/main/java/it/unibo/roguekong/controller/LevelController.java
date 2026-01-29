package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.game.impl.LevelModel;

import java.util.List;

public class LevelController {

    private final List<LevelModel> levelsList;
    private int currentLevelIndex;

    public LevelController(List<LevelModel> levels) {
        this.levelsList = levels;
        this.currentLevelIndex = 0;
    }

    /**
     * Method that returns the Index
     */
    public int getCurrentLevelIndex() { return this.currentLevelIndex; }

    /**
     * Method that returns the list
     */
    public List<LevelModel>  getLevelsList() { return levelsList; }

    /**
     * Method that returns the current level
     */
    public LevelModel getCurrentLevel() {
        return levelsList.get(this.currentLevelIndex);
    }

    /**
     *Methods that check if there is another level after the current one
     */
    public boolean isThereAnotherLevel() {
        return this.currentLevelIndex < this.levelsList.size() - 1;
    }

    /**
     * Method that reset the index if the player loose or quit the game
     */
    public void reset() {
        this.currentLevelIndex = 0;
        LevelModel level = getCurrentLevel();
        level.init();
    }

    /**
     * Method that set and init the next level once the previous one is completed
     */
    public void nextLevel() {
        LevelModel level = getCurrentLevel();

        if(this.isThereAnotherLevel() && this.getCurrentLevel().isLevelComplete()){
            this.currentLevelIndex++;
            Level newLevel = getCurrentLevel();
            newLevel.init();
        }
    }

    public void setUpLevel() {
        LevelModel level = getCurrentLevel();
        level.init();
    }
}