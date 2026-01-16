package it.unibo.roguekong.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.game.Level;
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


    @BeforeEach
    void setUp() {
        Player player = new Player() {
            Position position;
            Velocity velocity;

            @Override
            public Position getPosition() {
                return this.position;
            }

            @Override
            public Velocity getVelocity() {
                return this.velocity;
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
    }
}
