package year2022.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CampCleanup {

    // Easier to just make a Pair class and use that for the
    // pairs and the range bounds
    // I made it generic for some reason
    private static class Pair<T> {
        private final T first;
        private final T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return String.format("Pair [first=%s, seconds=%s]", first, second);
        }
    }

    public static void main(String[] args) throws IOException {
        // Reading data file
        Path dataFilePath = Paths.get("src/year2022/day4/data.txt");
        List<String> rawLines = Files.readAllLines(dataFilePath);

        // Converting raw lines into pair of numbers
        List<Pair<Pair<Integer>>> pairs = rawLines.stream().map(t -> {
            var sa = t.split(","); // creatively named: string array
            return new Pair<Pair<Integer>>(
                    new Pair<>(
                            Integer.parseInt(sa[0].split("-")[0]),
                            Integer.parseInt(sa[0].split("-")[1])),
                    new Pair<>(
                            Integer.parseInt(sa[1].split("-")[0]),
                            Integer.parseInt(sa[1].split("-")[1])));
        }).toList();

        // Counting the number overlapss
        int fullOverlaps = 0;
        for (var p : pairs) {
            if (rangeOverlaps(p.first, p.second) || rangeOverlaps(p.second, p.first)) {
                fullOverlaps++;
                System.out.println(p);
            }
        }

        // Printing the solution
        System.out.println(fullOverlaps);
    }

    private static boolean rangeOverlaps(Pair<Integer> range1, Pair<Integer> range2) {
        // This is for full overlaps (where range1 contains range2)
        // return range1.first <= range2.first && range1.second >= range2.second;

        return ((range1.first <= range2.first && range1.second >= range2.first) ||
                (range1.first <= range2.second && range1.second >= range2.second));
    }
}
