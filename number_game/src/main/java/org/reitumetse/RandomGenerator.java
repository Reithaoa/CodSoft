package org.reitumetse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomGenerator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int guessMax = 3;
        int roundsMax = 10;
        boolean correctGuess = false;
        int rounds = 0; // Current round number
        int winTrack = 0;
        List<Integer> trueCounts = new ArrayList<>();

        // Get the number of rounds from the user
        String userRounds = getInput("How many rounds do you want to play: ");

        // Main loop to play the specified number of rounds
        while (rounds <= roundsMax) {

            Random randomNumber = new Random();
            int correctNumber = 1 + randomNumber.nextInt(100); // Generate a random number between 1 and 100
            int requestedRounds = Integer.parseInt(userRounds);
            roundsMax = requestedRounds; // Update the maximum number of rounds based on user input

            // Inner loop for the user to make guesses
            for (int guess = 1; guess <= guessMax; guess++) {
                String userAttempt = getInput("Guess the correct number: ");

                try {
                    int userGuess = Integer.parseInt(userAttempt);

                    // Check if the user's guess is correct
                    if (userGuess == correctNumber) {
                        System.out.println("Your guess is correct!");
                        correctGuess = true;
                        if (correctGuess) {
                            winTrack++;
                            trueCounts.add(winTrack);
                        }
                        break;
                    } else if (userGuess > (correctNumber + 10)) {
                        System.out.println("Your guess is too high from the correct guess.");
                    } else if (userGuess < (correctNumber - 10)) {
                        System.out.println("Your guess is too low from the correct guess.");
                    } else {
                        System.out.println("Your guess is incorrect");
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


    /**
     * Prompts the user for input and returns the entered string.
     * Ensures the input is not blank.
     *
     * @param prompt The message to display to the user.
     * @return The user's input as a string.
     */
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        // Loop until a non-blank input is provided
        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
