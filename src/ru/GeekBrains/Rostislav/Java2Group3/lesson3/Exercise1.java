package ru.GeekBrains.Rostislav.Java2Group3.lesson3;

import java.util.*;

public class Exercise1 {

    private static final String[] myArray = {"wolf", "cup", "spoon", "hat", "hand", "rabbit", "wolf", "head", "hat", "table", "lamp", "tiger", "cup", "lamp", "wolf"};
    private static final Set<String> set = new HashSet<>();
    private static final HashMap<String, Integer> hashMap = new HashMap<>();
    private static int count = 0;

    public static void main(String[] args) {

        Collections.addAll(set, myArray);
        System.out.println("Unique array elements:");
        System.out.println(set);
        System.out.println();

        for (String s : myArray) {
            for (String value : myArray) {
                if (s.equals(value)) {
                    count++;
                }
            }
            hashMap.put(s, count);
            count = 0;
        }

        for (Map.Entry<String, Integer> o : hashMap.entrySet()) {
            System.out.println(o.getKey() + " occurs " + o.getValue() + " times.");
        }
    }
}
