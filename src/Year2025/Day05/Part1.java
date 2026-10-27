package Year2025.Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Part1 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
        File inFile = new File(filename);
        ArrayList<long[]> ranges = new ArrayList<>();
        ArrayList<Long> ids = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.isEmpty()) break;
                String[] split = line.split("-");
                ranges.add(new long[] {Long.parseLong(split[0]), Long.parseLong(split[1])});
            }
            while (scnr.hasNextLong()) {
                ids.add(scnr.nextLong());
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        for (long id : ids) {
            boolean isValid = false;
            for (long[] range : ranges) {
                if (id >= range[0] && id <= range[1]) {
                    isValid = true;
                    break;
                }
            }
            if (isValid) total++;
        }

        System.out.printf("Total: %d\n", total);
    }
}