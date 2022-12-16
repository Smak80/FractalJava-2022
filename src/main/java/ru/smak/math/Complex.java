package ru.smak.math;

import org.jetbrains.annotations.NotNull;

public class Complex{
    private double re, im;
    public double getRe(){
        return re;
    }
    public double getIm(){
        return im;
    }
    public void setRe(double re){
        this.re = re;
    }
    public void setIm(double im){
        this.im = im;
    }
    public Complex(double r, double i){
        re = r;
        im = i;
    }
    public Complex(Complex other){
        re = other.re;
        im = other.im;
    }
    public Complex(double r){
        re = r;
        im = 0.0;
    }
    public Complex plus(@NotNull Complex other){
        return new Complex(re + other.re, im + other.im);
    }
    public Complex minus(@NotNull Complex other){
        return new Complex(re - other.re, im - other.im);
    }
    public Complex times(@NotNull Complex other){
        return new Complex(
                re * other.re - im * other.im,
                re * other.im + im * other.re
                );
    }
    public Complex div(@NotNull Complex other){
        double den = other.abs2();
        return new Complex(
                (re * other.re + im * other.im) / den,
                (re * other.im - im * other.re) / den
        );
    }
    public double abs(){
        return Math.sqrt(re * re + im * im);
    }
    public double abs2(){
        return re * re + im * im;
    }
    public Complex eInPower(Complex z){
        double x = z.re;
        double y = z.im;
        return new Complex(Math.exp(x)*Math.cos(y), Math.exp(x)*Math.sin(y));
    }
    public Complex cos(Complex z){
        Complex i = new Complex(0,1);
        return new Complex((eInPower(z.times(i)).plus(eInPower(z.times(i.times(new Complex(-1,0)))))).times(new Complex(0.5,0)));
    }
    public Complex sin(Complex z){
        Complex i = new Complex(0,1);
        return new Complex((eInPower(z.times(i)).minus(eInPower(z.times(i.times(new Complex(-1,0)))))).times(new Complex(0, -0.5)));
    }
    public Complex ch(Complex z){
        return new Complex((eInPower(z).plus(eInPower(z.times(new Complex(-1,0))))).times(new Complex(0.5,0)));
    }
    public Complex sh(Complex z){
        return new Complex((eInPower(z).minus(eInPower(z.times(new Complex(-1,0))))).times(new Complex(0.5,0)));
    }

    /**
     *
     * @return аргумент комплексного числа
     */
    public float arg()
    {
        //return (float)Math.atan(this.getIm()/this.getRe());
        return (float)Math.acos((this.getRe())/(this.abs()));
    }
    public float arg2()
    {
        //return (float)Math.atan(this.getIm()/this.getRe());
        return (float)Math.asin((this.getRe())/(this.abs()));
    }
}

