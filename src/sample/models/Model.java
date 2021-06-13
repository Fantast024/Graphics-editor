package sample.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Model {
    ListView<Point> points = new ListView<Point>();

    public void addPoint(Point point){
        points.getItems().add(point);
    }

    public List<Point> getPoint(){
        return points.getItems();
    }

    public void draw(GraphicsContext gc, Point point){
        if(point.type == "Карандаш"){
            drawPencil(gc, point);
        }else{
            drawBrush(gc, point);
        }
    }

    private void drawPencil(GraphicsContext gc, Point point){
        gc.setFill(point.color);
        gc.setStroke(point.color);
        gc.setLineWidth(2);
        gc.strokeLine(point.x, point.y, point.x, point.y);
    }

    private void drawBrush(GraphicsContext gc, Point point){
        gc.setFill(point.color);
        gc.setStroke(point.color);
        gc.fillOval(point.x - 3, point.y - 3, 6, 6);
        gc.strokeLine(point.x, point.y, point.x, point.y);
    }
}
