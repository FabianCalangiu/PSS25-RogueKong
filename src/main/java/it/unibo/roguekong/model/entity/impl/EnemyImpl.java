package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.game.impl.HitboxImpl;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.impl.LivesImpl;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

public class EnemyImpl implements Enemy {
    private static final double W = 32;
    private static final double H = 32;
    private static final double LEFT_OFFSET = 9;
    private static final double RIGHT_OFFSET = 31;
    private PositionImpl position = new PositionImpl();
    private VelocityImpl velocity = new VelocityImpl();
    private static final int ENEMY_LIVES_AT_START = 1;
    private final LivesImpl lives = new LivesImpl(ENEMY_LIVES_AT_START);
    private boolean isMovable;
    private boolean isDead;
    private String sprite = "/assets/sprites/standing-mario.png";
    private HitboxImpl hitbox = new HitboxImpl(new PositionImpl(0, 0), W, H);
    private TileManager tileManager;
    private int dirX = 1;

    public EnemyImpl() {
        setIsMovable(false);
        setIsDead(false);
        setSprite("/assets/sprites/standing-mario.png");
        syncHitboxWithPosition();
    }

    public EnemyImpl(final PositionImpl position, final VelocityImpl velocity, final int lives) {
        setPositionInternal(position);
        setVelocityInternal(velocity);
        setLivesInternal(lives);
        setIsMovable(true);
        setIsDead(false);
        setSprite("/assets/sprites/standing-mario.png");
        syncHitboxWithPosition();
    }

    public EnemyImpl(final PositionImpl position) {
        setPositionInternal(position);
        setVelocityInternal(new VelocityImpl());
        setIsMovable(false);
        setIsDead(false);
        setSprite("/assets/sprites/standing-mario.png");
        syncHitboxWithPosition();
    }

    @Override
    public PositionImpl getPosition() {
        return this.position;
    }

    @Override
    public VelocityImpl getVelocity() {
        if (isDead()) {
            return null;
        }
        return this.velocity;
    }

    @Override
    public boolean hitPlayer(final PositionImpl playerPos) {
        return playerPos != null && playerPos.equals(this.position);
    }

    @Override
    public boolean isMovable() {
        if (isDead()) {
            return false;
        }
        return this.isMovable;
    }

    @Override
    public void setIsDead(final boolean isDead) {
        if (getLives() <= 0) {
            this.isDead = true;
            this.isMovable = false;
            return;
        }
        this.isDead = isDead;
        if (this.isDead) {
            this.isMovable = false;
        }
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public int getLives() {
        return this.lives.getLives();
    }

    @Override
    public void setIsMovable(final boolean isMovable) {
        if (isDead()) {
            this.isMovable = false;
            return;
        }
        this.isMovable = isMovable;
        if (!this.isMovable) {
            this.velocity = new VelocityImpl();
        }
    }

    @Override
    public String getSprite() {
        return sprite;
    }

    public void setSprite(final String sprite) {
        this.sprite = sprite;
    }

    public HitboxImpl getHitbox() {
        return this.hitbox;
    }

    public void setTileManager(final TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public void setDirectionX(final int dir) {
        this.dirX = (dir >= 0) ? 1 : -1;
    }

    public int getDirectionX() {
        return this.dirX;
    }

    public void setPosition(final double x, final double y) {
        if (isDead()) {
            return;
        }

        if (this.tileManager == null) {
            moveIgnoringTiles(x, y);
            return;
        }

        if (!collidesAt(x, y)) {
            moveIgnoringTiles(x, y);
        }
    }

    private void moveIgnoringTiles(final double x, final double y) {
        this.hitbox.moveHitBox(x, y);

        this.position = new PositionImpl(
                this.hitbox.getBounds().getMinX(),
                this.hitbox.getBounds().getMinY()
        );
    }

    private void syncHitboxWithPosition() {
        this.hitbox.moveHitBox(this.position.getX(), this.position.getY());
    }

    private boolean collidesAt(final double x, final double y) {
        final double left = x + LEFT_OFFSET;
        final double right = x + RIGHT_OFFSET;
        final double top = y;
        final double bottom = y + (H - 1);

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).isCollidable();
    }

    public boolean collidesWithPlayer(final Player player) {
        if (player instanceof PlayerImpl p) {
            return this.hitbox.isColliding(p.getHitbox());
        }
        return false;
    }

    public void patrolHorizontal(double speedPxPerFrame) {
        if (!isMovable() || isDead()) return;
        if (speedPxPerFrame <= 0) speedPxPerFrame = 0.5;

        final double x = position.getX();
        final double y = position.getY();

        final double beforeX = x;
        setPosition(x + dirX * speedPxPerFrame, y);

        if (position.getX() == beforeX) {
            dirX *= -1;
            setPosition(x + dirX * speedPxPerFrame, y);
        }
    }

    public void patrolHorizontalWithGravity(double speedPxPerFrame, double levelGravity) {
        if (!isMovable() || isDead()) return;

        patrolHorizontal(speedPxPerFrame);

        final double x = position.getX();
        final double y = position.getY();

        final double fall = levelGravity * this.velocity.getVelocityY();
        setPosition(x, y + fall);
    }

    public void chasePlayerIgnoreTiles(final Player player, double speedPxPerFrame) {
        if (!isMovable() || isDead() || player == null) return;
        if (speedPxPerFrame <= 0) speedPxPerFrame = 0.3;

        final double ex = position.getX();
        final double ey = position.getY();
        final double px = player.getPosition().getX();
        final double py = player.getPosition().getY();

        final double dx = px - ex;
        final double dy = py - ey;

        final double dist = Math.hypot(dx, dy);
        if (dist < 0.0001) return;

        this.dirX = (dx >= 0) ? 1 : -1;

        final double vx = (dx / dist) * speedPxPerFrame;
        final double vy = (dy / dist) * speedPxPerFrame;

        moveIgnoringTiles(ex + vx, ey + vy);
    }

    private void setVelocityInternal(final VelocityImpl velocity) {
        if (!isMovable()) {
            this.velocity = new VelocityImpl();
            return;
        }
        this.velocity = velocity;
    }

    private void setLivesInternal(final int lives) {
        if (lives < 0) {
            this.lives.setLivesByValue(0);
            return;
        }
        this.lives.setLivesByValue(lives);
    }

    private void setPositionInternal(final PositionImpl position) {
        this.position = position;
    }
}
