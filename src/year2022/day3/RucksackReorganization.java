package year2022.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class RucksackReorganization {
    public static void main(String[] args) throws IOException {
        // Reading data file (learnt of a new fancy way)
        Path dataFilePath = Paths.get("src/year2022/day3/data.test.txt");
        List<String> sackContentList = Files.readAllLines(dataFilePath);

        // Priority string: used to determine the priority of each character
        // The space at the beginning is there because the priority indexing starts at
        // 1, while the String.indexOf() method starts indexing at 0
        String priorityString = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Summing up the commons
        int sum = 0;
        for (var content : sackContentList) {
            // Getting the first half of the string as an array, in which each
            // element is a character from the string
            List<String> first = Arrays.asList(
                    content.substring(0, content.length() / 2).split(""));
            // Same, but with the second half
            List<String> second = Arrays.asList(
                    content.substring(content.length() / 2).split(""));

            // Looping through the lists, finding the first (and only) common character
            char common = 0;
            for (var c : first) {
                if (second.contains(c)) {
                    common = c.charAt(0);
                    break; // not needed, but makes sense here
                }
            }

            // Using the priority string, to determine the value of the character
            sum += priorityString.indexOf(common);
        }

        // Printing the solution out
        System.out.println(sum);
    }
}
