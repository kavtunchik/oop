package by.bsuir.models.figures;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Figure {
    private final double[] xs;
    private final double[] ys;

    public Triangle(Point point1, Point point2, Point point3) {
        xs = new double[]{point1.getX(), point2.getX(), point3.getX()};
        ys = new double[]{point1.getY(), point2.getY(), point3.getY()};
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.strokePolygon(xs, ys, 3);
    }
}
