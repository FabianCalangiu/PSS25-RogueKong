package it.unibo.roguekong;

import it.unibo.roguekong.view.impl.RogueKongViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        RogueKongViewImpl view = new RogueKongViewImpl();
        view.addTestImage();

        stage.setTitle("RogueKong");
        stage.setScene(view.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}