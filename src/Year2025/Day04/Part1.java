package Year2025.Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Part1 {
    private static boolean canForklift(ArrayList<String> grid, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i < 0 || x + i >= grid.size() || y + j < 0 || y + j >= grid.getFirst().length()) continue;
                if (i == 0 && j == 0) continue;
                if (grid.get(x + i).charAt(y + j) == '@') count++;
            }
        }
        return count < 4;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
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

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.getFirst().length(); j++) {
                if (grid.get(i).charAt(j) == '@' && canForklift(grid, i, j)) {
                    total++;
                }
            }
        }

        System.out.printf("Total: %d\n", total);
    }
}