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

        Pane box1 = new Pane(); box1.getStyleClass().add("box1");
        Pane box2 = new Pane(); box2.getStyleClass().add("box2");
        Pane box3 = new Pane(); box3.getStyleClass().add("box3");

        box1.setMinSize(150, 50);
        box1.setMaxSize(150, 50);

        box2.setMinSize(150, 50);
        box2.setMaxSize(150, 50);

        box3.setMinSize(150, 50);
        box3.setMaxSize(150, 50);

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