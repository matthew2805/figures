package src;

class Segment {
    Point start, end;

    public Segment(Point start, Point end) {
        if (start.equals(end)) throw new IllegalArgumentException();
        else {
            this.start = start;
            this.end = end;
        }
    }

    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) +
                Math.pow(end.getY() - start.getY(), 2));
    }

    public Point intersection(Segment another) {
        var dx1 = end.getX() - start.getX();
        var dy1 = end.getY() - start.getY();
        var dx2 = another.end.getX() - another.start.getX();
        var dy2 = another.end.getY() - another.start.getY();
        var dxx = start.getX() - another.start.getX();
        var dyy = start.getY() - another.start.getY();

        var div = dy2 * dx1 - dx2 * dy1;
        if (Math.abs(div) <= 0.001) return null;

        var a = (dx1 * dyy - dy1 * dxx) / div;
        if (a < 0 || a > 1.0) return null;

        var b = (dx2 * dyy - dy2 * dxx) / div;
        if (b < 0 || b > 1.0) return null;

        return new Point(start.getX() + b * dx1, start.getY() + b * dy1);
    }
}

