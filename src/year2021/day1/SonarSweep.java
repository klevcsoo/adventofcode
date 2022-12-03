package year2021.day1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SonarSweep {

    public static void main(String[] args) throws IOException {
        // Reading input data file
        Scanner scanner = new Scanner(Paths.get("src/year2021/day1/data.txt"));
        List<Integer> depthList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            depthList.add(Integer.parseInt(scanner.nextLine()));
        }
        scanner.close();

        // Summing the scan windows
        List<Integer> scanWindowList = new ArrayList<>();
        for (int i = 0; i < depthList.size(); i++) {
            int a = depthList.get(i);
            int b = i + 1 >= depthList.size() ? 0 : depthList.get(i + 1);
            int c = i + 2 >= depthList.size() ? 0 : depthList.get(i + 2);

            scanWindowList.add(a + b + c);
        }

        // Counting increases
        int increases = 0;
        for (int i = 0; i < scanWindowList.size(); i++) {
            if (i != 0 && scanWindowList.get(i) > scanWindowList.get(i - 1)) {
                increases++;
            }
        }

        // Printing the solution out
        System.out.println(increases);
    }

}
