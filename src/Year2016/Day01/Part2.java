package Year2016.Day01;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Part2 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
        File inFile = new File(filename);
        String[] directions = new String[] {};
        try (Scanner scnr = new Scanner(inFile)) {
            directions = scnr.nextLine().split(", ");
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        HashSet<Point> visited = new HashSet<>();
        int[] pos = {0, 0};
        int[] dir = {0, 1};
        visited.add(new Point(pos[0], pos[1]));
        outerloop:
        for (String direction : directions) {
            char turn = direction.charAt(0);
            int dist = Integer.parseInt(direction.substring(1));
            if (turn == 'R') {
                if (Math.abs(dir[1]) == 1) {
                    dir[0] = dir[1];
                    dir[1] = 0;
                }
                else {
                    dir[1] = -dir[0];
                    dir[0] = 0;
                }
            }
            else {
                if (Math.abs(dir[0]) == 1) {
                    dir[1] = dir[0];
                    dir[0] = 0;
                }
                else {
                    dir[0] = -dir[1];
                    dir[1] = 0;
                }
            }
            for (int i = 0; i < dist; i++) {
                pos[0] += dir[0];
                pos[1] += dir[1];
                if (visited.contains(new Point(pos[0], pos[1]))) {
                    total = Math.abs(pos[0]) + Math.abs(pos[1]);
                    break outerloop;
                }
                visited.add(new Point(pos[0], pos[1]));
            }
        }

        System.out.printf("Total: %d\n", total);
    }
}
