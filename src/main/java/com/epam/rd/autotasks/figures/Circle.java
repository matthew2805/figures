package src.main.java.com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final Point a;
    private final double b;

    public Circle(Point a, double b) {
        if (b <= 0 || a == null) throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
    }

    @Override
    public Point centroid() { return a; }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure.getClass() == getClass()
                && a.equals(((Circle) figure).a)
                && Math.abs(((Circle) figure).b - b) <= 0.001;
    }

}
