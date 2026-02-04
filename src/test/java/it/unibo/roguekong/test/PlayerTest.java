package it.unibo.roguekong.test;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.entity.impl.powerup.ChangePlayerSpeed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private PlayerImpl player;
    private ChangePlayerSpeed changePlayerSpeed;

    @BeforeEach
    void setUp(){
        player = new PlayerImpl();
        changePlayerSpeed = new ChangePlayerSpeed(1.4);
    }

    @Test
    void testVelocityOnPlayerCreation(){
        assertEquals(1.0, player.getVelocity().getVelocityX());
        assertEquals(1.0, player.getVelocity().getVelocityY());
    }


    @Test
    void testHorizontalAndVerticalVelocityOnPowerUps(){
        changePlayerSpeed.applyEffect(this.player);

        assertEquals(1.0, player.getVelocity().getVelocityY());
        assertEquals(1.4, player.getVelocity().getVelocityX());
    }
}
