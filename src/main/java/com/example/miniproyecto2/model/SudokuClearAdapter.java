package com.example.miniproyecto2.model;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SudokuClearAdapter {
    private Game game;
    private GridPane gridPane;

    public SudokuClearAdapter(Game game, GridPane gridPane) {
        this.game = game;
        this.gridPane = gridPane;
    }

    public void clearSudokuBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField textField = (TextField) game.getNodeByRowColumnIndex(row, col, gridPane);
                if (textField != null && textField.isEditable()) {
                    // Limpiar solo las celdas editables
                    textField.clear();
                }
            }
        }
    }
}