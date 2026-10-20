package Year2025.Day07;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Pair {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

class Part2 {
    private HashMap<Pair, Long> cache;

    public long countTimelines(ArrayList<String> grid, int row, int col) {
        Pair parameters = new Pair(row, col);
        if (cache.containsKey(parameters)) return cache.get(parameters);

        int currRow = row;
        while (currRow < grid.size() && grid.get(currRow).charAt(col) != '^') {
            currRow++;
        }
        // Base Case: bottom of the grid
        if (currRow >= grid.size()) {
            cache.put(parameters, 1L);
            return 1;
        }

        // Recursive Case: Split into 2 beams (must be at a splitter)
        assert grid.get(currRow).charAt(col) == '^': "Not at splitter";
        long timelines = countTimelines(grid, currRow, col - 1) + countTimelines(grid, currRow, col + 1);
        cache.put(parameters, timelines);
        return timelines;
    }

    public void main(String[] args) {
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

        cache = new HashMap<>();
        int startCol = grid.getFirst().indexOf('S');
        total = countTimelines(grid, 0, startCol);

        System.out.printf("Total: %d\n", total);
    }
}
