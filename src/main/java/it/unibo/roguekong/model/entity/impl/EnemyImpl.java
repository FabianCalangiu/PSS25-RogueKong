package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;

public class EnemyImpl implements Enemy {
    private double x=0;
    private double y=0;

    public EnemyImpl(){
        setX(0);
        setY(0);
    }

    public EnemyImpl(double x, double y){
        setX(x);
        setY(y);
    }

    public void traslateLinearTo(double x, double y){

    }

    public double getX() {
        return x;
    }

    private void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    private void setY(double y) {
        this.y = y;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public Velocity getVelocity() {
        return null;
    }

    @Override
    public void hitPlayer() {

    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
