package ru.GeekBrains.Rostislav.Java2Group3.lesson5;

public class MyRunnableClass implements Runnable {
    private final float[] c;
    public MyRunnableClass(float[] c){
        this.c = c;
    }
    @Override
    public void run(){
        for (int i = 0; i < c.length; i++) {
            c[i] = (float)(c[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
    public float[] getArray() {
        return c;
    }
}