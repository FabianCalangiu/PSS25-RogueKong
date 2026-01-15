package it.unibo.roguekong.model.value.impl;

public class Velocity {
    private double velocityX=0;
    private double velocityY=0;

    public Velocity(double x, double y){
        setVelocityX(x);
        setVelocityY(y);
    }
    public Velocity(){
        setVelocityX(0);
        setVelocityY(0);
    }
    public Velocity(Velocity velocity){
        setVelocityX(velocity.getVelocityX());
        setVelocityY(velocity.getVelocityY());
    }

    private void setVelocityX(double velocityX){
        this.velocityX = velocityX;
    }
    private void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }
    public double getVelocityX(){
        return velocityX;
    }
    public double getVelocityY(){
        return velocityY;
    }
}
