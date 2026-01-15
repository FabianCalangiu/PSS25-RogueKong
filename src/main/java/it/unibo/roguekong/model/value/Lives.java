package it.unibo.roguekong.model.value;

public interface Lives {
    int getValue();

    void decrementLives();
    void incrementLives();
    void setLivesByValue();
}
