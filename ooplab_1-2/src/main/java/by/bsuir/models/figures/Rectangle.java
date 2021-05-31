package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Square {
    protected final double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width);
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeRect(x, y, side, height);
    }
}
