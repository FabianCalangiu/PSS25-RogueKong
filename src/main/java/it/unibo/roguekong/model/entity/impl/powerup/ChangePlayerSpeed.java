package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

public class ChangePlayerSpeed implements PowerUp {
    private final double multiplier;

    ChangePlayerSpeed(double multiplier){
        this.multiplier = multiplier;
    }

    @Override
    public void applyEffect(PlayerImpl player){
        VelocityImpl velocity = player.getVelocity();
        velocity.setVelocityX(multiplier);
        player.setVelocity(velocity);
    }

    @Override
    public void removeEffect(PlayerImpl player){
        /*
        Add body after player implementation!!
         */
    }
}
