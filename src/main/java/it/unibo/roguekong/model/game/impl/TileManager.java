package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.value.impl.PositionImpl;

public class TileManager {

    Tile[] tileSet; // The length of the array depends on how many kind of tiles we are going to use
    int [][] gameMap; // This represents the matrix map. It will be filled

    public TileManager(int width, int height, int numTiles) {
        this.tileSet = new Tile[numTiles];
        this.gameMap = new int[width][height];
    }

    public void fillTile(){}

    public void fillGameMap(){};

    public Tile[] getTileSet() {
        return tileSet;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    private boolean checkTileOutOfBounds(int col, int row) {
        if(row >= 0 && row < this.gameMap.length &&
                col >= 0 && col < this.gameMap[row].length) {}
    }

    public Tile getTileAtPosition(PositionImpl pos) {
        int col = (int) pos.getX() / tileSet[0].getWidth();
        int row = (int) pos.getY() / tileSet[0].getHeight();


    }
}
