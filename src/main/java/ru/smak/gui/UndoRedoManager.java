package ru.smak.gui;

import kotlin.Pair;
import java.util.Stack;

public class UndoRedoManager {
    private Scaler scaler;
    private int undoRedoPointer = -1;
    private Stack<Pair<Double, Double>> xEdgesStack;
    private Stack<Pair<Double, Double>> yEdgesStack;

    public UndoRedoManager(Scaler scaler) {
        this.scaler = scaler;
        xEdgesStack = new Stack<>();
        yEdgesStack = new Stack<>();
        insertState();
    }

    public void insertState() {
        deleteStatesAfterPointer(undoRedoPointer);
//        if(undoRedoPointer == 99){    // ограничение сохранения до 100 операций
//            xEdgesStack.remove(0);
//            yEdgesStack.remove(0);
//        }else
        undoRedoPointer++;
        xEdgesStack.push(new Pair<>(scaler.getXMin(), scaler.getXMax()));
        yEdgesStack.push(new Pair<>(scaler.getYMin(), scaler.getYMax()));
    }

    private void deleteStatesAfterPointer(int undoRedoPointer) {
        if(xEdgesStack.size() < 1)
            return;
        for(int i = xEdgesStack.size() - 1; i > undoRedoPointer; i--) {
            xEdgesStack.pop();
            yEdgesStack.pop();
        }
    }

    public void undo() {    //  Ctrl + Z
        if(undoRedoPointer < 1)
            return;
        undoRedoPointer--;
        scaler.setScaleBorders(xEdgesStack.get(undoRedoPointer).getFirst(), xEdgesStack.get(undoRedoPointer).getSecond(),
                yEdgesStack.get(undoRedoPointer).getFirst(), yEdgesStack.get(undoRedoPointer).getSecond());
    }

    public void redo() {    //  Ctrl + Y
        if(undoRedoPointer == xEdgesStack.size() - 1)
            return;
        undoRedoPointer++;
        scaler.setScaleBorders(xEdgesStack.get(undoRedoPointer).getFirst(), xEdgesStack.get(undoRedoPointer).getSecond(),
                yEdgesStack.get(undoRedoPointer).getFirst(), yEdgesStack.get(undoRedoPointer).getSecond());
    }
}
