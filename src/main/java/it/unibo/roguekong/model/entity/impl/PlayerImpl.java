package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.game.impl.HitboxImpl;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.game.impl.TileType;
import it.unibo.roguekong.model.value.impl.LivesImpl;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.ArrayList;
import java.util.List;

public class PlayerImpl implements Player {
    private static final double GRAVITY = 0.05;
    private static final double MAX_FALL_SPEED = 2;
    private static final int LIVES_AT_START = 3;

    private PositionImpl position = new PositionImpl();
    private HitboxImpl hitbox;
    private VelocityImpl velocity = new VelocityImpl();
    private int remainingJumps;
    private int maxJumps;
    private int jumpForce;
    private List<PowerUp> activePowerUps = new ArrayList<PowerUp>();
    private String sprite = "";
    private LivesImpl lives = new LivesImpl();
    private TileManager tileManager;

    public PlayerImpl() {
        hitbox = new HitboxImpl(getPosition(), 23, 32);
        this.maxJumps = 2;
        this.remainingJumps = this.maxJumps;
        this.jumpForce = 3;
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

    public void incrementMaxJumps(){
        this.maxJumps++;
        this.remainingJumps = this.maxJumps;
    }

    public int getMaxJumps(){
        return this.maxJumps;
    }

    public void setMaxJumps(int maxJumps){
        this.maxJumps = maxJumps;
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

    /**
     * Check if player is on a ladder tile type
     * @param x player world position
     * @param y player world position
     * @return boolean value, that evaluates if player is or is not on a ladder
     */
    public boolean collidesWithLadder(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).getTileType() == TileType.LADDER
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).getTileType() == TileType.LADDER;
    }

    /**
     * Check if player collides with a tile that deals damage
     * @param x player position
     * @param y player position
     * @return boolean value, that evaluates if player got hit or not
     */
    public boolean isPlayerHit(double x, double y) {
        double left = x;
        double right = x + 31;
        double top = y;
        double bottom = y + 31;

        return tileManager.getTileAtPosition(new PositionImpl(left, top)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(right, top)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(left, bottom)).isCanDealDamage()
                || tileManager.getTileAtPosition(new PositionImpl(right, bottom)).isCanDealDamage();
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

    public void hit(){
        this.lives.decrementLives();
    }

    public void resetPlayerStatus(){
        setLives(new LivesImpl(LIVES_AT_START));
        this.maxJumps = 1;
    }

    public void jump(){
        if(this.remainingJumps > 0) {
            this.velocity.setVelocityY(-jumpForce);
            this.remainingJumps--;
        }
    }

    /**
     * Must be used to update players physic
     */
    public void setGravityOnPlayer() {
        this.velocity.setVelocityY(Math.min(this.velocity.getVelocityY() + this.GRAVITY, this.MAX_FALL_SPEED));

        if (!collidesAt(this.position.getX(), this.position.getY() + this.velocity.getVelocityY())) {
            moveY(this.position.getY() + this.velocity.getVelocityY());
        } else {
            if (this.velocity.getVelocityY() > 0) {
                this.remainingJumps = this.maxJumps;
            }
            this.velocity.setVelocityY(0);
        }
    }
}

