package Year2025.Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Part2 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
        File inFile = new File(filename);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.charAt(0) == '+' || line.charAt(0) == '*') {
                    for (String s : line.split("\\s+")) ops.add(s.charAt(0));
                }
                else {
                    lines.add(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        int numsIndex = 0;
        for (char op : ops) {
            long count = 0;
            if (op == '*') count = 1;
            while (numsIndex < lines.getFirst().length()) {
                StringBuilder strNumBuilder = new StringBuilder();
                for (String line : lines) {
                    strNumBuilder.append(line.charAt(numsIndex));
                }
                String strNum = strNumBuilder.toString().trim();
                numsIndex++;
                if (strNum.isEmpty()) break;
                if (op == '+') count += Long.parseLong(strNum);
                else count *= Long.parseLong(strNum);
            }
            total += count;
        }

        System.out.printf("Total: %d\n", total);
    }
}
