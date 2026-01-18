package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.value.impl.*;

public class EnemyImpl implements Enemy {
    private PositionImpl position = new PositionImpl();
    private VelocityImpl velocity = new VelocityImpl();
    private final LivesImpl lives = new LivesImpl();
    private boolean isMovable;
    private boolean isDead;

    public EnemyImpl(){
        setIsMovable(false);
        setIsDead(false);
    }

    public EnemyImpl(PositionImpl position, VelocityImpl velocity, int lives){
        setPosition(position);
        setVelocity(velocity);
        setLives(lives);
        setIsMovable(true);
        setIsDead(false);
    }

    public EnemyImpl(PositionImpl position){
        setPosition(position);
        setVelocity(new VelocityImpl());
        setIsMovable(false);
        setIsDead(false);
    }

    @Override
    public PositionImpl getPosition() {
        return this.position;
    }

    @Override
    public VelocityImpl getVelocity() {
        if(isDead()){
            return null;
        }
        return this.velocity;
    }

    @Override
    public boolean hitPlayer(PositionImpl player) {
        return player.equals(this.position);
    }

    @Override
    public boolean isMovable() {
        if(isDead()){
            return false;
        }
        return this.isMovable;
    }

    @Override
    public void setIsDead(boolean isDead){
        if(getLives() <= 0){
            this.isDead = true;
        }
        this.isDead = isDead;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public int getLives(){
        return this.lives.getLives();
    }

    @Override
    public void setIsMovable(boolean isMovable){
        if(isDead()){
            this.isMovable = false;
        }
        this.isMovable = isMovable;
    }

    private void setVelocity(VelocityImpl velocity){
        if(!isMovable()){
            this.velocity = new VelocityImpl();
        }
        this.velocity = velocity;
    }

    private void setLives(int lives){
        if(lives < 0) {
            this.lives.setLivesByValue(0);
        }
        this.lives.setLivesByValue(lives);
    }

    private void setPosition(PositionImpl position){
        this.position = position;
    }
}
