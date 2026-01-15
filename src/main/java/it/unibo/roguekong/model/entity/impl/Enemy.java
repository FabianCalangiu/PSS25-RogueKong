package it.unibo.roguekong.model.entity.impl;

public class Enemy {
    private double x=0;
    private double y=0;

    public Enemy(){
        setX(0);
        setY(0);
    }

    public Enemy(double x, double y){
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
}
