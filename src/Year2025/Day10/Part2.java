package Year2025.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Part2 {
    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
        File inFile = new File(filename);
        ArrayList<Machine> machines = new ArrayList<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String[] splitLine = scnr.nextLine().split(" ");
                ArrayList<Boolean> lightDiagram = new ArrayList<>();
                ArrayList<ArrayList<Integer>> buttonLists = new ArrayList<>();
                ArrayList<Integer> joltageList = new ArrayList<>();
                for (int i = 1; i < splitLine[0].length() - 1; i++) {
                    if (splitLine[0].charAt(i) == '.') lightDiagram.add(false);
                    else lightDiagram.add(true);
                }
                for (int x = 1; x < splitLine.length - 1; x++) {
                    String[] buttons = splitLine[x].substring(1, splitLine[x].length() - 1).split(",");
                    ArrayList<Integer> buttonList = new ArrayList<>();
                    for (String s : buttons) {
                        buttonList.add(Integer.parseInt(s));
                    }
                    buttonLists.add(buttonList);
                }
                String[] joltages = splitLine[splitLine.length - 1].substring(1, splitLine[splitLine.length - 1].length() - 1).split(",");
                for (String s : joltages) {
                    joltageList.add(Integer.parseInt(s));
                }
                machines.add(new Machine(lightDiagram, buttonLists, joltageList));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        for (Machine machine : machines) {
            int minButtons = machine.makeJoltages();
            total += minButtons;
        }

        System.out.printf("Total: %d\n", total);
    }
}