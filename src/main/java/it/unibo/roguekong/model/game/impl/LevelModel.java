package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.ArrayList;
import java.util.List;

public class LevelModel implements Level {
    private final List<Enemy> enemies;
    private final PositionImpl spawnPosition;
    private final PositionImpl endPoint;
    private final PlayerImpl player;
    private boolean isComplete;
    private final TileManager tileManager;
    private double gravity;

    public LevelModel(
            final PositionImpl spawnPoint,
            final PositionImpl endPoint,
            final List<Enemy> enemies,
            final PlayerImpl player,
            final TileManager tileManager,
            final int gravity) {
        this.spawnPosition = spawnPoint;
        this.endPoint = endPoint;
        this.enemies = new ArrayList<>(enemies);
        this.player = player;
        this.isComplete = false;
        this.tileManager = tileManager;
        this.gravity = gravity;
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

    @Override
    public PlayerImpl getPlayer() {
        return this.player;
    }

    @Override
    public PositionImpl getSpawnPoint() {
        return this.spawnPosition;
    }

    @Override
    public PositionImpl getEndPoint() {
        return this.endPoint;
    }

    @Override
    public boolean isLevelComplete() {
        return this.isComplete;
    }

    @Override
    public void init() {
        this.isComplete = false;
        this.setPlayerOnSpawn();
        this.setGravityOnPlayer();
    }

    @Override
    public void checkLevel() {
        if(this.player.getPosition().equals(this.endPoint)){
            this.isComplete = true;
        }
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public double getLevelGravity(){
        return this.gravity;
    }

    public void setLevelGravity(double gravity){
        this.gravity = gravity;
    }

    public void setGravityOnPlayer() {
        this.player.setPosition(this.player.getPosition().getX(), this.player.getPosition().getY() + (this.gravity * this.player.getVelocity().getVelocityY()));
    }

    private void setPlayerOnSpawn() {
        this.player.setPosition(this.spawnPosition.getX(), this.spawnPosition.getY());
    }
}