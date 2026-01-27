package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.entity.PowerUp;
import it.unibo.roguekong.model.entity.impl.powerup.ChangePlayerSpeed;
import it.unibo.roguekong.model.entity.impl.powerup.DoubleJump;
import it.unibo.roguekong.model.entity.impl.powerup.IncreasePlayerLives;
import it.unibo.roguekong.model.entity.impl.powerup.Invulnerability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PowerUpController {

    private PowerUpController(){};

    /*
     * Returns a random amount of power ups in a list
     */

    public static List<PowerUp> getRandomPowerUps(int amount){
        List<PowerUp> powerUpPool = new ArrayList<>(List.of(
                new ChangePlayerSpeed(1.4),
                new DoubleJump(),
                new Invulnerability(),
                new IncreasePlayerLives()
        ));

        /*
         * Shuffles all values in a random position
         */
        Collections.shuffle(powerUpPool);

        /*
         * Takes the first n elements limited by the amount inputted
         */
        return powerUpPool.stream().limit(amount).toList();
    }
}
