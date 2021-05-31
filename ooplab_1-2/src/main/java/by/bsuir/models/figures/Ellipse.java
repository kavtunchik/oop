package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Circle {
    protected final double verticalAxis;

    public Ellipse(double x, double y, double horizontalAxis, double verticalAxis) {
        super(x, y, horizontalAxis);
        this.verticalAxis = verticalAxis;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeOval(x, y,  diameter, verticalAxis);
    }
}
