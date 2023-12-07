// Assignment 4
//Author: Salar Komeyshi


import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //the class called "generateRandomCode" will create a random 4 digit code and it gets called "code"
        int[] code = generateRandomCode();
        //Show user the randomly generated code.
        System.out.println("Generated code: " + code[0] + code[1] + code[2] + code[3]);
        //Create an array of integers called "guess"
        int[] guess = new int[4];

        //New variable called "attempt" followed by a while loop that tracks and then prints how many attempts were required.
        int attempts = 0;
        while (!checkCode(guess, code)) {
            generateNextGuess(guess);
            attempts++;
            System.out.println("Attempt " + attempts + ": " + guess[0] + guess[1] + guess[2] + guess[3]);
        }

        System.out.println("Code successfully broken! It took " + attempts + " attempts.");
    }


// A new method generates a random four-digit code.
// It works by creating a new array of integers and then using the Random class to generate a random integer between 0 and 9 for each digit in the code.
    public static int[] generateRandomCode() {
        int[] code = new int[4];
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            code[i] = rand.nextInt(10);
        }
        return code;
    }


//This method generates the next guess for the player.
// It works by starting at the last digit of the guess and increasing it until it reaches 9.
// If the last digit is already 9, it is reset to 0.
// This process continues until all of the digits in the guess have been incremented.
    public static void generateNextGuess(int[] guess) {
        for (int i = 3; i >= 0; i--) {
            if (guess[i] < 9) {
                guess[i]++;
                break;
            } else {
                guess[i] = 0;
            }
        }
    }


// This method checks if the guess is correct.
// It works by comparing each digit in the guess to the corresponding digit in the "code".
// If any of the digits are different, the method returns false.
// Otherwise, the method returns true.
    public static boolean checkCode(int[] guess, int[] code) {
        for (int i = 0; i < 4; i++) {
            if (guess[i] != code[i]) {
                return false;
            }
        }
        return true;
    }
}