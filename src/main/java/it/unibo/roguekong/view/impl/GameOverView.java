package it.unibo.roguekong.view.impl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverView {
    private final Scene scene;
    private Runnable onResume, onMenu;

    public GameOverView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Game Over");
        title.getStyleClass().add("game-over-title");
        Button menu = new Button("Menu");

        menu.setOnAction(e -> runIfNotNull(onMenu));

        root.getChildren().addAll(title, menu);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnMenu(Runnable r) {
        this.onMenu = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}
