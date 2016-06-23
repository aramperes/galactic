package ca.momoperes.spacegen;

public class Position {

    private double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double distance(Position position) {
        return Math.sqrt(Math.pow(position.getX() - getX(), 2) + Math.pow(position.getY() - getY(), 2));
    }
}
