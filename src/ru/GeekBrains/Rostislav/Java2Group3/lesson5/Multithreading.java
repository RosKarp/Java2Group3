package ru.GeekBrains.Rostislav.Java2Group3.lesson5;

public class Multithreading {

    private static final int size = 10000000;
    private static final int h = size/2;
    private final float[] a1 = new float[h];
    private final float[] a2 = new float[h];

    public void method1() {
        final float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Time to evaluate an expression in one thread: " + (System.currentTimeMillis() - a) + " ms.");
    }

    public void method2() {
        final float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1.0f;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        MyRunnableClass first = new MyRunnableClass(a1);
        MyRunnableClass second = new MyRunnableClass(a2);
        Thread firstFlow = new Thread(first);
        Thread secondFlow = new Thread(second);
        firstFlow.start();
        secondFlow.start();
        try {
            firstFlow.join();
            secondFlow.join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        System.arraycopy(first.getArray(), 0, arr, 0, h);
        System.arraycopy(second.getArray(), 0, arr, h, h);
        System.out.println("Time to evaluate an expression in two threads: " + (System.currentTimeMillis() - a) + " ms.");
    }

    public static void main(String[] args) {
        Multithreading multithreading = new Multithreading();
        multithreading.method1();
        multithreading.method2();
    }
}