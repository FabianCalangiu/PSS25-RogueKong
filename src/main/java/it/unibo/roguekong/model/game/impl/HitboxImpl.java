package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;

public class HitboxImpl implements Hitbox {
    private PositionImpl pos = new PositionImpl();
    private double minX = pos.getX()+0;
    private double maxX = pos.getX()+0;
    private double minY = pos.getY()+0;
    private double maxY = pos.getY()+0;

    public HitboxImpl() {

    }

    private void setHitBoxX(double length) {
        this.minX = pos.getX()+(length/2);
        this.maxX = pos.getX()-(length/2);
    }

    private void setHitBoxY(double height) {
        this.minY = pos.getY()+(height/2);
        this.maxY = pos.getY()-(height/2);
    }

    private void setHitBox(double length,  double height) {
        setHitBoxX(length);
        setHitBoxY(height);
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }
}
