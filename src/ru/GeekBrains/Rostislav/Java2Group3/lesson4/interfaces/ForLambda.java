package ru.GeekBrains.Rostislav.Java2Group3.lesson4.interfaces;

public class ForLambda {
    @FunctionalInterface
    public interface IndexOfInt {
        int search(Integer n, Integer[] list);
    }
    @FunctionalInterface
    public interface ReverseString {
        String reverse(String s);
    }
}
