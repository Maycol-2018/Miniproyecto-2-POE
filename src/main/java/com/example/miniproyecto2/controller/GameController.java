package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Board;
import com.example.miniproyecto2.model.Chronometer;
import com.example.miniproyecto2.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class GameController {
    private Chronometer chronometer;
    private Game game;

    @FXML
    private Label labelTime;

    @FXML
    private GridPane gridPane;

    public GameController() {}

    @FXML
    public void initialize(){
        // Pasamos "this" como referencia de GameController
        this.game = new Game(this);
        chronometer = new Chronometer(this);
        // Se proporciona al cronometro una referencia directa al objeto actual de la clase (GameController)
        // Así la clase cronometro puede acceder a los elementos de GameController.
        startGame();
        System.out.println("Game controller inicializado");
    }

    public void startGame(){

        chronometer.start();
        Board.fillMatriz();
        generateEvents();
        //fillTxtLabel();
        game.fillblocks();
    }


    @FXML
     public Label getLabelTime() {
        return labelTime;
    }

    public void generateEvents(){
        // Recorrer todas las filas y columnas del GridPane 6x6
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                TextField txtField = (TextField) game.getNodeByRowColumnIndex(row, column, gridPane);

                // Verificar que la celda contenga un Label
                if (txtField != null) {
                    // Asignar un evento de clic a cada Label
                    int finalRow = row;
                    int finalColumn = column;
                    txtField.setOnMouseClicked(event -> {
                        System.out.println("Label at [" + finalRow + "," + finalColumn + "] clicked: " + txtField.getText());
                    });

                    txtField.setTextFormatter(new TextFormatter<>(change -> {
                        // Limitar la longitud a 2 caracteres
                        if (change.getControlNewText().length() > 1) {
                            change.setText(""); // No permite agregar más caracteres
                        }
                        // Validar que solo se ingresen números
                        if (!change.getControlNewText().matches("[1-9]*")) {
                            change.setText(""); // No permite caracteres que no sean números
                        }

                        return change;
                    }));
                }
            }
        }
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        System.out.println("PRESIONADO");
    }

    public GridPane getGridPane(){
        return gridPane;
    }
}
