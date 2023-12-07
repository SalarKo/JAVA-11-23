// Author: Salar Komeyshi
// Hello Petr!
// I tried to do something a little extra this time.
// Also, I tried to shorten my explanations more by explaining the code in groups rather than line by line.
// I hope you will like it.

import java.util.Random;
import java.util.Scanner;

public class Main {
    //define arrays of words categorized by difficulty.
    //'final sstring' variable was used so that the variables wont be changed or reassigned
    private static final String[] easyWords = {"apple", "red", "cherry", "grape", "melon"};
    private static final String[] mediumWords = {"elephant", "applaud", "giraffe", "penguin", "computer"};
    private static final String[] hardWords = {"nauseous", "algorithm", "rhinoceros", "acquiesce", "supercalifragilisticexpialidocious"};

    //Variable to represent the max. amount of wrong guessess
    private static int maxWrongGuesses = 8;
    
    // Variables to represent the word that needs to be guessed and its length
    private static String secretWord = "";
    private static int n; //Var for the length of the secret word
    private static String guessedWord;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //Var to keep track of the number of incorrect guesses.
        int wrongGuesses = 0;

        System.out.println("Welcome to Hangman!");
        
        // Method to allow the user to choose their difficulty.
        int difficulty = chooseDifficulty(scanner);
        
        //Based on the chosen difficulty, a random word is selected from the appropriate word array.
        if (difficulty == 1) {
            secretWord = getRandomWord(easyWords);
        } else if (difficulty == 2) {
            secretWord = getRandomWord(mediumWords);
        } else if (difficulty == 3) {
            secretWord = getRandomWord(hardWords);
        } else {
            System.out.println("Invalid difficulty choice. Exiting.");
            return;
        }
        
        // Here the variable n is set to the length of the secret word and also changed to "." so the user cant see it.
        n = secretWord.length();
        guessedWord = ".".repeat(n); // Initialize guessedWord with underscores

        //while loop that goes on till user makes 8 mistakes. 
        while (wrongGuesses < maxWrongGuesses) {
            //different information output for the user to see
            System.out.println("\nSecret Word: " + guessedWord);
            System.out.println("You have " + (maxWrongGuesses - wrongGuesses) + " wrong guesses left.");
            System.out.print("Guess a letter: ");

            String guess = scanner.next();
            
            // if condition to check for a single letter guess.
            if (guess.length() == 1) {
                //Code to check if the players guessed code mathces with the secret word.
                boolean correctGuess = false;
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess.charAt(0)) {
                        guessedWord = guessedWord.substring(0, i) + guess + guessedWord.substring(i + 1);
                        correctGuess = true;
                    }
                }
                
                //checks if the player has successfully guessed the entire word and if not to continue
                if (guessedWord.equals(secretWord)) {
                    System.out.println("Congratulations! You guessed the word: " + secretWord);
                    break; // Break out of the loop if the word is fully revealed
                } else if (!correctGuess) {
                    wrongGuesses++;
                    System.out.println("Wrong guess. Try again.");
                }
            }
        }


        //Checks if the player has used all their guessess and lost.
        if (!guessedWord.equals(secretWord) && wrongGuesses >= maxWrongGuesses) {
            System.out.println("Game over! You've used all your wrong guesses. The word was: " + secretWord);
        }
        scanner.close();
    }

    //This private method is used to allow the player to select the difficulty level for the game.
    private static int chooseDifficulty(Scanner scanner) {
        System.out.println("Choose a difficulty level:");
        System.out.println("1: Easy");
        System.out.println("2: Medium");
        System.out.println("3: Hard");
        
        //Int var is declared to store the player's choice
        int difficulty;
        
        //a do/while loop to ask player to make a choice. The number has to be between 1 and 3
        do {
            System.out.print("Enter your choice: ");
            difficulty = scanner.nextInt();
        } while (difficulty < 1 || difficulty > 3);
        return difficulty;
    }
    //This method selects a random word from the wordList array and returns it.
    private static String getRandomWord(String[] wordList) {
        Random random = new Random();
        return wordList[random.nextInt(wordList.length)];
    }
}
