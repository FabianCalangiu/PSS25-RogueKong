package it.unibo.roguekong.model.entity.impl;

import it.unibo.roguekong.model.entity.GamePlatform;
import it.unibo.roguekong.model.game.impl.GameStatus;
import it.unibo.roguekong.model.value.Position;

import java.awt.image.BufferedImage;

public class Tile implements GamePlatform {
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    private Position position;
    BufferedImage image;
    boolean isCollidable;
    boolean canHit;

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
