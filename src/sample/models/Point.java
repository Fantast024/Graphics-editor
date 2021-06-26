package sample.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Point {
    Color color;
    double x;
    double y;
    String type;

    public Point(Color color, double x, double y, String str){
        this.color = color;
        this.x = x;
        this.y = y;
        this.type = str;
    }
    
    public String getString(){
        String str = type + " " + "Координаты: " + x + " " + y + " " + "Цвет " + color;
        return str;
    }
}
