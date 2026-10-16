package Year2025.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Part1 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
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
            int firstDigit = 0, secondDigit = 0;
            int firstIndex = 0, secondIndex = 0;
            for (int i = 0; i < battery.size() - 1; i++) {
                if (battery.get(i) > firstDigit) {
                    firstDigit = battery.get(i);
                    firstIndex = i;
                }
            }
            for (int i = firstIndex + 1; i < battery.size(); i++) {
                if (battery.get(i) > secondDigit) {
                    secondDigit = battery.get(i);
                    secondIndex = i;
                }
            }
            total += (10 * firstDigit) + secondDigit;
        }

        System.out.printf("Total: %d\n", total);
    }
}