package it.unibo.roguekong.controller;

import it.unibo.roguekong.model.game.GameState;
import it.unibo.roguekong.view.impl.MenuView;

/*
 * This may not be necessary
 */
public class MenuController {
    public MenuController(MenuView view, GameState gameState){
        view.setOnStart(() -> {
            gameState.startGame();
        });
    }
}
