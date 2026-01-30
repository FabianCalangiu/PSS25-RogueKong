package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;

/**
 * Simply increases the player's healthpoints if chosen
 */
public class IncreasePlayerLives implements PowerUp {
    private final static int NUMBER_OF_LIVES = 1;
    private String name;
    private String description;

    public IncreasePlayerLives(){
        this.name = "Life up";
        this.description = "Increases player's lives.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        /*
        Add body after player implementation!!
         */
    }

    @Override
    public void removeEffect(PlayerImpl player){
        /*
        Add body after player implementation!!
         */
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
