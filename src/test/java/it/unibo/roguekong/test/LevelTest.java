package it.unibo.roguekong.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.game.impl.LevelModel;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class LevelTest {
    LevelModel level;
    PositionImpl spawnPoint;
    PositionImpl endPoint;
    PlayerImpl player;
    TileManager tileManager;

    @BeforeEach
    void setUp() {
        this.player = new PlayerImpl();
        this.spawnPoint = new PositionImpl(0, 0);
        this.endPoint = new PositionImpl(10, 10);
        this.tileManager = new TileManager(960, 640, 2);
        this.level = new LevelModel(this.spawnPoint, this.endPoint, List.of(), List.of(), this.player, this.tileManager);
    }

    @Test
    void checkIfLevelIsCompleteAtStart() {
        assertFalse(level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsCompleteAtEnd() {
        this.player.setPosition(endPoint.getX(), endPoint.getY());
        this.level.update();
        assertTrue(level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsNotCompleteIfPlayerIsNotAtTheStart() {
        this.player.setPosition(spawnPoint.getX(), spawnPoint.getY());
        this.level.update();
        assertFalse(level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsNotCompleteAfterTheReset() {
        this.level.init();
        assertFalse(level.isLevelComplete());
        assertEquals(this.player.getPosition(), this.level.getSpawnPoint());
    }
}
