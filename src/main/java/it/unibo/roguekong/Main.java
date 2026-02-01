package it.unibo.roguekong;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.controller.LevelController;
import it.unibo.roguekong.controller.ScoreManager;
import it.unibo.roguekong.controller.SoundManager;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.*;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.impl.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static final SoundManager BACKGROUND_MUSIC = new SoundManager("/assets/sound/musicBackground.wav", -20.0f);

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
        ScoreManager scoreManager = new ScoreManager();

        scoreView.setScores(scoreManager.loadTopScores(3));

        LevelModel level = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(80, 200))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(new PlayerImpl())
                .setTileManager(new TileManager("maps/map2.txt", "maps/background1.txt"))
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
        levelController.getCurrentLevel().getPlayer().setTileManager(levelController.getCurrentLevel().getTileManager());
        /**
         * Set up the first level
         */
        levelController.setUpLevel();

        GameController controller = new GameController(gameView, gameState, levelController.getCurrentLevel().getPlayer(), levelController, scoreManager);
        gameView.loadMap(levelController.getCurrentLevel().getTileManager());
        gameView.renderPlayer(levelController.getCurrentLevel().getPlayer());

        /* ------------ MENU BUTTONS -------------*/
        menuView.setOnStart(() -> {
            controller.start();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
            BACKGROUND_MUSIC.loop();
        });

        menuView.setOnScore(() -> {
            scoreView.setScores(scoreManager.loadTopScores(3));
            stage.setScene(scoreView.getScene());
        });

        menuView.setOnExit(() -> {
            Platform.exit();
        });
        /* ---------------------------------------*/

        /* ------------ SCORE BUTTONS -------------*/
        scoreView.setOnReturn(() -> {
            stage.setScene(menuView.getScene());
        });

        scoreView.setOnClearScores(() -> {
            scoreManager.clearScores();
            scoreView.setScores(scoreManager.loadTopScores(3));
        });
        /* ---------------------------------------*/

        /* ------------ CONTROLLER BUTTONS -------------*/
        controller.setOnPause(() -> {
            controller.setOnPause(() -> {
                stage.setScene(pauseView.getScene());
                BACKGROUND_MUSIC.stop();
            });
            stage.setScene(pauseView.getScene());
            BACKGROUND_MUSIC.stop();
        });
        /* ---------------------------------------*/

        /* ------------ GAME BUTTONS -------------*/
        gameView.setOnKill(() -> {
            stage.setScene(gameOverView.getScene());
            controller.stop();
            BACKGROUND_MUSIC.stop();
        });
        /* ---------------------------------------*/

        /* ------------ PAUSE BUTTONS -------------*/
        pauseView.setOnResume(() -> {
            controller.resume();
            stage.setScene(gameView.getScene());
            gameView.getRoot().requestFocus();
            BACKGROUND_MUSIC.restart();
        });

        pauseView.setOnMenu(() -> {
            controller.goToMenu();
            stage.setScene(menuView.getScene());
            levelController.reset();
            BACKGROUND_MUSIC.stop();
        });
        /* ---------------------------------------*/

        /* ------------ GAME OVER BUTTONS -------------*/
        gameOverView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            levelController.reset();
            BACKGROUND_MUSIC.stop();
            scoreManager.saveScore(new ScoreRecord("Player", controller.getScoreManager()));
        });
        /* -----------------------------------------------*/

        stage.setScene(menuView.getScene());
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}