package year2022.day1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CalorieCounting {
    public static void main(String[] args) throws IOException {
        // Reading the data file
        Path dataFilePath = Paths.get("src/year2022/day1/data.txt");
        Scanner scanner = new Scanner(dataFilePath);

        // Storing data file content in array
        List<String> inputRows = new ArrayList<>();
        while (scanner.hasNextLine()) {
            inputRows.add(scanner.nextLine());
        }
        scanner.close();

        // Storing the elfs' calories in a matrix like this:
        // [ elf: [ food, food, food ], elf: [ food, food, food ] ]
        List<List<Integer>> elfCalorieMatrix = new ArrayList<>();
        int begin = 0;
        for (int i = 0; i < inputRows.size(); i++) {
            if (inputRows.get(i).isEmpty()) {
                var nums = inputRows.subList(begin, i).stream().map(t -> {
                    return Integer.parseInt(t);
                }).toList();
                elfCalorieMatrix.add(nums);
                begin = i + 1;
            }
        }

        // Counting the total of calories for each elf by getting
        // the sum of the individual lists
        //
        // The ArrayList constructor is needed, because the
        // Stream.toList() method gives an ImmutableList back,
        // which cannot be sorted later. So in order to avoid that,
        // I am constructing a new ArrayList from the ImmutableList.
        // Not very performant or memory-friendly, but that's okay.
        List<Integer> elfTotalList = new ArrayList<>(elfCalorieMatrix.stream().map(l -> {
            return l.stream().reduce((t, u) -> t + u).orElse(0);
        }).toList());

        // Sorting list to find top 3 (reverse=descending)
        elfTotalList.sort(Comparator.reverseOrder());

        // Getting top 3
        var top3 = elfTotalList.subList(0, 3);

        // Reducing to a single int
        int max = top3.stream().reduce((t, u) -> t + u).orElseThrow();

        // Printing result
        System.out.println(max);

        // I'm using waay to many loops here, but i think easy
        // readability is the priority here, while performance
        // is irrelevant.
        // That's also why I wrote this is Java not C++ or something.
    }
}
