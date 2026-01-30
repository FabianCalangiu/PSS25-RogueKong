package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.impl.PositionImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the logic of each level
 */
public class LevelModel implements Level {
    private final List<Enemy> enemies;
    private final PositionImpl spawnPosition;
    private final PositionImpl endPoint;
    private final PlayerImpl player;
    private boolean isComplete;
    private final TileManager tileManager;
    private double gravity;

    /**
     * Create a new LevelModel
     * @param spawnPoint is the player position spawn
     * @param endPoint is the escape position of each level
     * @param enemies is a list that contains every enemy of each level
     * @param player is the main player
     * @param tileManager the map manager of each level
     * @param gravity effect that changes the Y axis player velocity
     */
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
    public List<Enemy> getEnemies() { return List.copyOf(enemies); }

    @Override
    public PlayerImpl getPlayer() { return this.player; }

    @Override
    public PositionImpl getSpawnPoint() { return this.spawnPosition; }

    @Override
    public PositionImpl getEndPoint() { return this.endPoint; }

    @Override
    public boolean isLevelComplete() { return this.isComplete; }

    public TileManager getTileManager() { return tileManager; }

    public double getLevelGravity(){ return this.gravity; }

    /**
     * This method initialize each level, setting the player position on the spawn point
     */
    @Override
    public void init() {
        this.isComplete = false;
        this.setPlayerOnSpawn();
//        this.setGravityOnPlayer();
    }

    /**
     * Check if the player position is equals to the endpoint position
     */
    @Override
    public void checkIfPlayerIsOnEndPoint() {
        if(this.player.getPosition().equals(this.endPoint)){
            this.isComplete = true;
        }
    }

    /**
     * Set the gravity of each level
     * @param gravity world
     */
    public void setLevelGravity(double gravity){
        this.gravity = gravity;
        this.setGravityOnPlayer();
    }

    /**
     * Change the Y axis velocity of the player based to the gravity
     */
    public void setGravityOnPlayer() {
        this.player.setPosition(this.player.getPosition().getX(), this.player.getPosition().getY() + (this.gravity * this.player.getVelocity().getVelocityY()));
    }

    /**
     * Set the player spawnPosition
     */
    private void setPlayerOnSpawn() {
        this.player.setPosition(this.spawnPosition.getX(), this.spawnPosition.getY());
    }
}