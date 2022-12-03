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
        Path dataFilePath = Paths.get("src/year2022/day3/data.txt");
        List<String> sackContentList = Files.readAllLines(dataFilePath);

        // Priority string: used to determine the priority of each character
        // The space at the beginning is there because the priority indexing starts at
        // 1, while the String.indexOf() method starts indexing at 0
        String priorityString = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Summing up the commons
        int sum = 0;
        for (int i = 0; i < sackContentList.size(); i += 3) /* we need every third element */ {
            // Creating a list from the 3 elements and getting the common character
            char common = commonChar(Arrays.asList(new String[] {
                    sackContentList.get(i), sackContentList.get(i + 1), sackContentList.get(i + 2)
            }));

            // Using the priority string to determine the value that need to be added
            sum += priorityString.indexOf(common);
        }

        // Printing the solution out
        System.out.println(sum);
    }

    // Finds the (only, according to the task) common character
    // in a list of strings
    private static char commonChar(List<String> strings) {
        // Converting the list of strings to a character matrix
        List<List<Character>> charMatrix = strings.stream().map(t -> {
            return Arrays.asList(t.split("")).stream().map(u -> {
                // This array is needed for mapping the List<String> to
                // List<Character>
                // It's not necessary to use the Character type, but it helps clear the
                // confusion up a bit in my opinion between strings and chars
                return u.charAt(0);
            }).toList();
        }).toList();

        // Looping through the lists, finding the common character
        for (var character : charMatrix.get(0)) {
            // Using the Collection.stream() method, we are able to test, if
            // a boolean statement is applicable to every element in a list
            if (charMatrix.stream().allMatch(t -> t.contains(character))) {
                return character;
            }
        }

        return 0;
    }
}
