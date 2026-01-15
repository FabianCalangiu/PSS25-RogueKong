package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.Platform;

import java.util.List;

public interface Level {
    List<Platform> getPlatforms();
    List<Enemy> getEnemies();

    double getSpawnPoint();
    double getFinalPoint();
}
