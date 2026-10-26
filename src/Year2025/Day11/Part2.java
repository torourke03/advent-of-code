package Year2025.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


class Part2 {
    public static HashMap<Inputs, Long> cache;
    // Pretty please with cherry on top have no loops (edit: thanks)
    public static long traversePaths(HashMap<String, String[]> devices, String currDevice, boolean foundDAC, boolean foundFFT) {
        Inputs in = new Inputs(currDevice, foundDAC, foundFFT);
        if (cache.containsKey(in)) return cache.get(in);

        // Base case: All connections to "out" have it as the only connection
        if (devices.get(currDevice)[0].equals("out")) {
            assert devices.get(currDevice).length == 1: "The \"out\" connection has multiple connections";
            if (foundDAC && foundFFT) {
                cache.put(in, 1L);
                return 1;
            }
            else {
                cache.put(in, 0L);
                return 0;
            }
        }

        // Recursive case: Traverse each of the connections
        long pathCount = 0;
        boolean dac = foundDAC || currDevice.equals("dac"), fft = foundFFT || currDevice.equals("fft");
        for (String connection : devices.get(currDevice)) {
            pathCount += traversePaths(devices, connection, dac, fft);
        }
        cache.put(in, pathCount);
        return pathCount;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        if (args.length == 1) filename = args[0];
        long total = 0;
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

        cache = new HashMap<>();
        total = traversePaths(devices, "svr", false, false);

        System.out.printf("Total: %d\n", total);
    }
}