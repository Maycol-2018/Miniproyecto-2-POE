package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Chronometer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert; // Libreria que muestra ventanas emergentes como alertas de información, confirmación, error o precaución
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class GameController {
    private Chronometer chronometer;

    // label que muestra la cantidad de pistas disponibles
    @FXML
    private Label hintsLabel;
    // boton  que el usuario presiona para solicitar ayuda en una pista
    @FXML
    private Button hintButton;

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
        hintsLabel.setVisible(false); // El label está oculto inicialmente
        hintButton.setDisable(false); // El botón está habilitado desde el inicio
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
                "\n3. Puedes solicitar hasta seis pistas presionando el botón 'Pista'. Te mostrará un número válido para una celda vacía.\n" +
                "\n4. Debes asegurarte de no dejar ninguna celda vacía ni repetir números en filas, columnas o bloques.\n" +
                "\n¡Buena suerte y diviértete jugando!");

        // Mostrar el cuadro de diálogo en pantalla y esperar a que el usuario la cierre antes de continuar
        showMessageHowToPlay.showAndWait();
    }

    private int availableHints = 6; // Iniciamos con 6 pistas disponibles

    // Metodo que maneja el evento del clic del boton de pistas
    @FXML
    void onHandleButtonHint(ActionEvent event) {
        System.out.println("BOTÓN DE PISTAS PRESIONADO");
        if (availableHints > 0) {
            showHint();
        } else {
            System.out.println("NO HAY MÁS PISTAS DISPONIBLES");
            hintButton.setDisable(true);
        }
    }

    private void showHint() {
        availableHints--; // Reduce el número de pistas disponibles
        System.out.println("MOSTRANDO PISTA. PISTAS RESTANTES: " + availableHints);

        // Genera y muestra la pista
        String hint = "Esta es una pista para tu juego"; // Genera la pista aquí

        // Muestra la pista al jugador
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pista");
        alert.setHeaderText("Aquí tienes una pista");
        alert.setContentText(hint);
        alert.showAndWait();
        // Actualiza contador de pistas
        updateHintDisplay();

        // Aqui añadir cualquier lógica adicional del juego después de mostrar una pista
    }

    // Metodo que actualiza la visualización del contador de pistas
    private void updateHintDisplay() {
        if (availableHints > 0) {
            // Muestra solo el número de pistas restantes
            hintsLabel.setText(String.valueOf(availableHints));
            hintsLabel.setVisible(true);
        } else {
            // Cuando no quedan pistas, oculta el label y deshabilita el botón
            hintsLabel.setVisible(false);
            hintButton.setDisable(true);
        }
    }
}
