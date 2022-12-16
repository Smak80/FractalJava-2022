package ru.smak.gui;

import kotlin.Pair;
import ru.smak.graphics.Plane;

public class Scaler {
    private Plane plane;
    private double selectedXMin;
    private double selectedXMax;
    private double selectedYMin;
    private double selectedYMax;
    public Scaler(Plane plane){
        this.plane = plane;
        selectedXMin = plane.getXMin();
        selectedXMax = plane.getXMax();
        selectedYMin = plane.getYMin();
        selectedYMax = plane.getYMax();
    }
    public void setScaleBorders(double selectedXMin, double selectedXMax, double selectedYMin, double selectedYMax){
        this.selectedXMin = selectedXMin;
        this.selectedXMax = selectedXMax;
        this.selectedYMin = selectedYMin;
        this.selectedYMax = selectedYMax;
        scale();
    }

    public double getXMin(){
        return selectedXMin;
    }
    public double getXMax(){
        return selectedXMax;
    }
    public double getYMin(){
        return selectedYMin;
    }
    public double getYMax(){
        return selectedYMax;
    }
    public void scale(){
        double dx = selectedXMax - selectedXMin;
        double dy = selectedYMax - selectedYMin;
        double midX = (selectedXMax + selectedXMin) / 2;
        double midY = (selectedYMax + selectedYMin) / 2;
        double w = plane.getWidth();
        double h = plane.getHeight();
        if(dx / dy < w / h){
            plane.setYEdges(new Pair<>(selectedYMin, selectedYMax));
            double xMinNew = midX - dy * w / h / 2;
            double xMaxNew = midX + dy * w / h / 2;
            plane.setXEdges(new Pair<>(xMinNew, xMaxNew));
        }else{
            plane.setXEdges(new Pair<>(selectedXMin, selectedXMax));
            double yMinNew = midY - dx * h / w / 2;
            double yMaxNew = midY + dx * h / w / 2;
            plane.setYEdges(new Pair<>(yMinNew, yMaxNew));
        }
    }
}
