package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.paths.Assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private final static int ROWS = 20; //The Y of the map
    private final static int COLS = 30; //The X of the map
    private final static int TILE_SIZE = 32;

    private Tile[] tileSet;
    private Tile[] tileBackGround;
    private final int [][] gameMap;
    private final int [][] backgroundMap;

    public TileManager(String mapPath, String backgroundPath) {
        this.gameMap = new int[ROWS][COLS];
        this.backgroundMap = new int[ROWS][COLS];

        this.fillTileSet();
        this.fillGameBackground(backgroundPath);
        this.fillGameMap(mapPath);
    }

    public Tile[] getTileSet() {
        return tileSet;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    public int[][] getBackgroundMap() {
        return backgroundMap;
    }

    public int getRows() { return ROWS; }

    public int getCols() { return COLS; }

    /**
     * Fill the tileSet array with the tile implementations
     */
    private void fillTileSet() {
        Tile tile0 = new Tile("", false, TileType.VOID);
        Tile tile1 = new Tile(Assets.BRICK_WALL, true, TileType.PLATFORM);
        Tile tile2 = new Tile(Assets.GRASSY_SOIL, true, TileType.PLATFORM);
        Tile tile3 = new Tile(Assets.SOIL, true, TileType.PLATFORM);
        Tile tile4 = new Tile(Assets.WATER, true, TileType.VOID);
        Tile tile5 = new Tile(Assets.WATER_RIPPLE, true, TileType.VOID);
        Tile tile6 = new Tile(Assets.LADDER, false, TileType.LADDER);
        Tile tile7 = new Tile(Assets.TOP_TREE_BUSH, false, TileType.TREE);
        Tile tile8 = new Tile(Assets.MIDDLE_TREE_BUSH, false, TileType.TREE);
        Tile tile9 = new Tile(Assets.TREE_LOG, false, TileType.TREE);


        Tile tile10 = new Tile(Assets.DARK_CLOUD, false, TileType.VOID);
        Tile tile11 = new Tile(Assets.DARK_FOGGY_SKY, false, TileType.VOID);
        Tile tile12 = new Tile(Assets.DARK_SKY, false, TileType.VOID);

        this.tileSet = new Tile[] { tile0, tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12 };
    }

    /**
     * This method fill the matrix
     * @param mapPath is the mapPath file
     */
    private void fillGameMap(String mapPath){
        try(final InputStream mapFile = ClassLoader.getSystemResourceAsStream(mapPath)) {
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapFile));

            int row = 0;
            int col = 0;

            while (row < ROWS && col < COLS) {
                String line = mapReader.readLine();

                while(col < COLS) {
                    String values[] = line.split(" ");
                    int n = Integer.parseInt(values[col]);
                    this.gameMap[row][col] = n;
                    col++;
                }
                if(col == COLS) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method fill the matrix
     * @param backgroundPath is the background map file
     */
    private void fillGameBackground(String backgroundPath){
        try(final InputStream backgroundFile = ClassLoader.getSystemResourceAsStream(backgroundPath)) {
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(backgroundFile));

            int row = 0;
            int col = 0;

            while (row < ROWS && col < COLS) {
                String line = mapReader.readLine();

                while(col < COLS) {
                    String values[] = line.split(" ");
                    int n = Integer.parseInt(values[col]);
                    this.backgroundMap[row][col] = n;
                    col++;
                }
                if(col == COLS) {
                    col = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the tile by the player's x and y
     * @param pos Position player. Usefully to check collisions
     * @return Must return the kind of tile is the player on
     */
    public Tile getTileAtPosition(PositionImpl pos) {
        int row = (int) pos.getY() / TILE_SIZE;
        int col = (int) pos.getX() / TILE_SIZE;

        if(row < 0 || row >= ROWS
                || col < 0 || col >= COLS){ return this.tileSet[0]; }

        int index = gameMap[row][col];
        return tileSet[index];
    }
}
