package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Figure {
    protected final double x, y;
    protected final double side;

    public Square(double x, double y, double side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeRect(x, y, side, side);
    }
}
