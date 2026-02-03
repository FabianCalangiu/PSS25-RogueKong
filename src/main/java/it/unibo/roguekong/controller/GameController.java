package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.GameStatus;
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
    private static final SoundManager HURT_SOUND = new SoundManager("/assets/sound/Hit1.wav", -30.0f);

    private AnimationTimer gameLoop;
    private final GameStateImpl gameState;
    Runnable onMenu;
    Runnable onPause;
    Runnable onDeath;
    Runnable onVictory;
    private GameView gameView;
    private PlayerImpl player;
    private LevelController levelController;
    private ScoreManager scoreManager;

    private int score = 1000;
    private long lastScoreUpdate = 0;
    private boolean jumpPressed = false;

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
            if (this.gameState.getState() != GameStatus.PLAYING) {
                return;
            }

            switch (e.getCode()) {

                case ESCAPE -> {
                    if (this.gameState.getState() == GameStatus.PLAYING) {
                        pause();
                    } else if (this.gameState.getState() == GameStatus.PAUSED) {
                        resume();
                    }
                }

                case SPACE -> {
                    if (!this.jumpPressed) {
                        this.player.jump();
                        JUMP_SOUND.play();
                        this.jumpPressed = true;
                    }
                }
            }
        });

        view.getRoot().setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                this.jumpPressed = false;
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
        this.gameState.startGame();
        this.gameLoop.start();
    }

    /**
     * Parses user input each frames
     */
    private void userInput(){
        /* --------------------------- USER INPUT ----------------------------------*/
        if(this.gameState.getState() != GameStatus.PLAYING) {
            return;
        } else {

            if(gameView.isKeyPressed(KeyCode.A)) {
                this.player.setPosition(this.player.getPosition().getX() - (1 * this.player.getVelocity().getVelocityX()), this.player.getPosition().getY());
                this.player.setSprite("/assets/sprites/standing-mario-left.png");
            }

            if(gameView.isKeyPressed(KeyCode.D)) {
                this.player.setPosition(this.player.getPosition().getX() + (1 * this.player.getVelocity().getVelocityX()), this.player.getPosition().getY());
                this.player.setSprite("/assets/sprites/standing-mario-right.png");
            }

            if(gameView.isKeyPressed(KeyCode.W) && this.player.collidesWithLadder(this.player.getPosition().getX(), this.player.getPosition().getY())) {
                this.player.setPosition(this.player.getPosition().getX(), this.player.getPosition().getY() - 3);
            }

            if(gameView.isKeyPressed(KeyCode.S)) {
                this.player.setPosition(this.player.getPosition().getX(), this.player.getPosition().getY() + 1);
            }

            if(this.gameView.isKeyPressed(KeyCode.P)){
                showPowerUpPanel();
            }
        }
        /* -------------------------------------------------------------*/
    }

    /**
     * Update and render are the body of the main game loop. Everything in their body
     * gets updated every 60fps
     */
    private void update() {
        this.updateScore();

        this.player.setGravityOnPlayer();

        this.levelController.nextLevelIfIsComplete(this.gameView);

        this.checkIfPlayerGotHit();

        this.checkLose();

        this.checkWin();
    }

    private void render(){
        /*
         * Add render here
         */
        this.gameView.renderPlayer(this.player);
        this.gameView.renderLives(this.player);
        System.out.println(this.player.getLives().getLives());
    }

    public void stop(){
        this.gameLoop.stop();
    }

    private void pause(){
        this.gameState.pauseGame();
        this.gameLoop.stop();
        if(this.onPause != null) this.onPause.run();
    }

    public void resume(){
        this.gameState.resumeGame();
        this.gameLoop.start();
    }

    public void goToMenu() {
        this.gameLoop.stop();
        this.gameState.goToMenu();
        if (this.onMenu != null) this.onMenu.run();
    }

    public void setOnPause(Runnable r) {
        this.onPause = r;
    }

    public void setOnDeath(Runnable r) {
        this.onDeath = r;
    }

    public void setOnVictory(Runnable r) { this.onVictory = r; }

    public int getScoreManager() { return this.score; }

    private void runIfNotNull(Runnable r) { if(r != null) r.run(); }

    private void showPowerUpPanel(){
        this.gameLoop.stop();
        this.gameState.pauseGame();

        var powerUps = PowerUpController.getRandomPowerUps(2);

        this.gameView.showPowerUpPanel(
                this.player,
                powerUps,
                () -> {
                    this.gameState.resumeGame();
                    this.gameLoop.start();
                }
        );
    }

    /**
     * Updates score each second (maybe changed)
     */
    private void updateScore(){
        long now = System.nanoTime();

        if(this.lastScoreUpdate == 0){
            this.lastScoreUpdate = now;
            return;
        }

        long elapsed = now - this.lastScoreUpdate;

        if(elapsed >= 1_000_000_000L){
            this.score = Math.max(0, this.score - 2);
            this.lastScoreUpdate = now;
            System.out.println("Score: " + this.score);
        }
    }

    private void checkWin() {
        if(this.levelController.hasPlayerWon()) {
            this.gameView.clearKeyPressed();
            this.player.getVelocity().resetVelocity();
            this.gameState.goToMenu();
            runIfNotNull(this.onVictory);
        }
    }

    private void checkLose() {
        if(this.player.getLives().getLives() == 0){
            this.gameView.clearKeyPressed();
            this.player.getVelocity().resetVelocity();
            this.gameState.gameOver();
            runIfNotNull(this.onDeath);
        }
    }

    private void checkIfPlayerGotHit() {
        if(this.player.isPlayerHit(this.player.getPosition().getX(), this.player.getPosition().getY())) {
            this.player.getLives().decrementLives();
            this.player.setSprite("/assets/sprites/standing-mario-right.png");
            HURT_SOUND.play();
            this.player.setPosition(
                    this.levelController.getCurrentLevel().getSpawnPoint().getX(),
                    this.levelController.getCurrentLevel().getSpawnPoint().getY()
            );
        }
    }
}
