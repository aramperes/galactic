package ca.momoperes.spacegen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {


    static List<Branch> systems = new ArrayList<>();
    static Random random = new Random();
    static int generated = 0;
    public static void main(String[] args) {
        Branch main = Branch.create(random, new Position(0, 0), new ArrayList<>(), true, 1);
        systems.add(main);
        Branch[] outer = recursiveGeneration(main, 1);
        for (Branch outerBranch : outer) {
            Collections.addAll(systems, recursiveGeneration(outerBranch, 1000));
        }
        int sectors = 0;
        double minX = 0, maxX = 0;
        for (Branch system : systems) {
            sectors += system.getPoints().length;
            for (Position position : system.getPoints()) {
                System.out.println(position);
                if (position.getX() > maxX) {
                    maxX = position.getX();
                }
                if (position.getX() < minX) {
                    minX = position.getX();
                }
            }
        }
        System.out.println("DONE GENERATION OF " + sectors + " SECTORS IN " + systems.size() + " SYSTEMS");
        System.out.println("SIZE OF UNIVERSE IS " + Math.abs(maxX - minX) + " (" + minX + " to " + maxX + ")");
    }

    public static Branch[] recursiveGeneration(Branch master, int max) {
        int count = 0;
        List<Branch> branchList = new ArrayList<>();
        while (count < max) {
            Branch[] branches = generateBranches(master);
            count++;
            Collections.addAll(branchList, branches);
        }
        return branchList.toArray(new Branch[branchList.size()]);
    }

    public static Branch[] generateBranches(Branch master) {
        List<Branch> subs = new ArrayList<>();
        Position[] points = master.getPoints();
        for (int i = 0; i < points.length; i++) {
            Position position = points[i];
            Branch branch = Branch.create(random, position, systems, false, 10000);
            if (branch == null) {
                branch = Branch.create(random, position, systems, true, 10000);
            }
            generated++;
            subs.add(branch);
        }
        return subs.toArray(new Branch[subs.size()]);
    }

}
