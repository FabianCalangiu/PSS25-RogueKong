package it.unibo.roguekong.model.game;

public interface Level {
    List<Platform> getPlatforms();
    List<Enemy> getEnemies();

    double getSpawnPointX();
    double getSpawnPointY();

    double getFinalPointX();
    double getFinalPointY();
}
