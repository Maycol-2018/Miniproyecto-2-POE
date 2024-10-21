package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Chronometer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    private Chronometer chronometer;

    @FXML
    private Label labelTime;

    public GameController() {}

    @FXML
    public void initialize(){
        chronometer = new Chronometer(this);
        // Se proporciona al cronometro una referencia directa al objeto actual de la clase (GameController)
        // Así la clase cronometro puede acceder a los elementos de GameController.
        //chronometer = new Chronometer();
        chronometer.start();
        System.out.println("Game controller inicializado");
    }

    @FXML
     public Label getLabelTime() {
        return labelTime;
    }

    @FXML
    void handlePlay(ActionEvent event) {
        System.out.println("PRESIONADO");
    }
}
