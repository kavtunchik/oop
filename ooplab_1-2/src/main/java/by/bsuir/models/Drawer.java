package by.bsuir.models;

import by.bsuir.models.figures.Figure;
import by.bsuir.models.figures.Point;

import java.util.function.Function;

public final class Drawer {
    private final int steps;
    private final Function<Point[], Figure> function;
    private final Point[] points;
    private int step;

    public Drawer(int steps, Function<Point[], Figure> function) {
        this.steps = steps;
        this.function = function;
        points = new Point[steps];
    }

    public boolean next(double x, double y) {
        points[step++] = new Point(x, y);
        return step == steps;
    }

    public void reset() {
        step = 0;
    }

    public Figure get() {
        return function.apply(points);
    }
}
