package it.unibo.roguekong.test;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.entity.impl.powerup.*;
import org.junit.jupiter.api.BeforeEach;

public class PowerUpsTest {
    private PlayerImpl player;
    private ChangePlayerSpeed changePlayerSpeed;

    @BeforeEach
    void setUp(){
        player = new PlayerImpl();
        ChangePlayerSpeed changePlayerSpeed = new ChangePlayerSpeed(1.4);
        DoubleJump doubleJump = new DoubleJump();
        ChangePlayerGravity changePlayerGravity = new ChangePlayerGravity(0.5);
        Invulnerability invulnerability = new Invulnerability();
        IncreasePlayerLives increasePlayerLives = new IncreasePlayerLives();
    }
}
