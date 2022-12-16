package ru.smak.graphics;

import java.awt.*;

/**
 * Колорайзер, написанный Маклецовым. По умолчанию фрактал черного цвета.
 * var r = (float)(Math.abs(Math.sin(30*value)));
 * g = (float)(Math.abs(Math.cos(45*value)));
 * b = (float)(1 - 0.5*(Math.abs(Math.sin(12*value)) + Math.abs(Math.cos(28*value))));
 */
public class ColorFunctionGreen implements Colorizer{
    @Override
    public Color getColor(float value) {
        if (value == 1.0) return Color.BLACK;
        var r = (float)(Math.abs(Math.sin(30*value)));
        var g = (float)(Math.abs(Math.cos(45*value)));
        var b = (float)(1 - 0.5*(Math.abs(Math.sin(12*value)) + Math.abs(Math.cos(28*value))));
        return new Color(r, g, b);
    }
}
