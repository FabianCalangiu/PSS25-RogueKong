package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.game.impl.HitboxImpl;
import it.unibo.roguekong.model.game.impl.Tile;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.Lives;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.impl.LivesImpl;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player {

    private PositionImpl position = new PositionImpl();
    private HitboxImpl hitbox;
    private VelocityImpl velocity = new VelocityImpl();
    private boolean midAir = false;
    private List<PowerUp> activePowerUps = new ArrayList<PowerUp>();
    private String sprite = "";
    private LivesImpl lives = new LivesImpl();
    private static final int LIVES_AT_START = 3;
    private TileManager tileManager;

    public PlayerImpl() {
        hitbox = new HitboxImpl(getPosition(), 23, 32);
        setMidAir(true);
        setLives(new LivesImpl(LIVES_AT_START));
        setSprite("/assets/sprites/standing-mario.png");
    }

    @Override
    public LivesImpl getLives() {
        return lives;
    }

    @Override
    public void setLives(LivesImpl lives) {
        this.lives = lives;
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public PositionImpl getPosition() {
        return this.position;
    }

    @Override
    public VelocityImpl getVelocity() {
        return this.velocity;
    }

    @Override
    public boolean isMidAir() {
        return this.midAir;
    }

    @Override
    public List<PowerUp> getActivePowerUps() {
        return activePowerUps;
    }

    @Override
    public void addPowerUp(PowerUp powerUp) {
        this.activePowerUps.add(powerUp);
    }

    private void setSprite(String sprite) {
        this.sprite = sprite;
    }

    private void setMidAir(boolean midAir) {
        this.midAir = midAir;
    }

    @Override
    public void moveX(double x) {
        setPosition(x, getPosition().getY());
    }

    @Override
    public void moveY(double y) {
        setPosition(getPosition().getX(), y);
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    @Override
    public void setPosition(double x, double y) {
        if(!collidesAt(x, y)){
            getHitbox().moveHitBox(x, y);
            setXandY(new PositionImpl(getHitbox().getBounds().getMinX(), getHitbox().getBounds().getMinY()));
        }
    }

    private boolean collidesAt(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).isCollidable()
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).isCollidable();
    }

    private void setXandY(PositionImpl position) {
        this.position = position;
    }

    public HitboxImpl getHitbox() {
        return hitbox;
    }

    @Override
    public void setVelocity(VelocityImpl velocity) {
        this.velocity = velocity;
    }
}
