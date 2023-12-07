//Assignment 3
//Author: Salar Komeyshi

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Generate a random number between 0 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(101);

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Start the game by asking the user to guess a number between 0 and 100
        System.out.println("Hello friend! Can u guess what number i am thinking of? HINT: its between 0 and 100");

        // Get the user's guess or input
        int userGuess = scanner.nextInt();

        // Check if the guess is correct
        while (userGuess != randomNumber) {
            if (userGuess < randomNumber) {
                System.out.println("The number is higher!");
            } else {
                System.out.println("The number is lower!");
            }

            // Ask for the users next answer
            userGuess = scanner.nextInt();
        }

        // The guess is correct
        System.out.println("HOORAY!!! YOU DID IT!");
    }
}
