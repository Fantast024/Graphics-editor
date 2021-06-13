package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import sample.models.Model;
import sample.models.Point;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.MalformedURLException;

public class Controller {
    public Canvas isCanvas;
    public ChoiceBox<String> chBox;
    public ListView<Point> listOfPoints;
    public ChoiceBox<String> choicePoint;
    double X, Y, x = 590, y = 390;
    private Window primaryStage;
    GraphicsContext gc;
    Color colorPoint;
    Model model;
    Point point;

    ObservableList<String> langs = FXCollections.observableArrayList("Черный","Красный", "Зеленый");
    ObservableList<String> typePoint = FXCollections.observableArrayList("Карандаш","Кисточка");

    @FXML
    void initialize(){
        chBox.getItems().addAll(langs);
        chBox.setValue("Черный");
        choicePoint.getItems().addAll(typePoint);
        choicePoint.setValue("Карандаш");
        model = new Model();
    }

    public void clickButton(){

        gc = isCanvas.getGraphicsContext2D();

            model.draw(gc, listOfPoints.getSelectionModel().getSelectedItem());
    }


    public void clickCanvas(MouseEvent e){
        listOfPoints.getItems().clear();
        if (chBox.getValue() == "Красный"){
            colorPoint = Color.RED;
        }else if (chBox.getValue() == "Черный"){
            colorPoint = Color.BLACK;
        }else if (chBox.getValue() == "Зеленый"){
            colorPoint = Color.GREEN;
        }
        if(choicePoint.getValue() == "Карандаш") {
            point = new Point(colorPoint, e.getX(), e.getY(), "Карандаш");
        }else{
            point = new Point(colorPoint, e.getX(), e.getY(), "Кисточка");
        }
        model.addPoint(point);

        listOfPoints.getItems().addAll(model.getPoint());
        listOfPoints.getSelectionModel().selectFirst();
    }

    public void toLoad() throws MalformedURLException {
        File file = new File("resources\\images\\img.jpg");
        String fileURL = file.toURI().toURL().toString();
        Image image = new Image(fileURL);
        X = isCanvas.getWidth()/2 - x/2;
        Y = isCanvas.getHeight()/2 - y/2;
        gc = isCanvas.getGraphicsContext2D();
        gc.drawImage(image, X, Y, x, y);
    }

    public void onSave() {
        WritableImage writableImage = isCanvas.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите директорию...");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Изображение", "*.png"));
        File file = fileChooser.showSaveDialog(primaryStage);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
