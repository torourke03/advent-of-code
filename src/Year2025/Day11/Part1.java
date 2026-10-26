package Year2025.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


class Part1 {
    static int count = 0;
    // Pretty please with cherry on top have no loops (edit: thanks)
    public static int traversePaths(HashMap<String, String[]> devices, String currDevice) {
        count++;
        System.out.println(count);
        // Base case: All connections to "out" have it as the only connection
        if (devices.get(currDevice)[0].equals("out")) {
            assert devices.get(currDevice).length == 1: "The \"out\" connection has multiple connections";
            return 1;
        }

        // Recursive case: Traverse each of the connections
        int pathCount = 0;
        for (String connection : devices.get(currDevice)) {
            pathCount += traversePaths(devices, connection);
        }
        return pathCount;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        int total = 0;
        File inFile = new File(filename);
        HashMap<String, String[]> devices = new HashMap<>();
        try (Scanner scnr = new Scanner(inFile)) {
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                devices.put(line.substring(0, 3), line.substring(5).split(" "));
            }
        } catch (FileNotFoundException e) {
            System.out.printf("File %s not found\n", filename);
            System.exit(1);
        }

        total = traversePaths(devices, "you");

        System.out.printf("Total: %d\n", total);
    }
}