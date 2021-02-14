package ru.GeekBrains.Rostislav.Java2Group3.lesson4;

import ru.GeekBrains.Rostislav.Java2Group3.lesson4.interfaces.ForLambda;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {

    // #4. Напишите метод, который возвращает наибольшее целое число в списке.
    public static Integer maximum(Integer[] list) {
        return Arrays.stream(list)
                .max(Integer::compareTo)
                .get();
    }

    // #5. Напишите метод, который возвращает среднее значение из списка целых чисел.
    public static Double average(List<Integer> list) {
        return list.stream().collect(Collectors.averagingDouble(Integer::intValue));
    }

    // #6. Имея список строк, напишите метод, который возвращает список всех строк, которые начинаются с буквы «а» (нижний регистр) и имеют ровно 3 буквы.
    public static List<String> search(List<String> list) {
        return list.stream()
                .filter(s -> s.length() == 3)
                .filter(s -> s.charAt(0) == 'a')
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        // #2. Напишите метод, который возвращает индекс первого вхождения данного целого числа в списке.
        ForLambda.IndexOfInt index = ((n, list) -> {
            for (int i = 0; i < list.length; i++) {
                if(list[i].equals(n)){
                    return i;
                }
            }
            return -1;
        });
        Integer[] myArray = {5, 8, 4, 33, 56, 7, 4, 3, 9, 3};
        int indexOfNumber = index.search(3, myArray);
        System.out.println("#2");
        System.out.println("Index of the first occurrence 3: " + indexOfNumber);
        System.out.println();

        // #3. Напишите метод, переворачивающий строку.
        ForLambda.ReverseString string = s -> {
            char[] a;
            a = s.toCharArray();
            char[] b = new char[s.length()];
            for (int i = 0; i < s.length(); i++) {
                b[s.length() - 1 - i] = a[i];
            }
            return String.valueOf(b);
        };
        String sourceString = "I like Java!";
        System.out.println("#3");
        System.out.println("Reversed 'I like Java!' - " + string.reverse(sourceString));
        System.out.println();

        //#4. Напишите метод, который возвращает наибольшее целое число в списке.
        Integer[] input = {15, 2, 1, 4, 5, 6, 20, 3, 6, 48};
        System.out.println("#4");
        System.out.println("Maximum value from {15, 2, 1, 4, 5, 6, 20, 3, 6, 48}");
        System.out.println(maximum(input));
        System.out.println();

        //#5. Напишите метод, который возвращает среднее значение из списка целых чисел.
        List<Integer> ints = Arrays.asList(15, 2, 1, 4, 5, 6, 20, 3, 6, 48);
        System.out.println("#5");
        System.out.println("Average value from {15, 2, 1, 4, 5, 6, 20, 3, 6, 48}");
        System.out.println(average(ints));
        System.out.println();

        //#6. Имея список строк, напишите метод, который возвращает список всех строк, которые начинаются с буквы «а» (нижний регистр) и имеют ровно 3 буквы.
        List<String> words = Arrays.asList("angle", "and", "africa", "text", "rope", "dog", "add");
        System.out.println("#6");
        System.out.println("A words with three letters from \"angle\", \"and\", \"africa\", \"text\", \"rope\", \"dog\", \"add\"");
        search(words).forEach(System.out::println);
    }
}
