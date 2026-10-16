package Year2025.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Part2 {
    private static long calcJoltage(ArrayList<Integer> battery, int length) {
        long joltage = 0;

        int index = -1;
        for (int x = 0; x < length; x++) {
            long val = 0;
            for (int i = index + 1; i < battery.size() - length + x + 1; i++) {
                if (battery.get(i) > val) {
                    index = i;
                    val = (long)battery.get(i);
                }
            }
            joltage += val * Math.powExact((long)10, length - x - 1);
        }

        return joltage;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
        File inFile = new File(filename);
        ArrayList<ArrayList<Integer>> batteries = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                int[] arr = Arrays.stream(scnr.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
                ArrayList<Integer> battery = new ArrayList<>(arr.length);
                for (int i : arr) battery.add(i);
                batteries.add(battery);
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        for (ArrayList<Integer> battery : batteries) {
            total += calcJoltage(battery, 12);
        }

        System.out.printf("Total: %d\n", total);
    }
}