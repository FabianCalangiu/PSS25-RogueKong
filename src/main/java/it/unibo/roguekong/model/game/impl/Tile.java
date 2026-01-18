package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GamePlatform;

import java.awt.image.BufferedImage;

public class Tile implements GamePlatform {
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    private final BufferedImage image;
    private final boolean isCollidable;
    private final boolean canHit;

    public Tile(BufferedImage image, boolean isCollidable, boolean canHit) {
        this.image = image;
        this.isCollidable = isCollidable;
        this.canHit = canHit;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
