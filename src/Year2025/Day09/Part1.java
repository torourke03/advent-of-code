package Year2025.Day09;

import java.awt.*;
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
        ArrayList<Point> tiles = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String[] splitLine = scnr.nextLine().split(",");
                tiles.add(new Point(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1])));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        for (int i = 0; i < tiles.size() - 1; i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                Point first = tiles.get(i), second = tiles.get(j);
                long area = (long)Math.abs(first.x - second.x + 1) * (long)Math.abs(first.y - second.y + 1);
                total = Math.max(total, area);
            }
        }

        System.out.printf("Total: %d\n", total);
    }
}