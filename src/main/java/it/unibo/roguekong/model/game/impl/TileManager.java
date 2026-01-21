package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.value.impl.PositionImpl;

public class TileManager {
    private final static int ROWS = 20; //The Y of the map
    private final static int COLS = 32; //The X of the map
    private final static int TILE_SIZE = 32;

    private final Tile[] tileSet;
    private final int [][] gameMap;

    public TileManager() {
        this.gameMap = new int[ROWS][COLS];
        Tile tile0 = new Tile("", false, TileType.VOID);
        Tile tile1 = new Tile("/assets/sprites/Sand.png", true, TileType.PLATFORM);
        Tile tile2 = new Tile("/assets/sprites/Wall.png", true, TileType.PLATFORM);
        Tile tile3 = new Tile("/assets/sprites/Dirt.png", true, TileType.PLATFORM);
        Tile tile4 = new Tile("/assets/sprites/Water.png", false, TileType.VOID);
        this.tileSet = new Tile[] { tile0, tile1, tile2, tile3, tile4 };
        this.fillGameMap();
    }

    /**
     * This method fill the matrix, soon it will be passed a file.txt with inside the map
     */
    public void fillGameMap(){
        // must be complete in the future, when it will be implemented the file reader map
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(i == ROWS - 1){
                    gameMap[i][j] = 4;
                } else{
                    gameMap[i][j] = 1;
                }
            }
        }
    };

    public Tile[] getTileSet() {
        return tileSet;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    /**
     * Get the tile by the player's x and y
     * @param pos Position player. Usefully to check collisions
     * @return Must return the kind of tile is the player on
     */
    public Tile getTileAtPosition(PositionImpl pos) {
        int row = (int) pos.getY() / TILE_SIZE;
        int col = (int) pos.getX() / TILE_SIZE;

        /*
         * Check if the positions are in or out of bounds. If it's outside, return the tiletype void
         */
        if(row < 0 || row >= ROWS
                || col < 0 || col >= COLS){ return this.tileSet[0]; }

        int index = gameMap[row][col];
        return tileSet[index];
    }
}
