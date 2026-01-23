package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.LevelBuilder;
import it.unibo.roguekong.model.value.impl.PositionImpl;

import java.util.List;
import java.util.Objects;

/**
 * This is a LevelModel Builder, to guarantee dryness when we need to create more than one level
 */

public class LevelBuilderImpl implements LevelBuilder {
    private List<Enemy> enemies;
    private PositionImpl spawnPosition;
    private PositionImpl endPoint;
    private PlayerImpl player;
    private TileManager tileManager;
    private int gravity;

    public LevelBuilderImpl setSpawnPosition(final PositionImpl spawnPosition) {
        this.spawnPosition = spawnPosition;
        return this;
    }

    public LevelBuilderImpl setEndPoint(final PositionImpl endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public LevelBuilderImpl setPlayer(final PlayerImpl player) {
        this.player = player;
        return this;
    }

    public LevelBuilderImpl setTileManager(final TileManager tileManager) {
        this.tileManager = tileManager;
        return this;
    }

    public LevelBuilderImpl setGravity(final int gravity) {
        this.gravity = gravity;
        return this;
    }

    public LevelBuilderImpl setEnemiesList(final List<Enemy> enemies) {
        this.enemies = enemies;
        return this;
    }

    public LevelModel build() {
        Objects.requireNonNull(this.spawnPosition, "Spawn position is required");
        Objects.requireNonNull(this.enemies, "Enemies is required");
        Objects.requireNonNull(this.endPoint, "End point is required");
        Objects.requireNonNull(this.player, "Player is required");
        Objects.requireNonNull(this.gravity, "Gravity is required");
        Objects.requireNonNull(this.tileManager, "TileManager is required");

        return new LevelModel(this.spawnPosition, this.endPoint, this.enemies, this.player, this.tileManager, this.gravity);
    }
}
