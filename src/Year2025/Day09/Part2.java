package Year2025.Day09;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Part2 {
    public static boolean isValidShape(ArrayList<Point> tiles, Point first, Point second) {
        // If any line between two adjacent red tiles intersects with any of the rectangle's 4 sides, it is invalid
        for (int i = 0; i < tiles.size() - 1; i++) {
            Point firstTile = tiles.get(i), secondTile = tiles.get(i + 1);
            if (firstTile.x == secondTile.x && firstTile.x > Math.min(first.x, second.x) && firstTile.x < Math.max(first.x, second.x)) {
                // Check for intersection
                if ((Math.min(firstTile.y, secondTile.y) < Math.min(first.y, second.y) && Math.max(firstTile.y, secondTile.y) > Math.min(first.y, second.y))
                        || (Math.min(firstTile.y, secondTile.y) < Math.max(first.y, second.y) && Math.max(firstTile.y, secondTile.y) > Math.max(first.y, second.y))) {
                    return false;
                }
                // Check for line within the rectangle
                if (Math.min(firstTile.y, secondTile.y) >= Math.min(first.y, second.y) && Math.max(firstTile.y, secondTile.y) <= Math.max(first.y, second.y)) {
                    return false;
                }
            }
            if (firstTile.y == secondTile.y && firstTile.y > Math.min(first.y, second.y) && firstTile.y < Math.max(first.y, second.y)) {
                // Check for intersection
                if ((Math.min(firstTile.x, secondTile.x) < Math.min(first.x, second.x) && Math.max(firstTile.x, secondTile.x) > Math.min(first.x, second.x))
                        || (Math.min(firstTile.x, secondTile.x) < Math.max(first.x, second.x) && Math.max(firstTile.x, secondTile.x) > Math.max(first.x, second.x))) {
                    return false;
                }
                // Check for line within the rectangle
                if (Math.min(firstTile.x, secondTile.x) >= Math.min(first.x, second.x) && Math.max(firstTile.x, secondTile.x) <= Math.max(first.x, second.x)) {
                    return false;
                }
            }
        }
        // If any red tile is within the bounds of the rectangle, its also invalid
        /*for (Point tile : tiles) {
            if (tile.x > Math.min(first.x, second.x) && tile.x < Math.max(first.x, second.x)
                    && tile.y > Math.min(first.y, second.y) && tile.y < Math.max(first.y, second.y)) {
                return false;
            }
        }*/
        return true;
    }

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
                if (!isValidShape(tiles, first, second)) continue;
                long area = (long)Math.abs(first.x - second.x + 1) * (long)Math.abs(first.y - second.y + 1);
                // System.out.printf("Valid rectangle (%d, %d) - (%d, %d) = %d\n", first.x, first.y, second.x, second.y, area);
                if (area >= total) {
                    total = area;
                }
            }
        }

        System.out.printf("Total: %d\n", total);
    }
}