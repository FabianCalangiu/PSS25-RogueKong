package it.unibo.roguekong;

import it.unibo.roguekong.view.impl.MenuView;
import it.unibo.roguekong.view.impl.RogueKongViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        RogueKongViewImpl view = new RogueKongViewImpl();
        view.addTestImage();

        MenuView menu = new MenuView();

        stage.setTitle("RogueKong");
        stage.setScene(menu.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}