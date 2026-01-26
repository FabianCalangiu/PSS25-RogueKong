package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.value.impl.PositionImpl;

public interface Hitbox {
    public void moveHitBox(double x, double y);
    public PositionImpl getTl();
    public PositionImpl getTr();
    public PositionImpl getBl();
    public PositionImpl getBr();
}
