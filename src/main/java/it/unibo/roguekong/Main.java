package it.unibo.roguekong;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.LevelModel;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.impl.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("RogueKong");
        stage.setResizable(false);

        GameStateImpl gameState = new GameStateImpl();

        MenuView menuView = new MenuView();
        ScoreView scoreView = new ScoreView();
        PauseView pauseView = new PauseView();
        GameView gameView = new GameView();
        GameOverView gameOverView = new GameOverView();

        // TileManager tileManager = new TileManager(32, 20, 2);
        LevelModel level = new LevelModel(
                new PositionImpl(960-32, 640-32),
                new PositionImpl(10, 10),
                List.of(),
                new PlayerImpl(),
                new TileManager("maps/map1.txt", "maps/background1.txt"),
                1
        );
        level.init();
        GameController controller = new GameController(gameView, gameState, level.getPlayer());
        gameView.loadMap(level.getTileManager());
        gameView.renderPlayer(level.getPlayer());

        menuView.setOnStart(() -> {
            controller.start();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
        });

        menuView.setOnScore(() -> {
            stage.setScene(scoreView.getScene());
        });

        scoreView.setOnReturn(() -> {
            stage.setScene(menuView.getScene());
        });

        menuView.setOnExit(() -> {
            Platform.exit();
        });

        controller.setOnPause(() -> {
            controller.setOnPause(() -> {
                stage.setScene(pauseView.getScene());
            });
            stage.setScene(pauseView.getScene());
        });

        gameView.setOnKill(() -> {
            stage.setScene(gameOverView.getScene());
        });

        pauseView.setOnResume(() -> {
            controller.resume();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
        });

        pauseView.setOnMenu(() -> {
            controller.goToMenu();
            stage.setScene(menuView.getScene());
            level.init();
        });

        gameOverView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            level.init();
        });

        stage.setScene(menuView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}