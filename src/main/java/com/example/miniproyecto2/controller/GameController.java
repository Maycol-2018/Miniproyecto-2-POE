package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Chronometer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert; // Libreria que muestra ventanas emergentes como alertas de información, confirmación, error o precaución
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


    @FXML
    void onHandleButtonHowToPlay(ActionEvent event) {
        // Crear una cuadro de diálogo de tipo "INFORMATION" que mostrará un cuadro de diálogo informativo
        Alert showMessageHowToPlay = new Alert(Alert.AlertType.INFORMATION);
        // Establece el título del cuadro de diálogo, el encabezado, el contenido principal del mensaje de la ventana emergente
        showMessageHowToPlay.setTitle("Instrucciones");
        showMessageHowToPlay.setHeaderText("Instrucciones del Juego");
        showMessageHowToPlay.setContentText("Bienvenido,\n" +
                "A continuación, te explico cómo jugar:\n" +
                "\n1. Para completar la cuadrícula de Sudoku, debes ingresar un número del 1 al 6 en cada celda.\n" +
                "\n2. Cada fila, columna y bloque de 2x3 debe contener los números del 1 al 6 sin repetir.\n" +
                "\n3. Puedes solicitar hasta tres pistas presionando el botón 'Pista'. Te mostrará un número válido para una celda vacía.\n" +
                "\n4. Debes asegurarte de no dejar ninguna celda vacía ni repetir números en filas, columnas o bloques.\n" +
                "\n¡Buena suerte y diviértete jugando!");

        // Mostrar el cuadro de diálogo en pantalla y esperar a que el usuario la cierre antes de continuar
        showMessageHowToPlay.showAndWait();
    }
}
