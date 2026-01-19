package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.GamePlatform;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.impl.PositionImpl;

import java.util.ArrayList;
import java.util.List;

public class LevelModel implements Level {
    private final List<Enemy> ENEMIES;
    private final List<GamePlatform> PLATFORMS;
    private final PositionImpl SPAWN_POSITION;
    private final PositionImpl END_POINT;
    private final PlayerImpl PLAYER;
    private boolean isComplete;
    private final TileManager tileManager;

    public LevelModel(final PositionImpl spawnPoint, final PositionImpl endPoint, final List<GamePlatform> platforms, final List<Enemy> enemies, final PlayerImpl player, final TileManager tileManager) {
        this.SPAWN_POSITION = spawnPoint;
        this.END_POINT = endPoint;
        this.PLATFORMS = new ArrayList<>(platforms);
        this.ENEMIES = new ArrayList<>(enemies);
        this.PLAYER = player;
        this.isComplete = false;
        this.tileManager = tileManager;
    }

    @Override
    public List<GamePlatform> getPlatforms() {
        return List.copyOf(PLATFORMS);
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(ENEMIES);
    }

    @Override
    public Player getPlayer() {
        return this.PLAYER;
    }

    @Override
    public Position getSpawnPoint() {
        return this.SPAWN_POSITION;
    }

    @Override
    public Position getEndPoint() {
        return this.END_POINT;
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
        this.PLAYER.setPosition(SPAWN_POSITION.getX(), SPAWN_POSITION.getY());
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    private void checkLevel() {
        if(this.PLAYER.getPosition().equals(this.END_POINT)){
            this.isComplete = true;
        }
    }
}