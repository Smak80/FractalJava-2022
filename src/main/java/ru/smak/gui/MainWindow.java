package ru.smak.gui;

import kotlin.Pair;
import ru.smak.dynamic.MaxIterations;
import ru.smak.graphics.*;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.math.fractals.MandelbrotX2;
import ru.smak.math.fractals.Mandelbrot;
import ru.smak.menu.InstrumentPanel;
import ru.smak.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private final GraphicsPanel mainPanel = new GraphicsPanel();
    private Plane plane;
    private InstrumentPanel tool;
    private static final int GROW = GroupLayout.DEFAULT_SIZE;
    private static final int SHRINK = GroupLayout.PREFERRED_SIZE;
    private final Dimension minSz = new Dimension(622, 504);

    private Point firstScalePoint = null;
    private Point lastScalePoint = null;
    private Point firstDragPoint = null;
    private Point lastDragPoint = null;
    private int LastButtonPressed;
    private int LastButtonReleased;
    private UndoRedoManager undoRedoManager;    //  "управляющий" методами отмены и повторного выполнения действий
    private Scaler scaler;
    public Plane getPlane() {
        return plane;
    }

    public GraphicsPanel getMainPanel()
    {
        return mainPanel;
    }
    public  void setPlane(Plane plane)
    {
        this.plane = plane;
    }
    public InstrumentPanel getInstrumentPanel(){return tool;}
    public UndoRedoManager getUndoRedoManager(){
        return undoRedoManager;
    }
    public MainWindow(){
        Data.frame = this;
        Data.panel = mainPanel;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        mainPanel.setFocusable(true); // Флаг focusable указывает, что mainPanel может получить фокус

        Mandelbrot m = new MandelbrotX2();

        plane = new Plane(-2., 1., -1., 1., 0, 0);
        scaler = new Scaler(plane);
        var colorFunc = new ColorFunctionDark();
        FractalPainter fp = new FractalPainter(plane, m, colorFunc);

        undoRedoManager = new UndoRedoManager(scaler);

        mainPanel.setBackground(Color.WHITE);

        JMenuBar menuBar = new JMenuBar();

        MainMenu menu = new MainMenu(menuBar, this);


        menu.setMainPanel(mainPanel); // Передача mainPanel в MainMenu
        menu.setDataPutMainMenu(plane,m,colorFunc);
        menu.setWindow(this);

        setJMenuBar(menuBar);
        JToolBar toolBar = new JToolBar();
        tool = new InstrumentPanel(toolBar, this);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                plane.setWidth(mainPanel.getWidth());
                plane.setHeight(mainPanel.getHeight());
                scaler.scale();
                mainPanel.repaint();
            }
        });
        //region Расположение
        GroupLayout gl = new GroupLayout(getContentPane());

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(4)
                                .addComponent(mainPanel,GROW, GROW, GROW)
                        )
                        .addGap(4)
        );

        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(4)
                        .addComponent(mainPanel, GROW, GROW, GROW)
                        .addGap(4)
        );
        setLayout(gl);
        //endregion
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                LastButtonPressed = e.getButton();
                if(LastButtonPressed == 1)
                {
                    firstScalePoint = e.getPoint();
                }
                else if(LastButtonPressed == 3)
                {
                    firstDragPoint = e.getPoint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                LastButtonReleased = e.getButton();
                if(LastButtonReleased == 1 && lastScalePoint != null) {
                    var g = mainPanel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.drawRect(Math.min(firstScalePoint.x, lastScalePoint.x), Math.min(firstScalePoint.y, lastScalePoint.y), Math.abs(lastScalePoint.x - firstScalePoint.x), Math.abs(lastScalePoint.y - firstScalePoint.y));
                    g.setPaintMode();
                    double selectedXMin = Converter.INSTANCE.xScrToCrt(Math.min(firstScalePoint.x, lastScalePoint.x), plane);
                    double selectedXMax = Converter.INSTANCE.xScrToCrt(Math.max(firstScalePoint.x, lastScalePoint.x), plane);
                    double selectedYMax = Converter.INSTANCE.yScrToCrt(Math.min(firstScalePoint.y, lastScalePoint.y), plane);
                    double selectedYMin = Converter.INSTANCE.yScrToCrt(Math.max(firstScalePoint.y, lastScalePoint.y), plane);
                    scaler.setScaleBorders(selectedXMin, selectedXMax, selectedYMin, selectedYMax);
                    undoRedoManager.insertState();
                    lastScalePoint = firstScalePoint = null;
                    MaxIterations maxIterations = new MaxIterations(MainWindow.this);
                    mainPanel.repaint();
                }
                if(LastButtonPressed == 3 && lastDragPoint != null){
                    scaler.setScaleBorders(plane.getXMin(), plane.getXMax(), plane.getYMin(), plane.getYMax());
                    undoRedoManager.insertState();
                    lastDragPoint = firstDragPoint = null;
                }
            }
        });

        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(LastButtonPressed == 1)
                {
                    var g = mainPanel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.setColor(Color.BLACK);
                    if (lastScalePoint !=null) {
                        g.drawRect(Math.min(firstScalePoint.x, lastScalePoint.x), Math.min(firstScalePoint.y, lastScalePoint.y), Math.abs(lastScalePoint.x- firstScalePoint.x), Math.abs(lastScalePoint.y- firstScalePoint.y));
                    }
                    g.drawRect(Math.min(firstScalePoint.x, e.getX()), Math.min(firstScalePoint.y, e.getY()), Math.abs(e.getX()- firstScalePoint.x), Math.abs(e.getY()- firstScalePoint.y));
                    g.setPaintMode();
                    lastScalePoint = e.getPoint();
                }
                if(LastButtonPressed == 3)
                {
                    lastDragPoint = e.getPoint();
                    Drag a =new Drag(plane,firstDragPoint,lastDragPoint);
                    firstDragPoint = new Point(lastDragPoint);
                    mainPanel.repaint();
                }
                if(LastButtonPressed == 2)
                {
                    lastScalePoint = e.getPoint();
                }
            }
        });

        mainPanel.addKeyListener(new KeyAdapter() {     //слушатель для прослушивания событий клавиатуры
            @Override
            public void keyPressed(KeyEvent e){
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
                    undoRedoManager.undo();
                    MaxIterations maxIterations = new MaxIterations(MainWindow.this);
                    mainPanel.repaint();
                }
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y){
                    undoRedoManager.redo();
                    MaxIterations maxIterations = new MaxIterations(MainWindow.this);
                    mainPanel.repaint();
                }
            }
        });
    }

    @Override
    public void setVisible(boolean v){
        super.setVisible(v);
        plane.setWidth(mainPanel.getWidth());
        plane.setHeight(mainPanel.getHeight());
        var g = mainPanel.getGraphics();
        //костыль
        g.setXORMode(Color.WHITE);
        g.drawRect(-1000, -1000, 1, 1);
        g.setPaintMode();
    }

    public void setUndoRedoManager(UndoRedoManager undoRedoManagerOpen)
    {
        this.undoRedoManager = undoRedoManagerOpen;
    }
    public void setScaler(Scaler scaler)
    {
        this.scaler = scaler;
    }


}
