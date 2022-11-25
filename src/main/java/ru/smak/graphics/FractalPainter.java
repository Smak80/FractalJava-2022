package ru.smak.graphics;

import org.jetbrains.annotations.NotNull;
import ru.smak.gui.Painter;
import ru.smak.math.Complex;
import ru.smak.math.fractals.Fractal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FractalPainter implements Painter {

    private final Plane plane;
    private Fractal f;
    private Graphics g;

    private Colorizer colorFunc;

    public FractalPainter(Plane plane, Fractal f, Colorizer colorFunc) {
        this.plane = plane;
        this.f = f;
        this.colorFunc = colorFunc;
    }

    public Colorizer getColorFunc() {
        return colorFunc;
    }
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
        var bt = System.currentTimeMillis();
        var threadCount = Runtime.getRuntime().availableProcessors();
        var pool = Executors.newFixedThreadPool(threadCount);
        var bWidth = getWidth() / threadCount / 20 + 1;
        var taskCount = getWidth() / bWidth + ((getWidth() % bWidth != 0) ? 1 : 0);
        for (int k = 0; k < taskCount; k++) {
            int shift = k * bWidth;
            pool.submit(() -> {
                var img = new BufferedImage(bWidth, getHeight(), BufferedImage.TYPE_INT_RGB);
                var tGr = img.createGraphics();
                for (int i = shift; i < shift + bWidth; i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        var x = Converter.INSTANCE.xScrToCrt(i, plane);
                        var y = Converter.INSTANCE.yScrToCrt(j, plane);
                        var r = f.isInSet(new Complex(x, y));
                        var c = colorFunc.getColor(r);
                        tGr.setColor(c);
                        tGr.drawLine(i - shift, j, i + 1 - shift, j + 1);
                    }
                }
                g.drawImage(img, shift, 0, null);
            });
        }
        pool.shutdown();
        try {
            pool.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var et = System.currentTimeMillis();
        System.out.println(et - bt);
    }
}
