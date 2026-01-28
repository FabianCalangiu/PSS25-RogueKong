package it.unibo.roguekong.view.impl;

import it.unibo.roguekong.model.game.impl.ScoreRecord;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class ScoreView {
    private final Scene scene;
    private Runnable onReturn;

    private Label score1;
    private Label score2;
    private Label score3;

    public ScoreView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        this.score1 = new Label("-");
        this.score2 = new Label("-");
        this.score3 = new Label("-");

        score1.getStyleClass().add("box1");
        score2.getStyleClass().add("box2");
        score3.getStyleClass().add("box3");

        score1.setMinSize(150, 50);
        score2.setMinSize(150, 50);
        score3.setMinSize(150, 50);

        Button returnButton = new Button("Return");

        returnButton.setOnAction(e -> runIfNotNull(onReturn));

        root.getChildren().addAll(score1, score2, score3, returnButton);
        this.scene = new Scene(root, 800, 600);
        this.scene.getStylesheets().add(
                getClass().getResource("/css/menu.css").toExternalForm()
        );
    }

    private String format(List<ScoreRecord> scores, int index){
        if(scores.size() <= index){
            return "-";
        }
        ScoreRecord s = scores.get(index);
        return s.name() + " - " + s.score();
    }

    public void setScores(List<ScoreRecord> scores){
        score1.setText(format(scores, 0));
        score2.setText(format(scores, 1));
        score3.setText(format(scores, 2));
    }

    public Scene getScene() {
        return scene;
    }

    public void setOnReturn(Runnable r) {
        this.onReturn = r;
    }

    private void runIfNotNull(Runnable r) {
        if (r != null) r.run();
    }
}