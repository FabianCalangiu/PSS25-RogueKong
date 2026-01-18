package it.unibo.roguekong.view.impl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class MenuView {
    private final Scene scene;

    public MenuView(){
        /*
         * From javafx docs: VBox lays out its children in a single vertical column.
         * If the vbox has a border and/or padding set,
         * then the contents will be laid out within those insets.
         * -------------------------------------------------------------------------
         * setAlignment aligns the referenced object in a certain
         * position relative to the scene.
         */
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button start = new Button("Start");
        Button score = new Button("Score");
        Button exit = new Button("Exit");

        root.getChildren().addAll(start, score, exit);
        this.scene = new Scene(root, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
