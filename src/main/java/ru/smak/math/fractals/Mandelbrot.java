package ru.smak.math.fractals;

import ru.smak.math.Complex;

public abstract class Mandelbrot implements Fractal{
    private int maxIterations;
    private double r;

    public Mandelbrot(){
        this(200, 2);
    }

    public Mandelbrot(int maxIterations, double r){
        this.maxIterations = maxIterations;
        this.r = r;
    }

    public int getMaxIterations(){
        return maxIterations;
    }

    public void setMaxIterations(int value){
        maxIterations = Math.max(Math.abs(value), 25);
    }

    public double getR(){
        return r;
    }

    public void setR(double value){
        r = Math.max(Math.abs(value), Double.MIN_VALUE);
    }
}
