package Year2025.Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Part1 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
        File inFile = new File(filename);
        ArrayList<ArrayList<Long>> lines = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String[] splitLine = scnr.nextLine().trim().split("\\s+");
                if (Character.isDigit(splitLine[0].charAt(0))) {
                    ArrayList<Long> nums = new ArrayList<>();
                    for (String s : splitLine) nums.add(Long.parseLong(s));
                    lines.add(nums);
                }
                else {
                    for (String s : splitLine) ops.add(s.charAt(0));
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        for (int i = 0; i < ops.size(); i++) {
            long count = 0;
            if (ops.get(i) == '*') count = 1;
            for (ArrayList<Long> line : lines) {
                if (ops.get(i) == '+') count += line.get(i);
                else count *= line.get(i);
            }
            total += count;
        }

        System.out.printf("Total: %d\n", total);
    }
}