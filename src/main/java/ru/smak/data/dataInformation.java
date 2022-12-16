package ru.smak.data;

import com.google.gson.Gson;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.math.fractals.MandelbrotX2;

import java.io.FileWriter;
import java.io.PrintWriter;


public class dataInformation {
    private final int MandelbrotXi;
    private final int CurrentColorI;
    private final ColorFunctionDark ColorSave;
    private final Plane PlaneSave;
    private final Mandelbrot MandelbrotSave;
    private  final boolean MaxIterations;


    public dataInformation(Plane plane, Mandelbrot m, ColorFunctionDark color, int MandelbrotXi, int CurrentColorI,boolean MaxIterations) {
        this.PlaneSave = plane;
        this.MandelbrotSave = m;
        this.ColorSave = color;
        this.MandelbrotXi = MandelbrotXi;
        this.CurrentColorI = CurrentColorI;
        this.MaxIterations = MaxIterations;

    }

    public Plane getPlaneSave() {
        return PlaneSave;
    }

    public Mandelbrot getPMandelbrotX2Save() {
        return MandelbrotSave;
    }

    public ColorFunctionDark getColorDarkSave() {
        return ColorSave;
    }
    public Integer getPMandelbrotXiSave() {
        return MandelbrotXi;
    }
    public Integer getCCurrentColorISave() {
        return CurrentColorI;
    }
}
