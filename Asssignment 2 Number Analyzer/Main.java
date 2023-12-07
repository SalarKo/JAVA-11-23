/*
Assignment 2
Author: Salar Komeyshi

For this assignment we need to make a program that
First, prompts the user to input a number
Secondly, it needs to check if the number is positive or negative and even or odd.
Lastly, depending on the number, it needs to tell the user what their number is.
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//Create the scanner class to accept input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number \n");

        //Create a variable that is a number.
        int number = Integer.parseInt(scanner.nextLine());

        //Create a condition that checks if the number input is positive or negative.
        if(number>0){
            System.out.println("Your number is positive!");
        } else if (number<0) {
            System.out.println("Your number is negative!");
        }

        //Create another condition that checks if the number variable is even or odd.
        if (number % 2 == 0) {
            System.out.println("Your number is even!");
        }else{
            System.out.println("Your number is odd... :/");
        }
    }
}