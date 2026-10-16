package Year2025.Day04;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

class Part2 {
    private static HashSet<Point> removeRolls(ArrayList<char[]> grid) {
        HashSet<Point> removes = new HashSet<>();
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.getFirst().length; y++) {
                if (grid.get(x)[y] == '.') continue;
                int count = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (x + i < 0 || x + i >= grid.size() || y + j < 0 || y + j >= grid.getFirst().length) continue;
                        if (i == 0 && j == 0) continue;
                        if (grid.get(x + i)[y + j] == '@') count++;
                    }
                }
                if (count < 4) removes.add(new Point(x, y));
            }
        }
        for (Point p : removes) grid.get(p.x)[p.y] = '.';

        return removes;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
        File inFile = new File(filename);
        ArrayList<char[]> grid = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                grid.add(scnr.nextLine().toCharArray());
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        HashSet<Point> removedRolls = new HashSet<>();
        while (true) {
            HashSet<Point> rolls = removeRolls(grid);
            if (rolls.isEmpty()) break;
            removedRolls.addAll(rolls);
        }
        total = removedRolls.size();

        System.out.printf("Total: %d\n", total);
    }
}