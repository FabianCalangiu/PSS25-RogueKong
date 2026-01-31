package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import javafx.geometry.Rectangle2D;

public class HitboxImpl implements Hitbox {
    Rectangle2D hitbox ;

    public HitboxImpl() { //void constructor
        setHitbox(new Rectangle2D(0,0,0,0));
    }

    public HitboxImpl(PositionImpl tl) { //base constructor
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), 32, 32));
    }

    public HitboxImpl(PositionImpl tl, double width, double height) { //constructor with width and height
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), width, height));
    }

    public void moveHitBox(double x, double y, HitboxImpl hitbox) { //check if the hitbox is moveing without colliding with another Hitbox
        if(!isColliding(hitbox)) {
            moveHitBox(x, y);
        }
    }

    public void moveHitBox(double x, double y) { //check if the hitbox is within screen bounds
        final double SCREEN_W = 960;
        final double SCREEN_H = 640;

        final Rectangle2D screen = new Rectangle2D(0, 0, SCREEN_W, SCREEN_H);

        Rectangle2D candidate = new Rectangle2D(x, y, getBounds().getWidth(), getBounds().getHeight());

        if (screen.contains(candidate)) {
            setHitbox(candidate);
        }
    }

    public boolean isColliding(HitboxImpl hb) { //return true when the other hitbox is toutching this one
        return getBounds().intersects(hb.getBounds());
    }

    public Rectangle2D getBounds() {
        return hitbox;
    }

    private void setHitbox(Rectangle2D rect) {
        this.hitbox = rect;
    }
}
