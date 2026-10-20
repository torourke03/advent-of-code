package Year2025.Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

class Part1 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
        File inFile = new File(filename);
        ArrayList<String> grid = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                grid.add(scnr.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        HashSet<Integer> cols = new HashSet<>();
        cols.add(grid.getFirst().indexOf('S'));
        for (int row = 1; row < grid.size(); row++) {
            HashSet<Integer> removeCols = new HashSet<>();
            for (int col : cols) {
                if (grid.get(row).charAt(col) == '^') {
                    removeCols.add(col);
                    total++;
                }
            }
            cols.removeAll(removeCols);
            for (int removeCol : removeCols) {
                cols.add(removeCol - 1);
                cols.add(removeCol + 1);
            }
        }

        System.out.printf("Total: %d\n", total);
    }
}