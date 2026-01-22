package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.*;

public class PlayerImpl implements Player {

    private PositionImpl position = new PositionImpl();
    private VelocityImpl velocity = new VelocityImpl();
    private boolean midAir = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean jump = false;
    private List<PowerUp> activePowerUps = new ArrayList<PowerUp>();
    private String sprite = "";

    public PlayerImpl() {
        setJump(true);
        setMoveRight(true);
        setMoveLeft(true);
        setMoveUp(false);
        setMidAir(false);
        setSprite("standing-mario.png");
    }

    @Override
    public String getSprite() {
        return this.sprite;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Velocity getVelocity() {
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
    public boolean canJump() {
        return this.jump;
    }

    @Override
    public boolean canMoveRight() {
        return this.moveRight;
    }

    @Override
    public boolean canMoveLeft() {
        return this.moveLeft;
    }

    @Override
    public boolean canMoveUp() {
        return this.moveUp;
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

    private void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    private void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    private void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    private void setJump(boolean jump) {
        this.jump = jump;
    }

    @Override
    public void moveX(double x) {
        setPosition(x, getPosition().getY());
    }

    @Override
    public void moveY(double y) {
        setPosition(getPosition().getX(), y);
    }

    @Override
    public void setPosition(double x, double y) {

        PositionImpl pos = new PositionImpl(position.getX(), position.getY());

        if(!canMoveRight() && position.getX() < x) {
            pos.setX(position.getX());
        } else if (!canMoveLeft() && position.getX() > x) {
            pos.setX(position.getX());
        } else {
            pos.setX(x);
        }

        if(!canMoveUp() && position.getY() < y) {
            pos.setY(position.getY());
        } else if (!canJump() && position.getY() < y) {
            pos.setY(position.getY());
        } else if (!isMidAir() && position.getY() > y) {
            pos.setY(position.getY());
        } else {
            pos.setY(y);
        }

        setXandY(pos);
    }

    private void setXandY(PositionImpl position) {
        this.position = position;
    }

    @Override
    public void setVelocity(VelocityImpl velocity) {
        this.velocity = velocity;
    }
}
