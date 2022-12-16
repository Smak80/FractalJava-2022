package ru.smak.gui;

import kotlin.Pair;
import ru.smak.graphics.Converter;
import ru.smak.graphics.Plane;
import ru.smak.math.Complex;

import java.awt.*;

public class Drag {
    public Drag(Plane plane, Point firstDragPoint, Point lastDragPoint) {
        double crtFirstDragPointX = Converter.INSTANCE.xScrToCrt(firstDragPoint.x, plane);
        double crtFirstDragPointY = Converter.INSTANCE.yScrToCrt(firstDragPoint.y, plane);
        double crtLastDragPointX = Converter.INSTANCE.xScrToCrt(lastDragPoint.x, plane);
        double crtLastDragPointY = Converter.INSTANCE.yScrToCrt(lastDragPoint.y, plane);
        Complex firstPoint = new Complex(crtFirstDragPointX, crtFirstDragPointY);
        Complex lastPoint = new Complex(crtLastDragPointX, crtLastDragPointY);
        Complex thirdPoint = new Complex(lastPoint.getRe(), firstPoint.getIm());
        Complex vector1 = new Complex(thirdPoint.getRe() - firstPoint.getRe(), thirdPoint.getIm() - firstPoint.getIm());
        Complex vector2 = new Complex(thirdPoint.getRe() - lastPoint.getRe(), thirdPoint.getIm() - lastPoint.getIm());

        double length1 = vector1.abs();
        double length2 = vector2.abs();

        double dragSpeed = 0.4;

        if (length1 >= 0.0 && length2 >= 0.0) {
            Double xMin = null;
            Double xMax = null;
            Double yMin = null;
            Double yMax = null;
            if (firstPoint.getRe() <= lastPoint.getRe() && firstPoint.getIm() <= lastPoint.getIm()) {
                xMin = (plane.getXMin() - dragSpeed * length1);
                xMax = (plane.getXMax() - dragSpeed * length1);
                yMin = (plane.getYMin() - dragSpeed * length2);
                yMax = (plane.getYMax() - dragSpeed * length2);
            }
            if (firstPoint.getRe() > lastPoint.getRe() && firstPoint.getIm() < lastPoint.getIm()) {
                xMin = (plane.getXMin() + dragSpeed * length1);
                xMax = (plane.getXMax() + dragSpeed * length1);
                yMin = (plane.getYMin() - dragSpeed * length2);
                yMax = (plane.getYMax() - dragSpeed * length2);
            }
            if (firstPoint.getRe() >= lastPoint.getRe() && firstPoint.getIm() >= lastPoint.getIm()) {
                xMin = (plane.getXMin() + dragSpeed * length1);
                xMax = (plane.getXMax() + dragSpeed * length1);
                yMin = (plane.getYMin() + dragSpeed * length2);
                yMax = (plane.getYMax() + dragSpeed * length2);
            }
            if (firstPoint.getRe() < lastPoint.getRe() && firstPoint.getIm() > lastPoint.getIm()) {
                xMin = (plane.getXMin() - dragSpeed * length1);
                xMax = (plane.getXMax() - dragSpeed * length1);
                yMin = (plane.getYMin() + dragSpeed * length2);
                yMax = (plane.getYMax() + dragSpeed * length2);
            }

            plane.setXEdges(new Pair<>(xMin, xMax));
            plane.setYEdges(new Pair<>(yMin, yMax));
        }
    }
}
