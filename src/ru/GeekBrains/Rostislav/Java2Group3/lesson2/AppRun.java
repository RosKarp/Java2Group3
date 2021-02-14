package ru.GeekBrains.Rostislav.Java2Group3.lesson2;

import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.Array;
import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.MyArrayDataException;
import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.MyArraySizeException;
import ru.GeekBrains.Rostislav.Java2Group3.lesson2.model.WrongSizeArray;
import ru.GeekBrains.Rostislav.Java2Group3.lesson2.service.TestOfExceptions;

public class AppRun {
    public static void main(String[] args) {
        int sum;
        TestOfExceptions test = new TestOfExceptions();
        WrongSizeArray wrongSizeArray = new WrongSizeArray();
        try {
            test.toIntAndSum(wrongSizeArray.getArr());
        }
        catch (MyArraySizeException e) {
            System.out.println("# 1");
            System.out.println("Invalid array size");
            System.out.println(e.toString());
            System.out.println();
        }

        Array array = new Array();
        try {
            sum = test.toIntAndSum(array.getArr());
            System.out.println("# 2 and # 3");
            System.out.println("Sum of array elements = " + sum);
        }
        catch (MyArrayDataException e) {
            System.out.println("# 2 and # 3");
            for (int i = 0; i < e.getFalseData().length; i++) {
                for (int j = 0; j < e.getFalseData().length; j++) {
                    if(e.getFalseData()[i][j] == 1){
                        System.out.println("Conversion to type int gave an error on an array[" + i +"][" + j + "] element.");
                    }
                }

            }
            System.out.println();
            System.out.println("Sum of remaining elements: " + e.getSum());
        }
    }
}
