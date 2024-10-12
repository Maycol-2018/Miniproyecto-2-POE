package com.example.miniproyecto2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label labelTime;

    @FXML
    void handlePlay(ActionEvent event) {
        System.out.println("PRESIONADO");
    }

    @FXML
    public void initialize() {
        System.out.println("Controlador del game inicializado");
    }
}
