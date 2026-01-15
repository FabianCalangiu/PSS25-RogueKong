package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.GamePlatform;

import java.util.List;

public interface Level {
    List<GamePlatform> getPlatforms();
    List<Enemy> getEnemies();

    double getSpawnPoint();
    double getFinalPoint();
}
