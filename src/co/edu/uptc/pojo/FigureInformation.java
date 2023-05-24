package co.edu.uptc.pojo;

import java.awt.*;

public class FigureInformation {
    private Rectangle rectangle;
    private int Color;
    public FigureInformation() {

    }

    public FigureInformation(Rectangle rectangle, int color) {
        this.rectangle = rectangle;
        this.Color = color;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getColor() {
        return Color;
    }



    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
