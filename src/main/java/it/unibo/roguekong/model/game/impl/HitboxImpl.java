package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;

public class HitboxImpl implements Hitbox {
    private PositionImpl tl = new PositionImpl();
    private PositionImpl tr = new PositionImpl();
    private PositionImpl bl = new PositionImpl();
    private PositionImpl br = new PositionImpl();
    private double width = 32;
    private double height = 32;

    public HitboxImpl() {}

    public HitboxImpl(PositionImpl tl) {
        moveHitBox(tl.getX(), tl.getY());
    }

    public HitboxImpl(PositionImpl tl, double height, double width) {
        moveHitBox(tl.getX(), tl.getY());
        setHeight(height);
        setWidth(width);
    }

    public void moveHitBox(double x, double y, HitboxImpl hitbox) {
        if(!isColliding(hitbox)) {
            moveHitBox(x, y);
        }
    }

    public void moveHitBox(double x, double y) {
        PositionImpl topLeft = getTl();
        PositionImpl topRight = getTr();
        PositionImpl bottomLeft = getBl();
        PositionImpl bottomRight = getBr();

        try{
            PositionImpl newTl = new PositionImpl(x, y);
            PositionImpl newTr = new PositionImpl(x + getWidth(), y);
            PositionImpl newBl = new PositionImpl(x, y + getHeight());
            PositionImpl newBr = new PositionImpl(x + getWidth(), y + getHeight());

            this.tl = newTl;
            this.tr = newTr;
            this.bl = newBl;
            this.br = newBr;
        }catch(IllegalArgumentException e){
            this.tl = getTl();
            this.tr = getTr();
            this.bl = getBl();
            this.br = getBr();
        }
    }

    public boolean isColliding(HitboxImpl hb) { //return true when the other hitbox is toutching this one
        if((getTl().getX() > hb.getTl().getX()   &&   getTl().getX() < hb.getTr().getX())
                && (getTl().getY() > hb.getTl().getY()   &&   getTl().getY() < hb.getBl().getY())) {
            return true;
        }if((getTr().getX() > hb.getTl().getX()   &&   getTr().getX() < hb.getTr().getX())
                && (getTr().getY() > hb.getTl().getY()   &&   getTr().getY() < hb.getBl().getY())) {
            return true;
        }if((getBl().getX() > hb.getTl().getX()   &&   getBl().getX() < hb.getTr().getX())
                && (getBl().getY() > hb.getTl().getY()   &&   getBl().getY() < hb.getBl().getY())) {
            return true;
        }if((getBr().getX() > hb.getTl().getX()   &&   getBr().getX() < hb.getTr().getX())
                && (getBr().getY() > hb.getTl().getY()   &&   getBr().getY() < hb.getBl().getY())) {
            return true;
        }
        return false;
    }

    public double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        this.height = height;
    }

    public PositionImpl getTl() {
        return tl;
    }

    public PositionImpl getTr() {
        return tr;
    }

    public PositionImpl getBl() {
        return bl;
    }

    public PositionImpl getBr() {
        return br;
    }
}
