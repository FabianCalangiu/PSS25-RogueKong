package it.unibo.roguekong.model.entity;

import it.unibo.roguekong.model.value.Position;

public interface GamePlatform {
    public Position getPosition();

    public int getWidth();

    public int getHeight();
}
