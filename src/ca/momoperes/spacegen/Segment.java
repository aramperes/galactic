package ca.momoperes.spacegen;

public class Segment {

    private Position p1, p2;
    private LineRule rule;

    public Segment(Position p1, Position p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.rule = new LineRule(this);
    }

    public Position getP1() {
        return p1;
    }

    public Position getP2() {
        return p2;
    }

    public LineRule getRule() {
        return rule;
    }

    public boolean crosses(Segment segment) {
        Position crossing = segment.getRule().crossing(rule);
        if (crossing == null)
            return false;

        Position furthest = getP1();
        Position earliest = getP2();
        if (getP2().getY() > furthest.getY()) {
            furthest = getP2();
            earliest = getP1();
        }
        return !(crossing.getY() < earliest.getY() || crossing.getY() > furthest.getY());
    }
}
