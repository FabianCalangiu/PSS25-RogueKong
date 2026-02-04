package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;

public class ChangePlayerGravity implements PowerUp {
    private final String name;
    private final String description;
    private final double multiplier;

    private Double originalGravity = null;

    ChangePlayerGravity(double multiplier){
        this.multiplier = multiplier;
        this.name = "Alter gravity";
        this.description = "Changes player's gravity.";
    }


    @Override
    public void applyEffect(PlayerImpl player) {

    }

    @Override
    public void removeEffect(PlayerImpl player) {

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
