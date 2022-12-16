package ru.smak.menu;



import ru.smak.dynamic.MaxIterations;
import ru.smak.gui.GraphicsPanel;
import ru.smak.gui.MainWindow;
import ru.smak.gui.UndoRedoManager;

import ru.smak.data.fileChooserOpen;
import ru.smak.data.fileChooserSave;
import ru.smak.graphics.ColorFunctionDark;
import ru.smak.graphics.Plane;
import ru.smak.gui.Data;
import ru.smak.math.fractals.Mandelbrot;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class MainMenu extends JFrame {
    private JMenuBar menuBar;
    private static GraphicsPanel mainPanel;

    private UndoRedoManager undoRedoManager;
    private static Plane PlaneSave;
    private MainWindow window;
    private Mandelbrot MandelbrotSave;
    private ColorFunctionDark ColorSave;
    private InstrumentPanel instrumentPanel;
    public MainMenu(JMenuBar m, MainWindow mainWindow) {
        this.mainPanel = mainWindow.getMainPanel();
        this.undoRedoManager = mainWindow.getUndoRedoManager();

        menuBar = m;
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createHelpMenu());

    }

    public JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem save = new JMenuItem("Сохранить как JSON...");//сохранить как собственный формат файла
        JMenuItem saveAs = new JMenuItem("Сохранить как картинку...");//сохранить как jpg
        JMenuItem open = new JMenuItem("Открыть...");//открыть как собственный формат файла (загрузить из файла)
        file.add(save);
        file.add(saveAs);
        file.add(open);

        fileChooserSave fileChooserSave = new fileChooserSave(mainPanel);// Никитино
        fileChooserOpen fileChooserOpen = new fileChooserOpen();


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Вызов окошка сохранения файла(пока что без формата)
                fileChooserSave.setDataPut(PlaneSave,MandelbrotSave,ColorSave);
                fileChooserSave.SaveFile();
            }
        });
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //сохранение в формате картинки jpg
                BufferedImage bufferedImage = getBufferedImage();
                // тут сохраняем изображение
                try {
                    // создаем диалог сохранения файла
                    JFileChooser jfc = new JFileChooser();
                    // диалог только для jpg-файлов
                    jfc.addChoosableFileFilter(new FileNameExtensionFilter("Изображения", "jpg"));
                    // показываем диалог
                    int retVal = jfc.showSaveDialog(null);
                    // если файл выбран
                    if(retVal==JFileChooser.APPROVE_OPTION) {
                        // получаем данные выбранного файла
                        File f = jfc.getSelectedFile();
                        String test = f.getAbsolutePath();
                        // сохраняем изображение в файл
                        var res = ImageIO.write(bufferedImage, "jpg", new File(test));
                        // если не удалось сохранить выводим предупреждение
                        if(!res)
                            JOptionPane.showMessageDialog(null, "Не удалось сохранить файл");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Не удалось сохранить файл");
                }
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserOpen.WindowOpen(window);
                fileChooserOpen.Panel(mainPanel);
                fileChooserOpen.OpenFile();
                PlaneSave = fileChooserOpen.getPlane();
                MandelbrotSave = fileChooserOpen.getMandelbrot();
            }
        });
        return file;
    }

    public JMenu createEditMenu() {
        JMenu edit = new JMenu("Правка");
        JMenuItem undo = new JMenuItem("Отменить (Ctrl + Z)");
        JMenuItem redo = new JMenuItem("Вернуть (Ctrl + Y)");
        edit.add(undo);
        edit.add(redo);

        undo.addMouseListener(new MouseAdapter() {      //  отмена операции
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                undoRedoManager.undo();
                MaxIterations maxIterations = new MaxIterations(window);
                mainPanel.repaint();
            }
        });
        redo.addMouseListener(new MouseAdapter() {      //  возвращение операции
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                undoRedoManager.redo();
                MaxIterations maxIterations = new MaxIterations(window);
                mainPanel.repaint();
            }
        });
        //повтор операции
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        return edit;
    }

    public JMenu createHelpMenu() {
        JMenu help = new JMenu("О программе");
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String about =
                        "Над программой работали:\n"
                                + "КФУ ИМиМ группа 05-003\n"
                                    + "Антонов Максим - 5-6 задание\n"
                                    + "Депресов Артём - 7 задание\n"
                                    + "Иванова Инна - 1 задание\n"
                                    + "Игнатьев Вадим - 8 задание\n"
                                    + "Кушаева Алиса - 10 задание\n"
                                    + "Кушаева Камила - 10 задание\n"
                                    + "Марданова Альбина - 2 задание\n"
                                    + "Сошников Никита - 3 задание\n"
                                    + "Филиппов Артур - 4 задание\n"
                                    + "Шумихина Анна - 9 задание";
                JOptionPane.showMessageDialog(null, about);
            }
        });
        return help;
    }

    public void setMainPanel(GraphicsPanel mainPanel) // Передача mainPanel(Никита)
    {
        this.mainPanel = mainPanel;
    }
    public void setDataPutMainMenu(Plane PlaneSave, Mandelbrot MandelbrotSave, ColorFunctionDark ColorSave)
    {
        this.PlaneSave = PlaneSave;
        this.MandelbrotSave = MandelbrotSave;
        this.ColorSave = ColorSave;

    }
    public void setWindow(MainWindow window)
    {
        this.window= window;
    }

    private static BufferedImage getBufferedImage() {

        // размеры изображения:
        // как главная панель(оттуда и беру размеры) + область снизу для записи координат
        int width = mainPanel.getWidth();
        int height = mainPanel.getHeight() + 60;
        // создаем пустое изображение
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // из изображения вытаскиваем объект на котором можно рисовать
        Graphics2D g = bufferedImage.createGraphics();
        // задаем цвет
        g.setPaint ( Color.white );
        // заливаем изображение цветом
        g.fillRect ( 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight() );

        // записываем изображение главной панели на изображение,
        // так как мы задали изображение больше в высоту(чем у главной панели)
        // снизу остается пространство для подписей
        Data.panel.print(g);

        // задаем цвет текста
        g.setPaint(Color.black);
        // задаем шрифт
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        // создаем строку для х
        String xStr = "X ∈ [" + PlaneSave.getXMin() + "  ,  " + PlaneSave.getXMax() + "]";
        // указываем координаты
        int x = 20;
        int y = height - 40;
        // рисуем строку на изображении
        g.drawString(xStr, x, y);
        // создаем строку для y
        String yStr = "Y ∈ [" + PlaneSave.getYMin() + "  ,  " + PlaneSave.getYMax() + "]";
        // координата х не меняется поэтому указываем только координату y
        y = height - 20;
        // рисуем строку на изображении
        g.drawString(yStr, x, y);

        // освобождаем обект для рисования
        g.dispose();
        return bufferedImage;
    }
}
