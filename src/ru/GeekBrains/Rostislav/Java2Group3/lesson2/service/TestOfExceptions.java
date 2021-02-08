package ru.GeekBrains.Rostislav.Java2Group3.lesson2.service;

import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.MyArrayDataException;
import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.MyArraySizeException;

public class TestOfExceptions {

    public int toIntAndSum(String[][] strings) throws MyArraySizeException, MyArrayDataException {  // требуемый в №1, №2, №3 метод

        if(strings.length != 4 || strings[0].length != 4 || strings[1].length != 4 || strings[2].length != 4 || strings[3].length != 4) {
            throw new MyArraySizeException();
        }
        int[][] intFromString = new int[strings.length][strings.length];
        int[][] FalseData = new int[strings.length][strings.length];
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                try{
                    intFromString[i][j] = Integer.parseInt(strings[i][j]);
                }
                catch (NumberFormatException e){
                    FalseData[i][j] = 1;
                    intFromString[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                sum += intFromString[i][j];
            }
        }
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                if(FalseData[i][j] == 1){
                    throw new MyArrayDataException(FalseData, sum);
                }
            }
        }
        return sum;
    }
}

