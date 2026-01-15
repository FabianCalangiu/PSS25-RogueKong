package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.value.*;
/**
 * This interface is going to represent the game enemies
 */

public interface Enemy {
    public Position getPosition();

    public Velocity getVelocity();

    public void hitPlayer();

    public boolean isMovable();

    public boolean isDead();
}
