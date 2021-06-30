package com.checkers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu extends StackPane {

    public Menu(final Stage stage) {

        Button newGame = new Button("Start New Game");
        newGame.setFont(new Font("Lato",22));

        Button mute = new Button("Mute");
        mute.setFont(new Font("Lato",22));
        mute.setTranslateX(-300);
        mute.setTranslateY(300);

        newGame.setOnAction(e -> {
            printGameOptions(stage);
        });

        setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(newGame, mute);

    }

    private void printGameOptions(Stage stage){
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton pvp = new RadioButton("PvP");
        pvp.setFont(new Font("Lato", 22));
        pvp.setMinWidth(150);
        pvp.setMinHeight(80);
        pvp.setTranslateX(200);
        pvp.setTranslateY(100);
        pvp.getStyleClass().remove("radio-button");
        pvp.getStyleClass().add("toggle-button");

        RadioButton pvc = new RadioButton("PvC");
        pvc.setFont(new Font("Lato", 22));
        pvc.setMinWidth(150);
        pvc.setMinHeight(80);
        pvc.setTranslateX(450);
        pvc.setTranslateY(100);
        pvc.getStyleClass().remove("radio-button");
        pvc.getStyleClass().add("toggle-button");

        Button start = new Button("START");
        start.setFont(new Font("Lato",22));
        start.setTranslateX(650);
        start.setTranslateY(700);

        Button back = new Button("BACK");
        back.setFont(new Font("Lato",22));
        back.setTranslateX(50);
        back.setTranslateY(700);

        back.setOnAction(e -> {
            Checkers checkers = new Checkers();
            checkers.start(stage);
        });

        pvp.setOnAction(e -> {
            playAgainstPlayer(stage, back, start);
        });

        pvc.setOnAction(e -> {
            playAgainstComputer(stage, back, start);
        });

        pane.getChildren().addAll(pvp,pvc,back,start);

        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void playAgainstPlayer(Stage stage, Button back,Button start){

        Label labelNames = new Label("Enter your names: ");
        labelNames.setFont(new Font("Lato",22));
        labelNames.setTranslateX(300);
        labelNames.setTranslateY(200);

        Label playerOneLabel = new Label("Player1: ");
        playerOneLabel.setFont(new Font("Lato",22));
        playerOneLabel.setTranslateX(250);
        playerOneLabel.setTranslateY(250);

        Label playerTwoLabel = new Label("Player2: ");
        playerTwoLabel.setFont(new Font("Lato",22));
        playerTwoLabel.setTranslateX(250);
        playerTwoLabel.setTranslateY(300);

        TextField playerOne = new TextField();
        playerOne.setTranslateX(400);
        playerOne.setTranslateY(250);

        TextField playerTwo = new TextField();
        playerTwo.setTranslateX(400);
        playerTwo.setTranslateY(300);

        back.setOnAction(e -> {
            printGameOptions(stage);
        });

        start.setOnAction(e -> {
            Board board = new Board();
            board.printBoard(stage, playerOne, playerTwo);
        });

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().addAll(labelNames, playerOneLabel, playerTwoLabel, playerOne, playerTwo, back, start);

        Scene scene = new Scene(pane, 800, 800);
        stage.setTitle("Checkers");
        stage.setScene(scene);
        stage.show();
    }

    private void playAgainstComputer(Stage stage, Button back, Button start){
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        ToggleGroup levelButtons = new ToggleGroup();

        RadioButton easy = new RadioButton("EASY");
        easy.getStyleClass().remove("radio-button");
        easy.getStyleClass().add("toggle-button");
        easy.setToggleGroup(levelButtons);
        easy.setMinWidth(130);
        easy.setMinHeight(80);
        easy.setTranslateX(200);
        easy.setTranslateY(250);
        easy.setSelected(true);

        RadioButton medium = new RadioButton("MEDIUM");
        medium.getStyleClass().remove("radio-button");
        medium.getStyleClass().add("toggle-button");
        medium.setToggleGroup(levelButtons);
        medium.setMinWidth(130);
        medium.setMinHeight(80);
        medium.setTranslateX(350);
        medium.setTranslateY(250);

        RadioButton hard = new RadioButton("HARD");
        hard.getStyleClass().remove("radio-button");
        hard.getStyleClass().add("toggle-button");
        hard.setToggleGroup(levelButtons);
        hard.setMinWidth(130);
        hard.setMinHeight(80);
        hard.setTranslateX(500);
        hard.setTranslateY(250);

        Label nameLabel = new Label("Enter your name: ");
        nameLabel.setFont(new Font("Lato", 18));
        nameLabel.setTranslateX(200);
        nameLabel.setTranslateY(400);

        TextField playerName = new TextField();
        playerName.setTranslateX(400);
        playerName.setTranslateY(400);

        back.setOnAction(e -> {
            printGameOptions(stage);
        });

        start.setOnAction(event -> {
            Board board = new Board();
            TextField computer = new TextField("Computer");
            board.printBoard(stage, playerName, computer);
        });

        pane.getChildren().addAll(easy, medium, hard, nameLabel, playerName, back, start);
        Scene scene = new Scene(pane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

}
