package it.unibo.roguekong.view.impl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ScoreView {
    private final Scene scene;
    private Runnable onReturn;

    public ScoreView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);



        Button returnButton = new Button("Return");

        returnButton.setOnAction(e -> runIfNotNull(onReturn));

        root.getChildren().addAll(box1, box2, box3, returnButton);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnReturn(Runnable r) {
        this.onReturn = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}