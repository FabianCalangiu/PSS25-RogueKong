package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.GamePlatform;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.value.Position;

import java.util.List;

public interface Level {
    List<GamePlatform> getPlatforms();
    List<Enemy> getEnemies();
    Player getPlayer();

    Position getSpawnPoint();
    Position getFinalPoint();
}
