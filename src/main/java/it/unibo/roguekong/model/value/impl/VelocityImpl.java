package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Velocity;
public class VelocityImpl implements Velocity {
    private double velocityX;
    private double velocityY;

    public VelocityImpl(double x, double y){
        setVelocityX(x);
        setVelocityY(y);
    }
    public VelocityImpl(){
        setVelocityX(1);
        setVelocityY(1);
    }
    public VelocityImpl(Velocity velocity){
        setVelocityX(velocity.getVelocityX());
        setVelocityY(velocity.getVelocityY());
    }

    public void setVelocityX(double velocityX){
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }

    @Override
    public double getVelocityX(){
        return velocityX;
    }

    @Override
    public double getVelocityY(){
        return velocityY;
    }

    public void resetVelocity(){
        this.velocityX = 1;
        this.velocityY = 1;
    }
}
