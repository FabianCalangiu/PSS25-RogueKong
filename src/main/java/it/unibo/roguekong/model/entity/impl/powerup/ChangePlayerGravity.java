package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;


/**
 * Power up that alters the player's gravity
 */
public class ChangePlayerGravity implements PowerUp {
    private final String name;
    private final String description;
    private final double multiplier;

    private Double originalGravity = null;

    /**
     * Creates an instance of the powerup
     * @param multiplier needed in order to alter the player's gravity
     */
    ChangePlayerGravity(double multiplier){
        this.multiplier = multiplier;
        this.name = "Alter gravity";
        this.description = "Changes player's gravity.";
    }


    @Override
    public void applyEffect(PlayerImpl player) {
        if(this.originalGravity == null){
            this.originalGravity = player.getGravity().gravity();
        }

        player.setGravity(player.getGravity().gravity() * this.multiplier, player.getGravity().max_fall_speed());
    }

    @Override
    public void removeEffect(PlayerImpl player) {
        if(this.originalGravity != null){
            player.setGravity(this.originalGravity, player.getGravity().max_fall_speed());
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
