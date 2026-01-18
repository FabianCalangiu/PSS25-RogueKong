package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Position;

public class PositionImpl implements Position {

    private double x=0;
    private double y=0;

    public PositionImpl(){
        this(0, 0);
    }
    public PositionImpl(double x, double y){
        this.x = x;
        this.y = y;
    }
    public PositionImpl(Position position){
        setX(position.getX());
        setY(position.getY());
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;

        if(!(other instanceof Position otherPosition)) return false;

        return Double.compare(this.x, otherPosition.getX()) == 0 && Double.compare(this.y, otherPosition.getY()) == 0;
    }
}
