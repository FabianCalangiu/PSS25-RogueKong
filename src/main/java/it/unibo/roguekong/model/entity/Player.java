package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;
import java.util.List;

/**
 * This interface is going to represent player
 */

public interface Player{
    public Position getPosition();

    public Velocity getVelocity();

    public boolean isMidAir();

    public boolean isMoving();

    public List<PowerUp> getActivePowerUps();
}