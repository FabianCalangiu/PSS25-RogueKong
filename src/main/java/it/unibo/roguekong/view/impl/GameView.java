package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.Tile;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.view.RogueKongView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final Pane background;
    private final Pane ui;
    private final Pane map;
    private final Pane playerRender;
    private final Scene scene;
    private Runnable onKill;

    public GameView(){
        this.root = new Pane();

        this.map = new Pane();
        this.background = new Pane();
        this.ui = new Pane();
        this.playerRender = new Pane();
        /*
         * setFocusTraversable makes the user input readable
         */

        Button sampleKill = new Button("Kill");
        sampleKill.setOnAction(e -> runIfNotNull(onKill));

        root.getChildren().addAll(sampleKill, background, map, playerRender, ui);
        ui.getChildren().addAll(sampleKill);
        this.root.setFocusTraversable(true);
        this.scene = new Scene(root, WIDTH, HEIGTH);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
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

        map.getChildren().clear();

        for(int i = 0; i < tileManager.getRows(); i++){
            for(int j = 0; j < tileManager.getCols(); j++){
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

                map.getChildren().add(tileView);
            }
        }
    }

    public void loadBackground(TileManager tileManager){
        int[][] mapMatrix = tileManager.getBackgroundMap();
        Tile[] tileSet = tileManager.getTileSet();

        background.getChildren().clear();

        for(int i = 0; i < tileManager.getRows(); i++){
            for(int j = 0; j < tileManager.getCols(); j++){
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

                background.getChildren().add(tileView);
            }
        }
    }

    public void renderPlayer(PlayerImpl player){
        Image playerSprite = new Image(
                getClass().getResourceAsStream(player.getSprite())
        );

        ImageView playerSpriteView = new ImageView(playerSprite);

        playerSpriteView.setFitWidth(TILE_SIZE);
        playerSpriteView.setFitHeight(TILE_SIZE);

        playerSpriteView.setX(player.getPosition().getX());
        playerSpriteView.setY(player.getPosition().getY());

        playerRender.getChildren().add(playerSpriteView);
    }

    public void setOnKill(Runnable r){
        this.onKill = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
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
