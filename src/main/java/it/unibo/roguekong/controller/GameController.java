package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.*;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.impl.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/**
 * This is the actual gameloop handler.
 * From javafx docs: The class AnimationTimer allows to create a timer, that is called in each frame while it is active.
 * An extending class has to override the method handle(long) which will be called in every frame. The methods start() and stop() allow to start and stop the timer
 */

public class GameController {
    private static final SoundManager JUMP_SOUND = new SoundManager("/assets/sound/jump.wav", -30.0f);

    private AnimationTimer gameLoop;
    private final GameStateImpl gameState;
    Runnable onMenu;
    Runnable onPause;
    Runnable onVictory;
    private GameView gameView;
    private PlayerImpl player;
    private LevelController levelController;
    private ScoreManager scoreManager;

    private int score = 1000;
    private long lastScoreUpdate = 0;

    /**
     * Initializes all the implementations the controller needs in order to update and run each frame
     * @param view renders the main game
     * @param gameState keeps record on game states
     * @param player: position and sprites are updated and loaded each frame
     * @param levelController gets current level and updates levels
     * @param scoreManager keeps record of the player's score
     */
    public GameController(GameView view, GameStateImpl gameState, PlayerImpl player, LevelController levelController, ScoreManager scoreManager) {
        this.gameState = gameState;
        this.gameView = view;
        this.player = player;
        this.levelController = levelController;
        this.scoreManager = scoreManager;
        /*
         * Insert inputs from here (They're outside the main loop because they're EVENT DRIVEN) ->>
         */

        /*
         * Press ESC to open Pause Menu while playing
         */
        view.getRoot().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                if (gameState.getState() == GameStatus.PLAYING) {
                    gameState.pauseGame();
                    pause();
                } else if (gameState.getState() == GameStatus.PAUSED) {
                    gameState.resumeGame();
                    resume();
                }
            }
        });

        /*
         * ->> To here
         */

        /*
         * Everything that is called inside handle()'s body
         * is executed continuously.
         * That does not mean you should change its body, but update's and render's.
         */
        this.gameLoop = new AnimationTimer(){
            @Override
            public void handle(long now){
                userInput();
                update();
                render();
            }
        };
    }

    /**
     * Uses AnimationTimer methods to start or stop the loop.
     * Calls state change.
     */
    public void start(){
        gameState.startGame();
        gameLoop.start();
    }

    /**
     * Parses user input each frames
     */
    private void userInput(){
        /* --------------------------- USER INPUT ----------------------------------*/
        if(gameState.getState() != GameStatus.PLAYING) {
            return;
        } else {

            if(gameView.isKeyPressed(KeyCode.A)) {
                this.player.setPosition(player.getPosition().getX() - (1 * player.getVelocity().getVelocityX()), player.getPosition().getY());
            }

            if(gameView.isKeyPressed(KeyCode.D)) {
                this.player.setPosition(player.getPosition().getX() + (1 * player.getVelocity().getVelocityX()), player.getPosition().getY()); // Must be implemented the velocity variation like gravity.
            }
            /**
             * Must be improved soon
             */
            PositionImpl tileBelow = new PositionImpl(
                    this.player.getPosition().getX() + 16,
                    this.player.getPosition().getY()
            );

            if(gameView.isKeyPressed(KeyCode.W)) {
                this.player.setPosition(player.getPosition().getX(), player.getPosition().getY() - 3);
            }

            /*
             * Must be improved soon
             */
            if(gameView.isKeyPressed(KeyCode.S)) {
                this.player.setPosition(player.getPosition().getX(), player.getPosition().getY() + 1);
            }

            if(gameView.isKeyPressed(KeyCode.SPACE)) {
                this.player.setPosition(player.getPosition().getX() + 0.5, player.getPosition().getY() + 0.5);
                JUMP_SOUND.play();
            }

            if(gameView.isKeyPressed(KeyCode.P)){
                showPowerUpPanel();
            }
        }
        /* -------------------------------------------------------------*/
    }

    /**
     * Update and render are the body of the main game loop. Everything in their body
     * gets updated every 60fps
     */
    private void update(){
        updateScore();
        setGravityEachFrame();

        // Check the player position
        this.levelController.nextLevelIfIsComplete(this.gameView);

        if(this.levelController.hasPlayerWon()) {
            runIfNotNull(this.onVictory);
        }

        System.out.println(this.player.getPosition().getX() + " " + this.player.getPosition().getY());
    }

    private void render(){
        /*
         * Add render here
         */
        gameView.renderPlayer(this.player);
        gameView.renderLives(this.player);
        System.out.println(this.player.getLives().getLives());
    }

    public void stop(){
        gameLoop.stop();
    }

    private void pause(){
        gameState.pauseGame();
        gameLoop.stop();
        if(onPause != null) onPause.run();
    }

    public void resume(){
        gameState.resumeGame();
        gameLoop.start();
    }

    public void goToMenu() {
        gameLoop.stop();
        gameState.goToMenu();
        if (onMenu != null) onMenu.run();
    }

    private void showPowerUpPanel(){
        gameLoop.stop();
        gameState.pauseGame();

        var powerUps = PowerUpController.getRandomPowerUps(2);

        gameView.showPowerUpPanel(
                player,
                powerUps,
                () -> {
                    gameState.resumeGame();
                    gameLoop.start();
                }
        );
    }

    /**
     * Updates score each second (maybe changed)
     */
    private void updateScore(){
        long now = System.nanoTime();

        if(lastScoreUpdate == 0){
            lastScoreUpdate = now;
            return;
        }

        long elapsed = now - lastScoreUpdate;

        if(elapsed >= 1_000_000_000L){
            score = Math.max(0, score - 2);
            lastScoreUpdate = now;
            System.out.println("Score: " + score);
        }
    }

    public void setOnPause(Runnable r) {
        this.onPause = r;
    }

    public void setOnVictory(Runnable r) { this.onVictory = r; }

    public int getScoreManager() { return this.score; }

    public void setGravityEachFrame() {
        this.levelController.getCurrentLevel().setGravityOnPlayer();
    }

    private void runIfNotNull(Runnable r) { r.run(); }
}
