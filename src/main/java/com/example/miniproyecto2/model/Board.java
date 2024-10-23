package com.example.miniproyecto2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    // Lista que contiene otras listas
    static List<List<Integer>>  matriz = new ArrayList<>();

    static boolean[][] rows = new boolean[6][7]; // Para verificar si un número se ha utilizado en una fila
    static boolean[][] columns = new boolean[6][7]; // Para verificar si un número se ha utilizado en una columna

    public static boolean isSafe(int f, int c, int num) {
        return !rows[f][num] && !columns[c][num];
    }

    // Verifica que en los cuadrantes los numeros no se repitan
    public static boolean isBlockSafe(int i, int j, int value){
        int posI = blockRows(i);
        int posJ = blockColumns(j);

        for(int k = posI-2; k<posI; k++){
            for(int l = posJ-3; l<posJ; l++){
                if(matriz.get(k).get(l) == value){
                    return false;
                }
            }
        }
        return true;
    }

    // Metodo que retorna la fila del bloque en que se encuentra el valor que se esta probando
    public static int blockRows(int f){
        if(f<=1) return 2;
        if(f<=3) return 4;
        else return 6;
    }

    // Metodo que retorna la columna del bloque en que se encuentra el valor que se esta probando
    public static int blockColumns(int c){
        if(c<=2) return 3;
        else return 6;
    }

    public static void insertNumber(int f, int c, int num) {
        matriz.get(f).set(c,num);

        rows[f][num] = true;
        columns[c][num] = true;
    }

    public static void removeNumber(int f, int c, int num) {
        matriz.get(f).set(c,0);

        rows[f][num] = false;
        columns[c][num] = false;
    }

    public static boolean solveSudoku(int f, int c) {
        // Si hemos llegado al final de la matriz, hemos completado el Sudoku
        if (f == 6) {
            return true;
        }

        // Si estamos al final de la fila, movemos a la siguiente fila
        if (c == 6) {
            return solveSudoku(f + 1, 0);
        }

        // Si la celda ya está llena, seguimos a la siguiente columna
        if (matriz.get(f).get(c) != 0) {
            return solveSudoku(f, c + 1);
        }

        // Generamos una lista con los números del 1 al 6
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int num = 1; num <= 6; num++) {
            numbers.add(num);
        }
        // Barajamos la lista
        // Llama al metodo shuffle de la clase collections que reodena los elementos de una lista aleatoriamente.
        Collections.shuffle(numbers);

        // Intentamos colocar los números aleatoriamente
        for (int num : numbers) {
            // Verificamos si es seguro colocar el número
            if (isSafe(f,c,num) && isBlockSafe(f,c,num)) {
                insertNumber(f, c, num);
                // Llamada recursiva para el siguiente número
                if (solveSudoku(f, c + 1)) {
                    return true;
                }
                // Si no se pudo resolver, retiramos el número
                removeNumber(f, c, num);
            }
        }
        // Si no se pudo colocar ningún número, devolvemos falso
        return false;
    }

    public static void fillMatriz() {

        // Añadimos las filas (listas internas) a la lista externa
        for (int i = 0; i < 6; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                row.add(0);  // Añadimos valores por defecto con "0"
            }
            matriz.add(row);  // Añadimos la fila completa a la matriz
        }

        solveSudoku(0, 0);
    }

    public static void showMatriz(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(matriz.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>>  getMatriz() {
        return matriz;
    }
}


