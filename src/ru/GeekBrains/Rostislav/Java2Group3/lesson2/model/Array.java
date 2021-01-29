package ru.GeekBrains.Rostislav.Java2Group3.lesson2.model;

public class Array {
    private static final int SIZE_OF_ARRAY = 4;
    private final String[][] arr = new String[SIZE_OF_ARRAY][SIZE_OF_ARRAY];
    public Array(){
        for (int i = 0; i < SIZE_OF_ARRAY; i ++) {
            for (int j = 0; j < SIZE_OF_ARRAY; j += 2) {
                arr[i][j] = String.valueOf(i + j);
                arr[i][j+1] = String.valueOf('X'); // передан непригодный аргумент
            }
        }
    }
    public String[][] getArr() {
        return arr;
    }
}
