package src;


import java.util.ArrayList;

class Quadrilateral extends Figure {
    ArrayList<Point> points = new ArrayList<>();

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) throw new IllegalArgumentException();
        if (!isConvex(a, b, c, d)) throw new IllegalArgumentException();
        Point firstIntersectionPoint = new Segment(a, b).intersection(new Segment(c, d));
        Point secondIntersectionPointOne = new Segment(b, c).intersection(new Segment(d, a));
        if (firstIntersectionPoint == null && secondIntersectionPointOne == null) {
            points.add(a);
            points.add(b);
            points.add(c);
            points.add(d);
        } else throw new IllegalArgumentException();
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure.getClass() == getClass()
                && this.points.containsAll(((Quadrilateral) figure).points);
    }

    @Override
    public Point centroid() {
        Point firstStart =
                new Triangle(points.get(0), points.get(1), points.get(2)).centroid();
        Point firstEnd =
                new Triangle(points.get(2), points.get(3), points.get(0)).centroid();

        Point secondStart =
                new Triangle(points.get(1), points.get(2), points.get(3)).centroid();
        Point secondEnd =
                new Triangle(points.get(3), points.get(0), points.get(1)).centroid();

        return new Segment(firstStart, firstEnd).intersection(new Segment(secondStart, secondEnd));
    }

    private boolean isConvex(Point alpha, Point beta, Point gamma, Point delta) {
        var z1 = CrossProduct(delta, alpha, beta);
        var z2 = CrossProduct(delta, beta, gamma);
        var z3 = CrossProduct(alpha, delta, gamma);
        var z4 = CrossProduct(alpha, gamma, beta);
        return z1 * z2 * z3 * z4 > 0;
    }
    private double CrossProduct(Point alpha, Point beta, Point gamma){
        return ((alpha.getX() - beta.getX()) * (gamma.getY() - beta.getY())
                - (alpha.getY() - beta.getY()) * (gamma.getX() - beta.getX()));
    }
}
