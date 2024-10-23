package com.example.miniproyecto2.model;

import com.example.miniproyecto2.controller.GameController;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<List<Integer>>  matriz;
    private Random random = new Random();
    private GridPane gridPane;
    private GameController gameController;

    public Game(GameController gameController) {
        this.gameController = new GameController();
        matriz = Board.getMatriz();
        gridPane = gameController.getGridPane();
    }

    // Metodo para obtener el valor correcto de la matriz
    public int getMatrizValue(int row, int col) {
        return matriz.get(row).get(col);
    }



    public void fillTxtLabel(){
        int digit;
        matriz = Board.getMatriz();
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 6; column++) {
                // Se obtiene el textfield del GridPane. Correspondiente a la fila y columna actual
                TextField txtField = (TextField) getNodeByRowColumnIndex(row, column, gridPane);
                digit = matriz.get(row).get(column);
                txtField.setText(digit + "");
            }
        }
    }


    public void fillblocks(){
        matriz = Board.getMatriz();
        // Iterar sobre los bloques de 2x3 en la matriz
        for (int bloqueFila = 0; bloqueFila < 6; bloqueFila += 2) {
            for (int bloqueColumna = 0; bloqueColumna < 6; bloqueColumna += 3) {
                List<int[]> posicionesBloque = new ArrayList<>();

                // Agregar todas las posiciones del bloque actual
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        posicionesBloque.add(new int[]{bloqueFila + i, bloqueColumna + j});
                    }
                }

                // Seleccionar dos posiciones distintas del bloque
                int[] posicion1 = posicionesBloque.remove(random.nextInt(posicionesBloque.size()));
                int[] posicion2 = posicionesBloque.remove(random.nextInt(posicionesBloque.size()));

                int pos11 = posicion1[0];
                int pos12 = posicion1[1];
                TextField txtField = (TextField) getNodeByRowColumnIndex(pos11, pos12, gridPane);
                int num = matriz.get(pos11).get(pos12);
                txtField.setText(num + "");

                int pos21 = posicion2[0];
                int pos22 = posicion2[1];
                TextField txtField2 = (TextField) getNodeByRowColumnIndex(pos21, pos22, gridPane);
                int num2 = matriz.get(pos21).get(pos22);
                txtField2.setText(num2 + "");
            }
        }
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null &&
                    GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }


}
