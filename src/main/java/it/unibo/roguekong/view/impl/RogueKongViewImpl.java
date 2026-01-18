package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.view.RogueKongView;
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

    @Override
    public Pane getRoot(){
        return root;
    }

    /*
     * ------------ Sample methods --------------
     */

    public void addTestImage(){
        Image img = new Image(
                getClass().getResourceAsStream("/images/sample1.jpg")
        );

        ImageView iv = new ImageView(img);
        iv.setX(100);
        iv.setY(100);
        iv.setFitWidth(64);
        iv.setFitHeight(64);

        root.getChildren().add(iv);
    }
}
