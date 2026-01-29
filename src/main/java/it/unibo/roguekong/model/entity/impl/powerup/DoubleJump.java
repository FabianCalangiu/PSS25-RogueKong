package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;

public class DoubleJump implements PowerUp {
    private String name;
    private String description;

    public DoubleJump(){
        this.name = "Double Jump";
        this.description = "Grants a second jump to the player.";
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
