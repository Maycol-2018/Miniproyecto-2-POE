package com.example.miniproyecto2.model;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public interface IGame {
    void cleanMatriz();
    void fillblocks();
    void paintSquares(int row, int column);
    void paintCurrentBox(int row, int column);
    void paintRows(int row);
    void paintColumns(int column);
    void paintBlock(int row, int column);
    void removePaintMatriz();
    void removePaintLastBox();
    void removePaintBlock();
    void removePaintRows(int row);
    void removePaintColumns(int column);
    int getRowBlock(int row);
    int getColumnBlock(int column);
    void editableBoxesFalse(int row1, int column1, int row2, int column2);
    void editableBoxesTrue();
    Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane);
    void showSolutionSudoku();
}
