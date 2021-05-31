package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure {
    private final double x1, y1;
    private final double x2, y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokeLine(x1, y1, x2, y2);
    }
}
