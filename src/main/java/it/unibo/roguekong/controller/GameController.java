package it.unibo.roguekong.controller;

import it.unibo.roguekong.view.impl.GameView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/*
 * This is the actual gameloop.
 * From javafx docs: The class AnimationTimer allows to create a timer, that is called in each frame while it is active.
 * An extending class has to override the method handle(long) which will be called in every frame. The methods start() and stop() allow to start and stop the timer
 */

public class GameController {
    private AnimationTimer gameLoop;

    public GameController(GameView view){
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                pause();
            }
        });

        this.gameLoop = new AnimationTimer(){
            @Override
            public void handle(long now){
                update();
                render();
            }
        };
    }

    public void start(){
        gameLoop.start();
    }

    public void stop(){
        gameLoop.stop();
    }

    private

    private void update(){
        /*
         * Add game logic here
         */
    }

    private void render(){
        /*
         * Add render here
         */
    }
}
