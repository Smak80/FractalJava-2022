package ru.smak.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GraphicsPanel extends JPanel {
    ArrayList<Painter> painters= new ArrayList<Painter>();
    public GraphicsPanel()
    {
    }
    public GraphicsPanel(Painter painter)
    {
        this.painters.add(painter);

    }
    public GraphicsPanel(Collection<Painter> painters)
    {
        this.painters.addAll(painters);
    }
    public void addPainter(Painter painter) {
        this.painters.add(painter);
    }
    public void addPainters(Collection<Painter> painters)
    {
        this.painters.addAll(painters);
    }
    public void removeAllPainters()
    {
        painters.clear();
    }
    public ArrayList<Painter> getPainters() {
        return painters;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for (Painter painter : painters) painter.paint(g);
    }

    /**
     * Удаляет все пэинтеры данного типа
     * @param type
     */
    public void removePaintersByType(String type)
    {
        for(int i =0;i<painters.size();i++)
        {
            if(painters.get(i).getClass().toString().equals(type))
                painters.remove(i);
        }
    }

    /**
     * Возвращает количество пэинтеров
     * @return
     */
    public int getCount()
    {
        return painters.size();
    }

    /**
     * Чтобы узнать индекс используйте метод getAllPainters
     * @param index
     */
    public void removePainterByIndex(int index)
    {
        painters.remove(index);
    }

    /**
     *
     * @param type
     * @return Спиосок пэинтеров определенного типа
     */
    public ArrayList<Painter> getAllPainters(String type)
    {
        ArrayList<Painter> a = new ArrayList<>();
        for(int i =0;i<painters.size();i++)
        {
            if(painters.get(i).getClass().toString().equals(type))
                a.add(painters.get(i));
        }
        return a;
    }
}
