package it.unibo.roguekong.model.entity.impl.powerup;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.game.impl.LevelModel;

public class ModifyGravity implements PowerUp {
    private double multiplier;


    @Override
    public void applyEffect(LevelModel levelModel) {
        levelModel.setGravityOnPlayer();
    }

    @Override
    public void removeEffect(PlayerImpl player) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
