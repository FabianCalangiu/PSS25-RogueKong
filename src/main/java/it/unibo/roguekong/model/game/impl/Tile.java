package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GamePlatform;

import java.awt.image.BufferedImage;

public class Tile implements GamePlatform {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    private TileType tileType;
    private final String  image;
    private final boolean isCollidable;
    private final boolean canDealDamage;

    public Tile(String image, boolean isCollidable, boolean canDealDamage, TileType tileType) {
        this.image = image;
        this.isCollidable = isCollidable;
        this.tileType = tileType;
        this.canDealDamage = canDealDamage;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() { return HEIGHT; }

    public String getImage() {
        return image;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public TileType getTileType() {
        return tileType;
    }
}
