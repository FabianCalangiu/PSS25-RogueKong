package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.GameStateImpl;
import it.unibo.roguekong.model.game.impl.GameStatus;
import it.unibo.roguekong.view.impl.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/*
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
    private GameView gameView;
    private PlayerImpl player;

    public GameController(GameView view, GameStateImpl gameState, PlayerImpl player){
        this.gameState = gameState;
        this.gameView = view;
        this.player = player;
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
                update();
                render();
            }
        };
    }

    /*
     * Uses AnimationTimer methods to start or stop the loop.
     * Calls state change.
     */
    public void start(){
        gameState.startGame();
        gameLoop.start();
    }

    /*
     * Update and render are the body of the main game loop. Everything in their body
     * gets updated every 60fps
     */
    private void update(){
        if(gameState.getState() != GameStatus.PLAYING) {
            return;
        } else {

            if(gameView.isKeyPressed(KeyCode.A) && this.player.getPosition().getX() > 0) {
                this.player.setPosition(player.getPosition().getX() - (1 * player.getVelocity().getVelocityX()), player.getPosition().getY());
            }

            if(gameView.isKeyPressed(KeyCode.D) && this.player.getPosition().getX() + 32 < 960) {
                this.player.setPosition(player.getPosition().getX() + (1 * player.getVelocity().getVelocityX()), player.getPosition().getY()); // Must be implemented the velocity variation like gravity.
            }

            if(gameView.isKeyPressed(KeyCode.W)) {
                this.player.setPosition(player.getPosition().getX(), player.getPosition().getY() - 1); // It will be deleted soon. Keep It to try the player movement around the map
            }

            if(gameView.isKeyPressed(KeyCode.S)) {
                this.player.setPosition(player.getPosition().getX(), player.getPosition().getY() + 1); // It will be deleted soon. Keep It to try the player movement around the map
            }

            if(gameView.isKeyPressed(KeyCode.SPACE)) {
                this.player.setPosition(player.getPosition().getX() + 0.5, player.getPosition().getY() + 0.5);
                JUMP_SOUND.play();
            }

            if(gameView.isKeyPressed(KeyCode.P)){
                showPowerUpPanel();
            }

        }
    }

    private void render(){
        /*
         * Add render here
         */
        gameView.renderPlayer(this.player);
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

    public void setOnPause(Runnable r) {
        this.onPause = r;
    }

    public void setOnMenu(Runnable r) {
        this.onMenu = r;
    }
}
