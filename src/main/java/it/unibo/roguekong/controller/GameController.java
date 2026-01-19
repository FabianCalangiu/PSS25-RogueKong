package it.unibo.roguekong.controller;

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
    private AnimationTimer gameLoop;
    private final GameStateImpl gameState;
    Runnable onMenu;
    Runnable onPause;

    public GameController(GameView view, GameStateImpl gameState){
        this.gameState = gameState;

        /*
         * Insert inputs from here (They're outside the main loop because its EVENT DRIVEN) ->>
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
         * is executed continously.
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
        /*
         * Add game logic here, also, add below here the input user handler
         */


    }

    private void render(){
        /*
         * Add render here
         */
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

    public void setOnPause(Runnable r) {
        this.onPause = r;
    }

    public void setOnMenu(Runnable r) {
        this.onMenu = r;
    }
}
