package it.unibo.roguekong.model.game.impl;

public class TileManager {

    Tile[] tileSet; // The length of the array depends on how many kind of tiles we are going to use
    int [][] gameMap; // This represents the matrix map. It will be filled

    public TileManager(){

    }

    public void fillTile(){}

    public void fillGameMap(){};

    public Tile[] getTileSet() {
        return tileSet;
    }

    public int[][] getGameMap() {
        return gameMap;
    }
}
