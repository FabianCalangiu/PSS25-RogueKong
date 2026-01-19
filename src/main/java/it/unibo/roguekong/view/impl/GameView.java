package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.game.impl.Tile;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameView implements RogueKongView {
    /*
     * 1. This implementation defines a container (Pane) and a Scene where
     * all its contents are rendered
     * 2. The scene renders said root Pane and takes as input widthxheight
     * n pixels
     */
    private final static int WIDTH = 960;
    private final static int HEIGTH = 640;
    private final static int TILE_SIZE = 32;

    private final Pane root;
    private final Scene scene;

    public GameView(){
        this.root = new Pane();
        /*
         * setFocusTraversable makes the user input readable
         */
        this.root.setFocusTraversable(true);
        this.scene = new Scene(root, WIDTH, HEIGTH);
    }

    @Override
    public Scene getScene(){
        return scene;
    }

    public Pane getRoot(){
        return root;
    }

    public void loadMap(TileManager tileManager){
        int[][] mapMatrix = tileManager.getGameMap();
        Tile[] tileSet = tileManager.getTileSet();

        root.getChildren().clear();

        for(int i = 0; i < mapMatrix.length; i++){
            for(int j = 0; j < mapMatrix[i].length; j++){
                int tileIndex = mapMatrix[i][j];
                Tile tile = tileSet[tileIndex];

                Image image = new Image(
                        getClass().getResourceAsStream(tile.getImage())
                );

                ImageView tileView = new ImageView(image);

                tileView.setFitWidth(TILE_SIZE);
                tileView.setFitHeight(TILE_SIZE);

                tileView.setX(j * TILE_SIZE);
                tileView.setY(i * TILE_SIZE);

                root.getChildren().add(tileView);
            }
        }
    }

    /*
     * ------------ Sample methods --------------
     */

//    public void addTestImage(){
//        Image tileset = new Image(
//                getClass().getResourceAsStream("/assets/sprites/world_tileset.png")
//        );
//
//        ImageView tile = new ImageView(tileset);
//
//        int TILE_SIZE = 16;
//        int col = 0;
//        int row = 0;
//
//        tile.setViewport(new Rectangle2D(
//                col * TILE_SIZE,
//                row * TILE_SIZE,
//                TILE_SIZE,
//                TILE_SIZE
//        ));
//
//        System.out.println(
//                "Image size: " + tileset.getWidth() + " x " + tileset.getHeight()
//        );
//
//        tile.setFitWidth(TILE_SIZE);
//        tile.setFitHeight(TILE_SIZE);
//        tile.setX(50);
//        tile.setY(50);
//
//        root.getChildren().add(tile);
//    }
}
