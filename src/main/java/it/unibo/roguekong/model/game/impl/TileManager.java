package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.value.impl.PositionImpl;

public class TileManager {
    int size = 32; // The size of each pixel
    Tile[] tileSet; // The length of the array depends on how many kind of tiles we are going to use
    int [][] gameMap; // This represents the matrix map. It will be filled

    public TileManager(int width, int height, int numTiles) {
        this.gameMap = new int[height][width];

        Tile tile1 = new Tile("/assets/sprites/Sand.png", true, TileType.PLATFORM);
        Tile tile2 = new Tile("/assets/sprites/Wall.png", true, TileType.LADDER);
        this.tileSet = new Tile[] { tile1, tile2 };
        this.fillGameMap();
    }

    /**
     * This method fill the matrix, soon it will be passed a file.txt with inside the map
     */
    public void fillGameMap(){
        // must be complete in the future, when it will be implemented the file reader map
        for(int i = 0; i < this.gameMap.length; i++){
            for(int j = 0; j < this.gameMap[0].length; j++){
                if(i == gameMap.length - 1){
                    gameMap[i][j] = 1;
                } else{
                    gameMap[i][j] = 0;
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
        int col = (int) pos.getX() / this.size;
        int row = (int) pos.getY() / this.size;

        int index = gameMap[row][col];
        return tileSet[index];
    }
}
