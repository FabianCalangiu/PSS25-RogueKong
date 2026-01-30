package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.GamePlatform;

/**
 * This class represents the single tile implementation
 */
public class Tile implements GamePlatform {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    private TileType tileType;
    private final String  image;
    private boolean isCollidable;
    private final boolean canDealDamage;
    private HitboxImpl hitbox;

    /**
     * Create a new tile
     * @param image is the sprite path
     * @param isCollidable says if the tile is a collidable object
     * @param canDealDamage says if the tile can deal damage
     * @param tileType says what kind of tile is
     */
    public Tile(String image, boolean isCollidable, boolean canDealDamage, TileType tileType) {
        this.image = image;
        setIsCollidable(isCollidable);
        this.tileType = tileType;
        this.canDealDamage = canDealDamage;
    }

    public HitboxImpl getHitbox() {
        return hitbox;
    }

    @Override
    public int getWidth() { return WIDTH; }

    @Override
    public int getHeight() { return HEIGHT; }

    public String getImage() { return image; }

    public boolean isCollidable() { return isCollidable; }

    public boolean isCanDealDamage() { return canDealDamage; }

    public TileType getTileType() { return tileType; }
    private void setIsCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
        this.hitbox = isCollidable ? new HitboxImpl() : null;
    }

    public TileType getTileType() {
        return tileType;
    }
}
