package com.example.miniproyecto2.model;

public interface IBoard {
    boolean isSafe(int f, int c, int num);
    boolean isBlockSafe(int i, int j, int value);
    int blockRows(int f);
    int blockColumns(int c);
    void insertNumber(int f, int c, int num);
    void removeNumber(int f, int c, int num);
    boolean solveSudoku(int f, int c);
    void fillMatriz();
}
