package by.bsuir.controllers;

import by.bsuir.models.Drawer;
import by.bsuir.models.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> figureChoiceBox;

    @FXML
    private Pane canvasPane;

    @FXML
    private Canvas canvas;

    private final HashMap<String, Drawer> drawers;

    private Drawer drawer;

    public MainController() {
        drawers = new HashMap<>(Map.ofEntries(
                Map.entry("Circle", new Drawer(2, points -> {
                    double a = points[0].getX() - points[1].getX();
                    double b = points[0].getY() - points[1].getY();
                    double r = Math.sqrt(a * a + b * b);
                    return new Circle(points[0].getX() - r, points[0].getY() - r, 2 * r);
                })),
                Map.entry("Ellipse", new Drawer(2, points -> {
                    double x = Math.min(points[0].getX(), points[1].getX());
                    double y = Math.min(points[0].getY(), points[1].getY());
                    double semiHorizontalAxis = Math.abs(points[0].getX() - points[1].getX());
                    double semiVerticalAxis = Math.abs(points[0].getY() - points[1].getY());
                    return new Ellipse(x, y, semiHorizontalAxis, semiVerticalAxis);
                })),
                Map.entry("Line", new Drawer(2, points -> {
                    double x1 = points[0].getX();
                    double y1 = points[0].getY();
                    double x2 = points[1].getX();
                    double y2 = points[1].getY();
                    return new Line(x1, y1, x2, y2);
                })),
                Map.entry("Rectangle", new Drawer(2, points -> {
                    double width = Math.abs(points[0].getX() - points[1].getX());
                    double height = Math.abs(points[0].getY() - points[1].getY());
                    double x = Math.min(points[0].getX(), points[1].getX());
                    double y = Math.min(points[0].getY(), points[1].getY());
                    return new Rectangle(x, y, width, height);
                })),
                Map.entry("Square", new Drawer(2, points -> {
                    double x = points[0].getX();
                    double y = points[0].getY();
                    double a = Math.abs(x - points[1].getX());
                    double b = Math.abs(y - points[1].getY());
                    double side = Math.min(a, b);
                    if (x > points[1].getX()) {
                        x -= side;
                    }
                    if (y > points[1].getY()) {
                        y -= side;
                    }
                    return new Square(x, y, side);
                })),
                Map.entry("Triangle", new Drawer(3, points -> new Triangle(points[0], points[1], points[2])))
        ));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        figureChoiceBox.getItems().addAll(drawers.keySet());
        canvas.heightProperty().bind(canvasPane.heightProperty());
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.setOnMouseClicked(this::canvasMouseClickedHandler);
    }

    private void canvasMouseClickedHandler(MouseEvent mouseEvent) {
        String figureName = figureChoiceBox.getValue();
        if (figureName != null) {
            // Подготовка UI
            if (!figureChoiceBox.isDisabled()) {
                drawer = drawers.get(figureName);
                figureChoiceBox.setDisable(true);
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }
            // Отрисовка фигуры
            if (drawer.next(mouseEvent.getX(), mouseEvent.getY())) {
                drawer.get().draw(canvas.getGraphicsContext2D());
                drawer.reset();
                // Разблокировка UI
                figureChoiceBox.setDisable(false);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Choose some type of figure!");
            alert.showAndWait();
        }
    }
}
