package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Lives;

public class LivesImpl implements Lives {
    private int lives;

    public LivesImpl(int lives) {
        setLivesByValue(lives);
    }

    public LivesImpl() {
        setLivesByValue(0);
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public void setLivesByValue(int lives) {
        this.lives = lives;
    }

    public void decrementLives(){
        if(this.lives > 0){
            this.lives--;
        }
    }
}
