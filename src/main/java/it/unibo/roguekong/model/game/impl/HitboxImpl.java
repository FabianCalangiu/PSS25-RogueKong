package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;

public class HitboxImpl implements Hitbox {
    private PositionImpl tl = new PositionImpl();
    private PositionImpl tr = new PositionImpl();
    private PositionImpl bl = new PositionImpl();
    private PositionImpl br = new PositionImpl();

    public HitboxImpl(PositionImpl tl, double width, double height) {
        setHitBox(tl, width, height);
    }

    public HitboxImpl(PositionImpl tl){
        setHitBox(tl, 32, 32);
    }

    @Override
    public void setHitBox(PositionImpl tl, double width, double height) {
        PositionImpl topLeft = getTl();
        PositionImpl topRight = getTr();
        PositionImpl bottomLeft = getBl();
        PositionImpl bottomRight = getBr();

        try{
            this.tl = tl;
            this.tr = new PositionImpl(getTl().getX()+width, getTl().getY());
            this.bl = new PositionImpl(getTl().getX(), getTl().getY()+height);
            this.br = new PositionImpl(getTr().getX(), getBl().getY());
        }catch(IllegalArgumentException e){
            this.tl = getTl();
            this.tr = getTr();
            this.bl = getBl();
            this.br = getBr();
        }
    }

    public void moveHitBox(double x, double y) {
        PositionImpl topLeft = getTl();
        PositionImpl topRight = getTr();
        PositionImpl bottomLeft = getBl();
        PositionImpl bottomRight = getBr();

        try{
            this.tl = new PositionImpl(getTl().getX()+x, getTl().getY()+y);
            this.tr = new PositionImpl(getTr().getX()+x, getTr().getY()+y);
            this.bl = new PositionImpl(getBl().getX()+x, getBl().getY()+y);
            this.br = new PositionImpl(getBr().getX()+x, getBr().getY()+y);
        }catch(IllegalArgumentException e){
            this.tl = getTl();
            this.tr = getTr();
            this.bl = getBl();
            this.br = getBr();
        }
    }

    public boolean destra(Hitbox hb){
        //se colpisce destra torna false
        return false;
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
