package src;

import java.util.ArrayList;

class Triangle extends Figure {
    ArrayList<Point> points = new ArrayList<>();

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) throw new IllegalArgumentException();
        if (area(a, b, c) <= 0.001) throw new IllegalArgumentException();
        points.add(a);
        points.add(b);
        points.add(c);
    }

    @Override
    public Point centroid() {
        double x = (points.get(0).getX() + points.get(1).getX() + points.get(2).getX()) / 3.0;
        double y = (points.get(0).getY() + points.get(1).getY() + points.get(2).getY()) / 3.0;
        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure.getClass() == getClass()
                && points.containsAll(((Triangle) figure).points);
    }

    private double area(Point alpha, Point beta, Point gamma) {
        var sideA = sideLength(alpha, beta);
        var sideB = sideLength(beta, gamma);
        var sideC = sideLength(gamma, alpha);
        var p = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    private double sideLength(Point alpha, Point beta) {
        return new Segment(alpha, beta).length();
    }

}
