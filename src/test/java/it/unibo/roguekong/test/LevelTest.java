package it.unibo.roguekong.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.game.Level;
import it.unibo.roguekong.model.game.impl.LevelImpl;
import it.unibo.roguekong.model.value.Position;
import it.unibo.roguekong.model.value.Velocity;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class LevelTest {
    Level level;
    Position spawnPoint;
    Position endPoint;
    Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player() {
            Position position = new PositionImpl(0, 0);

            @Override
            public Position getPosition() {
                return this.position;
            }

            @Override
            public Velocity getVelocity() {
                return null;
            }

            @Override
            public boolean isMidAir() {
                return false;
            }

            @Override
            public boolean isMoving() {
                return false;
            }

            @Override
            public List<PowerUp> getActivePowerUps() {
                return List.of();
            }

            @Override
            public void setPosition(Position spawnPoint) {
                this.position = spawnPoint;
            }
        };

        this.spawnPoint = new PositionImpl(0, 0);
        this.endPoint = new PositionImpl(10, 10);
        this.level = new LevelImpl(this.spawnPoint, this.endPoint, List.of(), List.of(), player);
    }

    @Test
    void checkIfLevelIsCompleteAtStart() {
        assertFalse(level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsCompleteAtEnd() {
        this.player.setPosition(endPoint);
        this.level.update();
        assertTrue(level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsNotCompleteIfPlayerIsNotAtTheStart() {
        this.player.setPosition(new PositionImpl(5, 5));
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
