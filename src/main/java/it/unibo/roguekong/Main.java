package it.unibo.roguekong;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.controller.LevelController;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.LevelBuilderImpl;
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

//        TileManager tileManager = new TileManager(32, 20, 2);
//        LevelModel level = new LevelModel(
//                new PositionImpl(960-32, 640-32),
//                new PositionImpl(10, 10),
//                List.of(),
//                new PlayerImpl(),
//                new TileManager("maps/map1.txt", "maps/background1.txt"),
//                1
//        );
        LevelModel level = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(960-32, 640-32))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(new PlayerImpl())
                .setTileManager(new TileManager("sampleMap.txt", "maps/background1.txt"))
                .setGravity(1)
                .build();

        LevelModel level2 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(960-32, 640-32))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(new PlayerImpl())
                .setTileManager(new TileManager("maps/map2.txt", "maps/background1.txt"))
                .setGravity(1)
                .build();

        /**
         * Creation of the LevelController, which contains LevelModel implementation for each levels
         */
        List<LevelModel> levels = List.of(level, level2);
        LevelController levelController = new LevelController(levels);

        /**
         * Set up the first level
         */
        levelController.setUpLevel();

        GameController controller = new GameController(gameView, gameState, levelController.getCurrentLevel().getPlayer());
        gameView.loadMap(levelController.getCurrentLevel().getTileManager());
        gameView.renderPlayer(levelController.getCurrentLevel().getPlayer());

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
            levelController.reset();
        });

        gameOverView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            levelController.reset();
        });

        stage.setScene(menuView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}