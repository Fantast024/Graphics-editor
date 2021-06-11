package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.MalformedURLException;

public class Controller {

    public Canvas canvas;
    public RadioButton pencil;
    public RadioButton brush;
    public ComboBox<String> comboColor;
    GraphicsContext gc;
    double X, Y, x = 580, y = 290;
    private Window primaryStage;
    ToggleGroup radioGroup;
    Color color;

    @FXML
    public void initialize(){
        radioGroup = new ToggleGroup();
        pencil.setToggleGroup(radioGroup);
        brush.setToggleGroup(radioGroup);
        radioGroup.selectToggle(pencil);
        comboColor.getItems().addAll("Зеленый", "Красный", "Черный");
        comboColor.getSelectionModel().selectLast();
    }

    public void toLoad() throws MalformedURLException {
        File file = new File("C:\\Users\\hikse\\Pictures\\Saved Pictures\\Для плейлиста -То что надо-.jpg");
        String fileURL = file.toURI().toURL().toString();
        Image image = new Image(fileURL);
        X = canvas.getWidth()/2 - x/2;
        Y = canvas.getHeight()/2 - y/2;
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, X, Y, x, y);
    }

    public void onSave() {
        WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
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

    public void clearCanvas(){
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void start(MouseEvent me) {
        gc = canvas.getGraphicsContext2D();

        if(comboColor.getValue() == "Черный"){
            color = Color.BLACK;
        }else if(comboColor.getValue() == "Зеленый"){
            color = Color.GREEN;
        }else if(comboColor.getValue() == "Красный"){
            color = Color.RED;
        }

        gc.setFill(color);
        gc.setStroke(color);

        if(radioGroup.getSelectedToggle() == pencil) {
            gc.beginPath();
            gc.moveTo(me.getX(), me.getY());
            gc.stroke();
        }else if(radioGroup.getSelectedToggle() == brush){
            gc.fillOval(me.getX() - 3, me.getY() - 3, 6, 6);
        }
    }

    public void inProcecc(MouseEvent me) {
        gc = canvas.getGraphicsContext2D();

        if(comboColor.getValue() == "Черный"){
            color = Color.BLACK;
        }else if(comboColor.getValue() == "Зеленый"){
            color = Color.GREEN;
        }else if(comboColor.getValue() == "Красный"){
            color = Color.RED;
        }
        gc.setFill(color);
        gc.setStroke(color);

        if(radioGroup.getSelectedToggle() == pencil) {
            gc.lineTo(me.getX(), me.getY());
            gc.stroke();
            gc.closePath();
            gc.beginPath();
            gc.moveTo(me.getX(), me.getY());
        }else if(radioGroup.getSelectedToggle() == brush){
            gc.fillOval(me.getX() - 3, me.getY() - 3, 6, 6);
            gc.fillOval(me.getX() - 3, me.getY() - 3, 6, 6);
            gc.fillOval(me.getX() - 3, me.getY() - 3, 6, 6);
        }
    }

    public void finish(MouseEvent me) {
        gc = canvas.getGraphicsContext2D();

        if(comboColor.getValue() == "Черный"){
            color = Color.BLACK;
        }else if(comboColor.getValue() == "Зеленый"){
            color = Color.GREEN;
        }else if(comboColor.getValue() == "Красный"){
            color = Color.RED;
        }

        gc.setFill(color);
        gc.setStroke(color);

        if(radioGroup.getSelectedToggle() == pencil) {
            gc.lineTo(me.getX(), me.getY());
            gc.stroke();
            gc.closePath();
        }else if(radioGroup.getSelectedToggle() == brush){
            gc.fillOval(me.getX() - 3, me.getY() - 3, 6, 6);
        }
    }
}
