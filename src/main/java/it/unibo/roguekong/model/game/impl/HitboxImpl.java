package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;

public class HitboxImpl implements Hitbox {
    private PositionImpl tl = new PositionImpl();
    private PositionImpl tr = new PositionImpl();
    private PositionImpl bl = new PositionImpl();
    private PositionImpl br = new PositionImpl();
    private final static double WIDTH = 32;
    private final static double HEIGHT = 32;

    public HitboxImpl() {}

    public HitboxImpl(PositionImpl tl) {
        moveHitBox(tl.getX(), tl.getY());
    }

    public void moveHitBox(double x, double y) {
        PositionImpl topLeft = getTl();
        PositionImpl topRight = getTr();
        PositionImpl bottomLeft = getBl();
        PositionImpl bottomRight = getBr();

        try{
            PositionImpl newTl = new PositionImpl(x, y);
            PositionImpl newTr = new PositionImpl(x + WIDTH, y);
            PositionImpl newBl = new PositionImpl(x, y + HEIGHT);
            PositionImpl newBr = new PositionImpl(x + WIDTH, y + HEIGHT);

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

    public boolean hitCheckRight(Hitbox hb){
        return getBr().getX() > hb.getBl().getX() || getTr().getX() > hb.getTr().getX();
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
