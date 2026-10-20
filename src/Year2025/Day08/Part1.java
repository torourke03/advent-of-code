package Year2025.Day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


class Part1 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 1;
        File inFile = new File(filename);
        ArrayList<Point3D> boxes = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String[] splitLine = scnr.nextLine().split(",");
                boxes.add(new Point3D(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }
        ArrayList<Pair<Double, Pair<Integer, Integer>>> dists = new ArrayList<>();
        for (int i = 0; i < boxes.size() - 1; i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                dists.add(new Pair<>(boxes.get(i).dist(boxes.get(j)), new Pair<>(i, j)));
            }
        }
        dists.sort(Comparator.comparing(Pair::getKey));

        HashSet<HashSet<Integer>> circuits = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Pair<Double, Pair<Integer, Integer>> dist = dists.get(i);
            int first = dist.getValue().getKey(), second = dist.getValue().getValue();
            HashSet<Integer> firstCircuit = null, secondCircuit = null;
            for (HashSet<Integer> circuit : circuits) {
                if (circuit.contains(first)) firstCircuit = circuit;
                if (circuit.contains(second)) secondCircuit = circuit;
            }

            HashSet<Integer> newCircuit = new HashSet<>();
            if (firstCircuit == null && secondCircuit == null) {
                newCircuit.add(first);
                newCircuit.add(second);
                circuits.add(newCircuit);
            }
            else if (firstCircuit != null && secondCircuit == null) {
                newCircuit.addAll(firstCircuit);
                newCircuit.add(second);
                circuits.remove(firstCircuit);
                circuits.add(newCircuit);
            }
            else if (firstCircuit == null && secondCircuit != null) {
                newCircuit.addAll(secondCircuit);
                newCircuit.add(first);
                circuits.remove(secondCircuit);
                circuits.add(newCircuit);
            }
            else if (!firstCircuit.equals(secondCircuit)) { // Merge both circuits, they must both be non-null
                newCircuit.addAll(firstCircuit);
                newCircuit.addAll(secondCircuit);
                circuits.remove(firstCircuit);
                circuits.remove(secondCircuit);
                circuits.add(newCircuit);
            }
            // If firstCircuit == secondCircuit, do nothing
        }

        for (int i = 0; i < 3; i++) {
            int maxSize = 0;
            HashSet<Integer> maxCircuit = null;
            for (HashSet<Integer> circuit : circuits) {
                if (circuit.size() > maxSize) {
                    maxSize = circuit.size();
                    maxCircuit = circuit;
                }
            }
            circuits.remove(maxCircuit);
            total *= maxSize;
        }

        System.out.printf("Total: %d\n", total);
    }
}