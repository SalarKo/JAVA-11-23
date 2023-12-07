/* Assigment 1.
 Author: Salar Komeyshi

First, the program needs to be able to receive an input from the user, which will be their name.
Then the program needs to greet the user with their name.

To receive an input for the user, one can use the scanner class as demonstrated in chapter 3.2 page 34 from the Think JAVA textbook
*/

import java.util.Scanner; // was automatically added once i used the Scanner class

public class Main {
    public static void main(String[] args) {
        // Using the Scanner class to be able to receive an input from the user.
        Scanner scanner= new Scanner(System.in);

        // The program prompts the user to enter their name. /n was used just so the users input is on a separate line
        System.out.print("Please enter your name.\n");

        /*
         First, declare a variable called "name" and since a name is made of letter, it will then be a string.
         Then the scanner takes the users name and save in the "name" variable.
        */
        String name = scanner.nextLine();

        // Lastly, the program outputs a greeting with the users name.
        System.out.print("Hello, "+name+ "!");
        }
    }
