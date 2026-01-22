package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Enemy;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.value.Position;

import java.util.List;

public interface Level {
    List<Enemy> getEnemies();
    Player getPlayer();
    Position getSpawnPoint();
    Position getEndPoint();
    boolean isLevelComplete();
    void checkLevel();
    void init();
}
