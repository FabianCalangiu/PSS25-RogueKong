package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

public class ChangePlayerSpeed implements PowerUp {
    private final double multiplier;
    private String name;
    private String description;

    public ChangePlayerSpeed(double multiplier){
        this.multiplier = multiplier;
        this.name = "Speed Power Up";
        this.description = "Changes the player's speed.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();
        velocity.setVelocityX(player.getVelocity().getVelocityX() * multiplier);
        player.setVelocity(velocity);
    }

    @Override
    public void removeEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();
        velocity.setVelocityX(player.getVelocity().getVelocityX() / multiplier);
        player.setVelocity(velocity);
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
