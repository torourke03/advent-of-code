package Year2025.Day05;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Part2 {
    // Method assumes the ranges do actually overlap, count amount that range2 overlaps range1, since range2 will have already been counted.
    public static long countOverlap(long[] range1, long[] range2) {
        // Full overlap
        if (range2[0] <= range1[0] && range2[1] >= range1[1]) return range1[1] - range1[0] + 1;
        // Range2 is inside range1
        if (range2[0] >= range1[0] && range2[1] <= range1[1]) return range2[1] - range2[0] + 1;

        return Math.min(range1[1], range2[1]) - Math.max(range1[0], range2[0]) + 1;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
        File inFile = new File(filename);
        ArrayList<long[]> ranges = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.isEmpty()) break;
                String[] split = line.split("-");
                ranges.add(new long[] {Long.parseLong(split[0]), Long.parseLong(split[1])});
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        /*for (int i = 0; i < ranges.size(); i++) {
            long[] curr = ranges.get(i);
            long overlap = 0;
            // Check each previous range for overlap and calculate how much overlap there is
            for (int j = 0; j < i; j++) {
                long[] check = ranges.get(j);
                if ((curr[0] >= check[0] && curr[0] <= check[1]) || (curr[1] >= check[0] && curr[1] <= check[1]) ||
                        (check[0] >= curr[0] && check[0] <= curr[1]) || (check[1] >= curr[0] && check[1] <= curr[1])) {
                    overlap += countOverlap(curr, check);
                }
            }
            if (overlap > curr[1] - curr[0] + 1) overlap = curr[1] - curr[0] + 1;
            total += curr[1] - curr[0] + 1 - overlap;
        }*

        /*boolean canCombine = true;
        while (canCombine) {
            canCombine = false;
            ArrayList<long[]> combinedRanges = new ArrayList<>(ranges.size());
            for (int i = 0; i < ranges.size() - 1; i++) {
                for (int j = i + 1; j < ranges.size(); j++) {
                    long[] r1 = ranges.get(i), r2 = ranges.get(j);
                    if ((r1[0] >= r2[0] && r1[0] <= r2[1]) || (r1[1] >= r2[0] && r1[1] <= r2[1]) ||
                            (r2[0] >= r1[0] && r2[0] <= r1[1]) || (r2[1] >= r1[0] && r2[1] <= r1[1])) {
                        combinedRanges.add(combineRanges(r1, r2));
                        canCombine = true;
                    }
                }
            }
            if (canCombine) ranges = combinedRanges;
        }*/
        total = Long.MAX_VALUE;

        System.out.printf("Total: %d\n", total);
    }
}