package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;

public class DoubleJump implements PowerUp {

    private final String name;
    private final String description;

    private Integer originalMaxJumps = null;

    public DoubleJump(){
        this.name = "Double Jump";
        this.description = "Grants a second jump to the player.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        if (this.originalMaxJumps == null) {
            this.originalMaxJumps = player.getMaxJumps();
        }
        player.setMaxJumps(this.originalMaxJumps + 1);
    }

    @Override
    public void removeEffect(PlayerImpl player){
        if (this.originalMaxJumps != null) {
            player.setMaxJumps(this.originalMaxJumps);
        }
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
