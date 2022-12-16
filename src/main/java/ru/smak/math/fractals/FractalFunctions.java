package ru.smak.math.fractals;

public enum FractalFunctions {
    Xin2Power("x^2"),
    Xin3Power("x^3"),
    Xin5Power("x^5"),
    Xin10Power("x^10"),
    Xsin("sin(x)"),
    Xcos("cos(x)");
    private final String function;
    FractalFunctions(String function){
        this.function = function;
    }
    @Override
    public String toString() {
        return function;
    }
}
