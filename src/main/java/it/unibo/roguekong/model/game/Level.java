package it.unibo.roguekong.model.game;

public interface Level {
    List<Platform> getPlatforms();
    List<Enemy> getEnemies();

    double getSpawnPoint();
    double getFinalPoint();
}
