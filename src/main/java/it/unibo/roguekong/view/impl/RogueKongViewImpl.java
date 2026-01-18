package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RogueKongViewImpl implements RogueKongView {
    /*
     * 1. This implementation defines a container (Pane) and a Scene where
     * all its contents are rendered
     * 2. The scene renders said root Pane and takes as input widthxheight
     * n pixels
     */
    private final Pane root;
    private final Scene scene;

    public RogueKongViewImpl(){
        this.root = new Pane();
        this.scene = new Scene(root, 800, 600);
    }

    @Override
    public Scene getScene(){
        return scene;
    }

    public Pane getRoot(){
        return root;
    }

    /*
     * ------------ Sample methods --------------
     */

    public void addTestImage(){
        Image tileset = new Image(
                getClass().getResourceAsStream("/assets/sprites/world_tileset.png")
        );

        ImageView tile = new ImageView(tileset);

        int TILE_SIZE = 16;
        int col = 0;
        int row = 0;

        tile.setViewport(new Rectangle2D(
                col * TILE_SIZE,
                row * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE
        ));

        System.out.println(
                "Image size: " + tileset.getWidth() + " x " + tileset.getHeight()
        );

        tile.setFitWidth(TILE_SIZE);
        tile.setFitHeight(TILE_SIZE);
        tile.setX(50);
        tile.setY(50);

        root.getChildren().add(tile);
    }
}
