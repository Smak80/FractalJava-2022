package ru.smak.dynamic;

import ru.smak.graphics.Plane;
import ru.smak.gui.MainWindow;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.menu.InstrumentPanel;

public class MaxIterations {
    MainWindow mainWindow;
    private boolean MaxIterationsSave;
    private Integer MaxIterationsSaveInt;
    public MaxIterations(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        InstrumentPanel instrumentPanel = mainWindow.getInstrumentPanel();
        setNewMaxIterations(mainWindow.getPlane(), instrumentPanel.getCurrentFractal(), instrumentPanel.getDynamicStepStatus());
    }
    private double getPlaneShape(Plane plane){
        double xSize = plane.getXMax() - plane.getXMin();
        double ySize = plane.getYMax() - plane.getYMin();
        return xSize*ySize;
    }

    private static int getNewMaxIterations(double shape){
        return (int) (200 + 20 * Math.log(6/shape));
    }

    public void setNewMaxIterations(Plane plane, Mandelbrot m, boolean isSelected){
        if(isSelected){
            m.setMaxIterations(getNewMaxIterations(getPlaneShape(plane)));
        }
        else {
            m.setMaxIterations(200);
        }
        this.MaxIterationsSave = isSelected;
        this.MaxIterationsSaveInt = getNewMaxIterations(getPlaneShape(plane));
    }
    public Boolean getMaxIterationsSave()
    {
        return MaxIterationsSave;
    }
    public Integer getMaxIterationsInt()
    {
        return MaxIterationsSaveInt;
    }
}
