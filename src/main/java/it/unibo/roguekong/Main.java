package it.unibo.roguekong;

import it.unibo.roguekong.view.impl.MenuView;
import it.unibo.roguekong.view.impl.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        MenuView menuView = new MenuView();
        GameView gameView = new GameView();

        menuView.setOnStart(() -> {
            stage.setScene(gameView.getScene());
        });

        stage.setTitle("RogueKong");
        stage.setScene(menuView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}