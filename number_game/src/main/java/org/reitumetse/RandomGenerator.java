package org.reitumetse;

import com.sun.source.tree.WhileLoopTree;

import java.util.Random;
import java.util.Scanner;

public class RandomGenerator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Random randomNumber = new Random();
        int correctNumber = 1 + randomNumber.nextInt(100);
        // TODO: REMOVE AFTER TESTING

        System.out.println(correctNumber);
        int guessMax = 12;
        boolean correctGuess = false;

        for (int guess = 1; guess<= guessMax; guess++){
            String userAttempt = getInput("Guess the correct number: ");

            try {
                int userGuess = Integer.parseInt(userAttempt);

                if (userGuess == correctNumber){
                    System.out.println("Your guess is correct!");
                    correctGuess = true;
                    break;
                }else {
                    System.out.println("Your guess is incorrect, please try again.");
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter an integer.");
            }
        }
        if (!correctGuess){
            System.out.println("Sorry, you've reached your maximum guesses.\nThe correct guess was " + correctNumber + ".");
        }
    }

    private static String getInput(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()){
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}