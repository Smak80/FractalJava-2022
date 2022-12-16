package ru.smak.graphics;

import org.jetbrains.annotations.NotNull;
import ru.smak.gui.MainWindow;
import ru.smak.gui.Painter;
import ru.smak.math.Complex;
import ru.smak.math.fractals.Fractal;
import ru.smak.movie.MovieWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FractalPainter implements Painter {

    private final Plane plane;
    private Fractal f;
    private Graphics g;

    public Plane getPlane() {
        return plane;
    }

    private Colorizer colorFunc;

    public FractalPainter(Plane plane, Fractal f, Colorizer colorFunc) {
        this.plane = plane;
        this.f = f;
        this.colorFunc = colorFunc;
    }

    public FractalPainter(FractalPainter other) {
        this.plane = new Plane(other.getPlane());
        this.f = other.getFractal();
        this.colorFunc = other.getColorFunc();
    }

    public Colorizer getColorFunc() {
        return colorFunc;
    }
    public Fractal getFractal(){return f;}
    public void setColorFunc(Colorizer colorFunc) {
        this.colorFunc = colorFunc;
    }

    @Override
    public int getHeight() {
        return plane.getHeight();
    }

    @Override
    public void setHeight(int h) {
        plane.setHeight(h);
    }

    @Override
    public int getWidth() {
        return plane.getWidth();
    }

    @Override
    public void setWidth(int w) {
        plane.setWidth(w);
    }

    @Override
    public void paint(@NotNull Graphics g) {
        this.g = g;
        //var bt = System.currentTimeMillis();
        var threadCount = Runtime.getRuntime().availableProcessors();
        var bWidth = getWidth() / threadCount + 1;
        ArrayList<Thread> threads = new ArrayList<>();
        for (int threadNum = 0; threadNum < threadCount; threadNum++) {
            int finalThreadNum = threadNum;
            threads.add(new Thread(() -> {
                var img = new BufferedImage(bWidth, getHeight(), BufferedImage.TYPE_INT_RGB);
                var tGr = img.createGraphics();
                var shift = finalThreadNum * bWidth;
                for (int i = shift; i < shift + bWidth; i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        var x = Converter.INSTANCE.xScrToCrt(i, plane);
                        var y = Converter.INSTANCE.yScrToCrt(j, plane);
                        var r = f.isInSet(new Complex(x, y));
                        var c = colorFunc.getColor(r);
                        tGr.setColor(c);
                        tGr.drawLine(i - shift, j, i - shift + 1, j + 1);
                    }
                }
                g.drawImage(img, shift, 0, null);
            }));
            threads.get(threads.size() - 1).start();
        }
        for (var t : threads){
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        }
        //var et = System.currentTimeMillis();
        //System.out.println(et - bt);
    }

    public BufferedImage getBufferedImage(){
        var img = new BufferedImage(plane.getWidth(), plane.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.paint(img.createGraphics());
        return img;
    }
}
