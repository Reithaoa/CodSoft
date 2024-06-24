import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomGenerator {

    private static Scanner scanner = new Scanner(System.in);
    static int correctNumber;

    public static void main(String[] args) {
        RandomGenerator game = new RandomGenerator();
        game.playGame();
    }

    public void playGame() {
        int guessMax = 5;
        boolean correctGuess = false;
        int rounds = 1; // Current round number
        int winTrack = 0;
        List<Integer> trueCounts = new ArrayList<>();



        int roundsMax = 0;

        do {
            // Get the number of rounds from the user
            String userRounds = getInput("How many rounds do you want to play: ");

            try {
                roundsMax = Integer.parseInt(userRounds);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        } while (true);

        // Main loop to play the specified number of rounds
        while (rounds <= roundsMax) {
            correctNumber = generateRandomNumber();
            System.out.println(correctNumber);

            // Inner loop for the user to make guesses
            for (int guess = 1; guess <= guessMax; guess++) {
                String userAttempt = getInput("Guess the correct number: ");

                try {
                    int userGuess = Integer.parseInt(userAttempt);

                    // Check if the user's guess is correct
                    if (checkGuess(userGuess)) {
                        System.out.println("Your guess is correct!");
                        correctGuess = true;
                        if (correctGuess) {
                            winTrack++;
                            trueCounts.add(winTrack);
                        }
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer.");
                }
            }

            // Inform the user if the guess was not correct
            if (!correctGuess) {
                System.out.println("Sorry, you've reached your maximum guesses.\nThe correct guess was " + correctNumber + ".");
            }

            correctGuess = false; // Reset correctGuess for the next round
            rounds++;
        }
        // Print the total number of rounds won
        System.out.println("Rounds won: " + trueCounts.size());
    }

    public static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        // Loop until a non-blank input is provided
        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public int generateRandomNumber() {
        Random randomNumber = new Random();
        return 1 + randomNumber.nextInt(100);
    }

    public boolean checkGuess(int userGuess) {
        if (userGuess == correctNumber) {
            return true;
        } else if (userGuess > (correctNumber + 10)) {
            System.out.println("Your guess is too high from the correct guess.");
        } else if (userGuess < (correctNumber - 10)) {
            System.out.println("Your guess is too low from the correct guess.");
        } else {
            System.out.println("Your guess is incorrect");
        }
        return false;
    }
}
