package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GameState;

public class GameStateImpl implements GameState {
    private GameStatus status = GameStatus.MENU;

    @Override
    public void changeState(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus(){
        return this.status;
    }
}
