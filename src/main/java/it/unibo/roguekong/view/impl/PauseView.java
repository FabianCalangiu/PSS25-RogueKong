package it.unibo.roguekong.view.impl;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PauseView {
    private final Scene scene;
    private Runnable onResume, onMenu;

    public PauseView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button resume = new Button("Resume");
        Button menu = new Button("Menu");

        resume.setOnAction(e -> runIfNotNull(onResume));
        menu.setOnAction(e -> runIfNotNull(onMenu));

        root.getChildren().addAll(resume, menu);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnResume(Runnable r) {
        this.onResume = r;
    }

    public void setOnMenu(Runnable r) {
        this.onMenu = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}