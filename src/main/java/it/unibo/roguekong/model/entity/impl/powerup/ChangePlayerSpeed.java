package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;

public class ChangePlayerSpeed implements PowerUp {
    private final double multiplier;

    ChangePlayerSpeed(double multiplier){
        this.multiplier = multiplier;
    }

    @Override
    public void applyEffect(Player player){
        /*
        Add body after player implementation!!
         */
    }

    @Override
    public void removeEffect(Player player){
        /*
        Add body after player implementation!!
         */
    }
}
