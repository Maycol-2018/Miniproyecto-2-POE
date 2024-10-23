package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Board;
import com.example.miniproyecto2.model.Chronometer;
import com.example.miniproyecto2.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert; // Libreria que muestra ventanas emergentes como alertas de información, confirmación, error o precaución
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class GameController {
    private Chronometer chronometer;
    private Game game;
    private Board board;

    // label que muestra la cantidad de pistas disponibles
    @FXML
    private Label hintsLabel;
    // boton  que el usuario presiona para solicitar ayuda en una pista
    @FXML
    private Button hintButton;

    @FXML
    private Label labelTime;

    @FXML
    private GridPane gridPane;

    public GameController() {}

    @FXML
    public void initialize(){
        // Pasamos "this" como referencia de GameController
        this.game = new Game(this);
        board = new Board();
        chronometer = new Chronometer(this);
        // Se proporciona al cronometro una referencia directa al objeto actual de la clase (GameController)
        // Así la clase cronometro puede acceder a los elementos de GameController.
        startGame();
        System.out.println("Game controller inicializado");
        hintsLabel.setVisible(false); // El label está oculto inicialmente
        hintButton.setDisable(false); // El botón está habilitado desde el inicio
    }

    public void startGame(){

        chronometer.start();
        board.fillMatriz();
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
                        game.paintSquares(finalRow, finalColumn);
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

    // Metodo que se ejecuta cuando se presione el boton de nuevo juego
    @FXML
    public void newGame(ActionEvent event) {
        game.cleanMatriz();
        game.fillblocks();
        chronometer.restart();
        // Metodo que reinicie el contador
        // Metodo que reinicie los errores
        // Metodo que reinicie la puntuación
        System.out.println("PRESIONADO");
    }

    public GridPane getGridPane(){
        return gridPane;
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
