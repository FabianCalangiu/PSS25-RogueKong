package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.GamePlatform;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.Position;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements Level {
    private final List<Enemy> enemies;
    private final List<GamePlatform> platforms;
    private final Position spawnPoint;
    private final Position endPoint;
    private final Player player;
    private boolean isComplete;

    public LevelImpl(final Position spawnPoint, final Position endPoint, final List<GamePlatform> platforms, final List<Enemy> enemies, final Player player) {
        this.spawnPoint = spawnPoint;
        this.endPoint = endPoint;
        this.platforms = new ArrayList<>(platforms);
        this.enemies = new ArrayList<>(enemies);
        this.player = player;
        this.isComplete = false;
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
        return this.spawnPoint;
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
        this.player.setPosition(spawnPoint);
    }

    private void checkLevel() {
        if(this.player.getPosition().equals(this.endPoint)){
            this.isComplete = true;
        }
    }
}