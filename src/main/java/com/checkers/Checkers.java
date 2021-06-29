package com.checkers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Checkers extends Application {
    @Override
    public void start(Stage stage){

        Menu menu = new Menu(stage);
        Scene scene = new Scene(menu, 800, 800);

        stage.setTitle("Checkers");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
