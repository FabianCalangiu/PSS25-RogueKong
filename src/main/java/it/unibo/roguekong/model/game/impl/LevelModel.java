package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.GamePlatform;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.model.value.impl.VelocityImpl;

import java.util.ArrayList;
import java.util.List;

public class LevelModel implements Level {
    private final List<Enemy> enemies;
    private final List<GamePlatform> platforms;
    private final PositionImpl spawnPosition;
    private final PositionImpl endPoint;
    private final PlayerImpl player;
    private boolean isComplete;
    private final TileManager tileManager;
    private final int gravity;

    public LevelModel(
            final PositionImpl spawnPoint,
            final PositionImpl endPoint,
            final List<GamePlatform> platforms,
            final List<Enemy> enemies,
            final PlayerImpl player,
            final TileManager tileManager,
            final int gravity) {
        this.spawnPosition = spawnPoint;
        this.endPoint = endPoint;
        this.platforms = new ArrayList<>(platforms);
        this.enemies = new ArrayList<>(enemies);
        this.player = player;
        this.isComplete = false;
        this.tileManager = tileManager;
        this.gravity = gravity;
    }

    @Override
    public List<GamePlatform> getPlatforms() {
        return List.copyOf(platforms);
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Position getSpawnPoint() {
        return this.spawnPosition;
    }

    @Override
    public Position getEndPoint() {
        return this.endPoint;
    }

    @Override
    public boolean isLevelComplete() {
        return this.isComplete;
    }

    @Override
    public void update() {
        this.checkLevel();
    }

    @Override
    public void init() {
        this.isComplete = false;
        this.player.setPosition(this.spawnPosition.getX(), this.spawnPosition.getY());
        this.setGravityOnPlayer();
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    private void checkLevel() {
        if(this.player.getPosition().equals(this.endPoint)){
            this.isComplete = true;
        }
    }

    private void setGravityOnPlayer() {
        this.player.setVelocity(new VelocityImpl(1, 1 * this.gravity));
    }
}