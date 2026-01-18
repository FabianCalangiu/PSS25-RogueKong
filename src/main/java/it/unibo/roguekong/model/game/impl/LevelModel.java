package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.GamePlatform;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.value.Position;

import java.util.ArrayList;
import java.util.List;

public class LevelModel implements Level {
    private final List<Enemy> ENEMIES;
    private final List<GamePlatform> PLATFORMS;
    private final Position SPAWN_POSITION;
    private final Position END_POINT;
    private final Player PLAYER;
    private boolean isComplete;

    public LevelModel(final Position spawnPoint, final Position endPoint, final List<GamePlatform> platforms, final List<Enemy> enemies, final Player player) {
        this.SPAWN_POSITION = spawnPoint;
        this.END_POINT = endPoint;
        this.PLATFORMS = new ArrayList<>(platforms);
        this.ENEMIES = new ArrayList<>(enemies);
        this.PLAYER = player;
        this.isComplete = false;
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
        this.PLAYER.setPosition(SPAWN_POSITION);
    }

    private void checkLevel() {
        if(this.PLAYER.getPosition().equals(this.END_POINT)){
            this.isComplete = true;
        }
    }
}