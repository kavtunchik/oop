package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Figure {
    protected double x, y;
    protected double diameter;

    public Circle(double x, double y, double diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeOval(x, y, diameter, diameter);
    }
}
