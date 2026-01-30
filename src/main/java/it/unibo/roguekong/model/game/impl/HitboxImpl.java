package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import javafx.geometry.Rectangle2D;

public class HitboxImpl implements Hitbox {
    Rectangle2D hitbox ;

    public HitboxImpl() {
        setHitbox(new Rectangle2D(0,0,0,0));
    }

    public HitboxImpl(PositionImpl tl) {
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), 32, 32));
    }

    public HitboxImpl(PositionImpl tl, double height, double width) {
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), width, height));
    }

    public void moveHitBox(double x, double y, HitboxImpl hitbox) {
        if(!isColliding(hitbox)) {
            moveHitBox(x, y);
        }
    }

    public void moveHitBox(double x, double y) {
        final double SCREEN_W = 960;
        final double SCREEN_H = 640;

        final Rectangle2D screen = new Rectangle2D(0, 0, SCREEN_W, SCREEN_H);

        final Rectangle2D candidate = new Rectangle2D(x, y, getBounds().getWidth(), getBounds().getHeight());

        if (screen.contains(candidate)) {
            setHitbox(candidate);
        }
    }

    public boolean isColliding(HitboxImpl hb) { //return true when the other hitbox is toutching this one
        Rectangle2D a = getBounds();
        Rectangle2D b = hb.getBounds();
        return a.intersects(b);
    }

    public Rectangle2D getBounds() {
        return hitbox;
    }

    private void setHitbox(Rectangle2D rect) {
        this.hitbox = rect;
    }
}
