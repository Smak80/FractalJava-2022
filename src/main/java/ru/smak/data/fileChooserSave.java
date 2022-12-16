package ru.smak.data;

import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.gui.GraphicsPanel;
import ru.smak.math.fractals.Mandelbrot;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.JFileChooser.SAVE_DIALOG;

public class fileChooserSave
{
    public static int MandelbrotXi;
    public static int MandelbrotInt;
    public static int CurrentColorI;
    public  static  boolean MaxIterationsSave;
    private Plane PlaneSave;
    ArrayList<String> filenames = new ArrayList<String>();
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    private final JFileChooser FileChooser = new JFileChooser();
    private final GraphicsPanel graphicsPanel;
    private final dataPut dataPut = new dataPut();
    public fileChooserSave(GraphicsPanel graphicsPanel)
    {
        FileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JSON", "JSON");
        FileChooser.setFileFilter(filter);
        this.graphicsPanel = graphicsPanel;
    }

    public void SaveFile()
    {

        int result = FileChooser.showSaveDialog(graphicsPanel);
        File fileSave = FileChooser.getSelectedFile();

        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileSave.getPath();

            dataPut.getPath(path);
            if (MandelbrotInt == 0) {
                dataPut.put(PlaneSave, MandelbrotSave, ColorSave, MandelbrotXi, CurrentColorI, MaxIterationsSave); // Никитино
            } else {
                MandelbrotSave.setMaxIterations(MandelbrotInt);
                dataPut.put(PlaneSave, MandelbrotSave, ColorSave, MandelbrotXi, CurrentColorI, MaxIterationsSave);
            }
        }

        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION )
            JOptionPane.showMessageDialog(graphicsPanel,
                    "Файл (" + FileChooser.getSelectedFile().getName() +".json"+
                            " ) сохранен");

    }
    public void setDataPut(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;
        System.out.println(PlaneSave.getXMax()+"planeSet");
    }
}