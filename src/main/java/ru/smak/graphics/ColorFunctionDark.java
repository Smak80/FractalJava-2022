package ru.smak.graphics;

import java.awt.*;

public class ColorFunctionDark implements Colorizer{
    @Override
    public Color getColor(float value) {
        if (value == 1.0) return new Color((float)0.08,(float)0.10,(float)0.09);
        var r = (float)(Math.abs(Math.sin(value * 3)));
        var g = (float)(Math.abs(Math.sin(value * 7)));
        var b = (float)(Math.abs(Math.sin(value * 9)));
        return new Color(r, g, b);
    }
}
