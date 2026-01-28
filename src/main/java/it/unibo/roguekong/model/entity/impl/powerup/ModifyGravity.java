package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

public class ModifyGravity implements PowerUp {
    private final double multiplier;

    public ModifyGravity(double multiplier){
        this.multiplier = multiplier;
    }

    @Override
    public void applyEffect(PlayerImpl player) {
        player.setVelocity(new VelocityImpl(player.getVelocity().getVelocityX(), player.getVelocity().getVelocityY() * multiplier));
    }

    @Override
    public void removeEffect(PlayerImpl player) {
        player.setVelocity(new VelocityImpl(player.getVelocity().getVelocityX(), player.getVelocity().getVelocityY() / multiplier));
    }

    @Override
    public String getName() {
        return "Low gravity";
    }

    @Override
    public String getDescription() {
        return "Lowers level gravity";
    }
}
