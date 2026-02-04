package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

/**
 * PowerUp that alters the player speed
 */
public class ChangePlayerSpeed implements PowerUp {
    private final double multiplier;
    private final String name;
    private final String description;

    private Double originalSpeed = null;

    /**
     * Creates an instance of a power up
     * @param multiplier needed in order to alter player's velocity
     */
    public ChangePlayerSpeed(double multiplier){
        this.multiplier = multiplier;
        this.name = "Speed Power Up";
        this.description = "Changes the player's speed.";
    }

    @Override
    public void applyEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();

        if(originalSpeed == null){
            originalSpeed = velocity.getVelocityX();
        }

        velocity.setVelocityX(originalSpeed * multiplier);
        player.setVelocity(velocity);
    }

    @Override
    public void removeEffect(PlayerImpl player){
        if(originalSpeed != null){
            VelocityImpl velocity = player.getVelocity();
            velocity.setVelocityX(originalSpeed);
            player.setVelocity(velocity);
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
