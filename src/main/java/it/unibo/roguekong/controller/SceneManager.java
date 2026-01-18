package it.unibo.roguekong.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * The Scene Manager simply changes what is viewed based on
 * the game state
 */

public class SceneManager {
    private final Stage stage;

    public SceneManager(Stage stage){
        this.stage = stage;
    }

    public void showMenu(Scene menuScene){
        stage.setScene(menuScene);
    }

    public void showGame(Scene gameScene){
        stage.setScene(gameScene);
    }
}
