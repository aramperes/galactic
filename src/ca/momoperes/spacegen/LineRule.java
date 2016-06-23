package ca.momoperes.spacegen;

public class LineRule {

    private double a, b;

    public LineRule(Segment segment) {
        a = (segment.getP2().getY() - segment.getP1().getY()) / (segment.getP2().getX() - segment.getP1().getX());
        b = segment.getP1().getY() - (a * segment.getP1().getX());
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public Position crossing(LineRule other) {
        if (a == other.a)
            return null;
        double x = ((other.getB() - b) / (a - other.getA()));
        double y = a * x + b;
        return new Position(x, y);
    }
}
