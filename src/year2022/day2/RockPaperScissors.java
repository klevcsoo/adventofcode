package year2022.day2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// A very elegant solution all around.
// Unfortunately, I'm no pro in Java, so I don't
// no much trickery like in Typescript.

public class RockPaperScissors {

    /**
     * Rock, Paper, Scissors symbol.
     */
    private static enum RPSSymbol {
        ROCK(1), PAPER(2), SCISSORS(3);

        public final int value;

        private RPSSymbol(int value) {
            this.value = value;
        }
    }

    /**
     * Suggested strategy for a round.
     */
    private static class RoundStrategy {
        // Opponent's choice
        public final RPSSymbol opp;
        // My choice
        public final RPSSymbol me;

        public RoundStrategy(String rawDataString) {
            String[] chars = rawDataString.split(" ");

            // Setting opponent's choice
            switch (chars[0]) {
                case "A":
                    this.opp = RPSSymbol.ROCK;
                    break;
                case "B":
                    this.opp = RPSSymbol.PAPER;
                    break;
                case "C":
                    this.opp = RPSSymbol.SCISSORS;
                    break;
                default:
                    throw new RuntimeException("Invalid character in data file");
            }

            // Setting my choice based on the expected action
            // X: lose, Y: draw, Z: lose
            switch (chars[1]) {
                case "X":
                    switch (this.opp) {
                        case PAPER:
                            this.me = RPSSymbol.ROCK;
                            break;
                        case ROCK:
                            this.me = RPSSymbol.SCISSORS;
                            break;
                        case SCISSORS:
                            this.me = RPSSymbol.PAPER;
                            break;
                        default:
                            throw new RuntimeException();
                    }
                    break;
                case "Y":
                    this.me = this.opp;
                    break;
                case "Z":
                    switch (this.opp) {
                        case PAPER:
                            this.me = RPSSymbol.SCISSORS;
                            break;
                        case ROCK:
                            this.me = RPSSymbol.PAPER;
                            break;
                        case SCISSORS:
                            this.me = RPSSymbol.ROCK;
                            break;
                        default:
                            throw new RuntimeException();
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid character in data file");
            }
        }

        /**
         * Evaluates the current round
         *
         * @return the amount of points I've won
         */
        public int eval() {
            boolean win = (this.me.equals(RPSSymbol.ROCK) && this.opp.equals(RPSSymbol.SCISSORS)) ||
                    (this.me.equals(RPSSymbol.PAPER) && this.opp.equals(RPSSymbol.ROCK)) ||
                    (this.me.equals(RPSSymbol.SCISSORS) && this.opp.equals(RPSSymbol.PAPER));
            int playPoints = this.me.value == this.opp.value ? 3 : win ? 6 : 0;

            return playPoints + this.me.value;
        }

        @Override
        public String toString() {
            return String.format("RoundStrategy [opp=%s, me=%s, eval=%s]", opp, me, eval());
        }
    }

    public static void main(String[] args) throws IOException {
        // Reading input data file
        Path dataFilePath = Paths.get("src/year2022/day2/data.txt");
        Scanner scanner = new Scanner(dataFilePath);

        // Collecting data
        List<RoundStrategy> rounds = new ArrayList<>();
        while (scanner.hasNextLine()) {
            rounds.add(new RoundStrategy(scanner.nextLine()));
        }
        scanner.close();

        // Calculating the amount of points I won each round
        List<Integer> pointsInRounds = rounds.stream().map(t -> {
            return t.eval();
        }).toList();

        // Summing the points up
        int totalPoints = pointsInRounds.stream().reduce((t, u) -> t + u).orElseThrow();

        // Printing the result
        System.out.println(totalPoints);
    }

}
