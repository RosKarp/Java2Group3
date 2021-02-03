package ru.GeekBrains.Rostislav.Java2Group3.lesson2.model;

public class MyArrayDataException extends NumberFormatException {
    private final int[][] FalseData;
    private final int sum;
    public MyArrayDataException(int[][] a, int b) {
    this.FalseData = a;
    this.sum = b;
    }

    public int[][] getFalseData() {
        return FalseData;
    }

    public int getSum() {
        return sum;
    }
}
