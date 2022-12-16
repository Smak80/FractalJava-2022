package ru.smak.graphics;

public enum Colorizers {
    Dark("Темный"),
    Green("Зеленый"),
    Red("Красный");
    private final String color;
    Colorizers(String color){
        this.color = color;
    }
    @Override
    public String toString() {
        return color;
    }
}
