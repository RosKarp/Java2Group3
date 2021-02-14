package ru.GeekBrains.Rostislav.Java2Group3.lesson2.model;

public class WrongSizeArray {
    private static final int SIZE_OF_ARRAY = 5;
    private final String[][] arr = new String[SIZE_OF_ARRAY][SIZE_OF_ARRAY];
    public WrongSizeArray(){
        for (int i = 0; i < SIZE_OF_ARRAY; i++) {
            for (int j = 0; j < SIZE_OF_ARRAY; j++) {
                arr[i][j] = String.valueOf(i + j);
            }
        }
    }
    public String[][] getArr() {
        return arr;
    }
}
