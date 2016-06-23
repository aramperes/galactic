package ca.momoperes.spacegen;

import java.util.List;
import java.util.Random;

public class Branch {

    private Position[] points;
    private Random random;

    public static Branch create(Random random, Position start, List<Branch> branches, boolean allowCross, int max) {
        int size = random.nextInt(10) + 5;
        Position[] points = new Position[size];
        points[0] = start;

        int cycles = 0;
        loop1:
        for (int i = 1; i < size; i++) {
            cycles++;
            if (cycles > 1000) {
                return null;
            }
            double angle = random.nextDouble() * 180;
            int length = max * 5 + random.nextInt(max * 5);
            double x = length * Math.cos(angle) + start.getX();
            double y = length * Math.sin(angle) + start.getY();
            Position point = new Position(x, y);
            Position previous = points[i - 1];
            Segment segment = new Segment(point, previous);


            if (!allowCross) {
                for (int j = 1; j < i; j++) {
                    Position p1 = points[j - 1];
                    Position p2 = points[j];
                    Segment s = new Segment(p1, p2);
                    if (s.crosses(segment)) {
                        i--;
                        continue loop1;
                    }
                }
                for (Branch branch : branches) {
                    if (branch == null)
                        continue;
                    if (collides(segment, branch)) {
                        i--;
                        continue loop1;
                    }
                }
            }
            points[i] = point;
        }
        return new Branch(points);
    }

    public Branch(Position[] points) {
        this.points = points;
    }

    public static boolean collides(Segment segment, Branch other) {
        for (int i = 1; i < other.getPoints().length; i++) {
            Position otherP1 = other.getPoints()[i - 1];
            Position otherP2 = other.getPoints()[i];
            Segment otherS = new Segment(otherP1, otherP2);
            if (otherS.crosses(segment))
                return true;
        }
        return false;
    }

    public Position[] getPoints() {
        return points;
    }
}
