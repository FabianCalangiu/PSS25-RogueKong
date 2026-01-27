package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.Tile;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.view.RogueKongView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashSet;
import java.util.Set;

public class GameView implements RogueKongView {
    /*
     * 1. This implementation defines several layers (Panes) and a Scene where
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
    private Pane powerUpLayer;
    private VBox powerUpBox;
    private final Scene scene;

    private Runnable onKill;
    private final Set<KeyCode> keysPressed = new HashSet<>();

    private ImageView playerSpriteView;

    public GameView(){
        this.root = new Pane();
        this.map = new Pane();
        this.background = new Pane();
        this.ui = new Pane();
        this.playerRender = new Pane();

        Button sampleKill = new Button("Kill");
        sampleKill.setOnAction(e -> runIfNotNull(onKill));

        root.getChildren().addAll(sampleKill, background, map, playerRender, ui);
        ui.getChildren().addAll(sampleKill);
        /*
         * setFocusTraversable makes the user input readable
         */
        this.root.setFocusTraversable(true);

        createPowerUpLayer();

        this.scene = new Scene(root, WIDTH, HEIGTH);

        /*
         * Events needed for user keys input.
         * All the registered input are inserted into a Queue (the hashset declared above) and removed when released.
         */
        this.scene.setOnKeyPressed(e -> keysPressed.add(e.getCode()));
        this.scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));

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

    /*
     * The loaders beneath load both the map and the background.
     * Background and map are loaded on different layers, but are both Tile types
     */
    public void loadMap(TileManager tileManager){
        int[][] mapMatrix = tileManager.getGameMap();
        int[][] backgroundMatrix = tileManager.getBackgroundMap();

        Tile[] tileSet = tileManager.getTileSet();

        map.getChildren().clear();
        background.getChildren().clear();

        for(int i = 0; i < tileManager.getRows(); i++){
            for(int j = 0; j < tileManager.getCols(); j++){
                int mapTileIndex = mapMatrix[i][j];
                int backgroundTileIndex = backgroundMatrix[i][j];

                Tile mapTile = tileSet[mapTileIndex];
                Tile backgroundTile = tileSet[backgroundTileIndex];


                Image mapTileImage = new Image(
                        getClass().getResourceAsStream(mapTile.getImage())
                );

                Image backgroundTileImage = new Image(
                        getClass().getResourceAsStream(backgroundTile.getImage())
                );

                ImageView mapTileView = new ImageView(mapTileImage);
                ImageView backgroundTileView = new ImageView(backgroundTileImage);

                mapTileView.setFitWidth(TILE_SIZE);
                mapTileView.setFitHeight(TILE_SIZE);
                backgroundTileView.setFitWidth(TILE_SIZE);
                backgroundTileView.setFitHeight(TILE_SIZE);

                mapTileView.setX(j * TILE_SIZE);
                mapTileView.setY(i * TILE_SIZE);
                backgroundTileView.setX(j * TILE_SIZE);
                backgroundTileView.setY(i * TILE_SIZE);

                background.getChildren().add(backgroundTileView);
                map.getChildren().add(mapTileView);
            }
        }
    }

    public void renderPlayer(PlayerImpl player){
        if (playerSpriteView == null) {
            Image playerSprite = new Image(
                    getClass().getResourceAsStream(player.getSprite())
            );

            playerSpriteView = new ImageView(playerSprite);
            playerSpriteView.setFitWidth(TILE_SIZE);
            playerSpriteView.setFitHeight(TILE_SIZE);

            playerRender.getChildren().add(playerSpriteView);
        }

        playerSpriteView.setX(player.getPosition().getX());
        playerSpriteView.setY(player.getPosition().getY());
    }


    /*
     * Creates the powerup Overlay where the player can choose the random
     * powerups the game picks.
     */
    private void createPowerUpLayer(){
        powerUpLayer = new Pane();
        powerUpLayer.setPrefSize(WIDTH, HEIGTH);

        /*
         * Inline CSS style
         */
        powerUpLayer.setStyle("""
                -fx-background-color: rbga(0, 0, 0, 0.6);
                """);

        powerUpBox = new VBox(15);
        powerUpBox.setStyle("""
                -fx-padding: 20;
                """);

        /*
         * Centers the box
         */
        powerUpBox.setLayoutX(WIDTH / 2.0 - 150);
        powerUpBox.setLayoutY(HEIGTH / 2.0 - 120);

        powerUpLayer.getChildren().add(powerUpBox);
        powerUpLayer.setVisible(false);

        ui.getChildren().add(powerUpLayer);
    }

    /*
     * Returns true if key is in the input queue
     */
    public boolean isKeyPressed(KeyCode key){
        return keysPressed.contains(key);
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
