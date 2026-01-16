package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.game.impl.GameStatus;

public interface GameState {

    void changeState(GameStatus status);
}
