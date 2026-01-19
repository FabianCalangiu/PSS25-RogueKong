package it.unibo.roguekong;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.view.impl.GameView;
import it.unibo.roguekong.view.impl.MenuView;
import it.unibo.roguekong.view.impl.PauseView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        GameStateImpl gameState = new GameStateImpl();

        MenuView menuView = new MenuView();
        PauseView pauseView = new PauseView();
        GameView gameView = new GameView();

        GameController controller = new GameController(gameView, gameState);

        TileManager tileManager = new TileManager(32, 20, 2);
        gameView.loadMap(tileManager);

        menuView.setOnStart(() -> {
            controller.start();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
        });

        controller.setOnPause(() -> {
            controller.setOnPause(() -> {
                stage.setScene(pauseView.getScene());
            });
            stage.setScene(pauseView.getScene());
        });

        pauseView.setOnResume(() -> {
            controller.resume();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
        });

        pauseView.setOnMenu(() -> {
            controller.goToMenu();
            stage.setScene(menuView.getScene());
        });

        stage.setScene(menuView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}