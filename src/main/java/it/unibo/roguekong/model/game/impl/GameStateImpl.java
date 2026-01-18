package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GameState;

public class GameStateImpl implements GameState {
    private GameStatus status = GameStatus.MENU;

    public void startGame(){
        this.status = GameStatus.PLAYING;
    }

    public void pauseGame(){
        if(this.status == GameStatus.PLAYING){
            this.status = GameStatus.PAUSED;
        }
        System.out.println(status);
    }

    public void resumeGame(){
        if(this.status == GameStatus.PAUSED){
            this.status = GameStatus.PLAYING;
        }
    }

    public void gameOver(){
        if(this.status == GameStatus.PLAYING){
            this.status = GameStatus.GAME_OVER;
        }
    }

    public void goToMenu(){
        this.status = GameStatus.MENU;
    }

    @Override
    public GameStatus getState(){
        return this.status;
    }
}
