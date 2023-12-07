/* Program : Mini-Project: Random Pits
Purpose : The purpose of this project is to implement and test a Java program that implements a
single-player game where the player has to reach a goal location while avoiding randomly placed pits.
Author: Kacper Leon Puzniak & Salar Komeyshi
Date: 21/11/2023 */

/* The Walkthrough
            1. Board ✔
            2. Player ✔
            3. Movement ✔
            4. Pits ✔
            5. Win condition: Location ✔
            6. Lose condition: Step on pit ✔
            7. Lose condition: Path finder ✔
*/

import java.util.Random;
import java.util.Scanner;

public class Main {
    //The boards size
    private static final int Board = 20;

    //The players starting position
    private static int playerY = 0;
    private static int playerX = 0;

    //Win location
    private static final int winX = 19;
    private static final int winY = 19;

    //Initialize  pits on the board.
    private static final boolean[][] pits = new boolean[Board][Board];

    //Marked position on board for the pathfinder
    private static boolean[][] marked = new boolean[Board][Board];

    //Possible movement directions for the pathfinder.
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    private static boolean findPath(int startX, int startY, int endX, int endY) {
        /*
        The findPath method searches through the board,
        using the player position as the start, and the win position as the end.
        The method goes through all possible routes and marks all not-pit positions.
        */
        if (startX == endX && startY == endY) {
            // this is a base condition that looks at whether the start = the end
            return true; // yes path
        }

        marked[startX][startY] = true;

        //This code recursively searches for a path from start to end in a grid, marking visited positions in marked
        for (int[] direction : DIRECTIONS) {
            int newX = startX + direction[0];
            int newY = startY + direction[1];


            if (isValid(newX, newY) && !marked[newX][newY]) {
                if (findPath(newX, newY, endX, endY)) {
                    /*
                    This is another condition that looks at and checks whether the different paths are "valid"
                    if they are, it marks them as such.
                     */
                    return true;
                }
            }
        }
        return false;
    }
    private static void makeBoard() { //Initialize the board, player, goal, and draw them
        String[][] boardArray = new String[Board][Board];
        /*
        This is the makeBoard method which initializes the board, the player and the crown.
        Afterward it draws it all out
        */

        //Set up the board with empty squares.
        for (int i = 0; i < Board; i++) {
            for (int j = 0; j < Board; j++) {
                boardArray[i][j] = " ■ ";
            }
        }

        //The player
        boardArray[playerX][playerY] = " \uD83D\uDC64";
        //The crown aka the winning location.
        boardArray[winX][winY] = " \uD83D\uDC51 ";

        //Place pits on the board.
        for (int i = 0; i < Board; i++) {
            for (int j = 0; j < Board; j++) {
                if (pits[i][j]) {
                    boardArray[i][j] = " □ ";
                }
            }
        }

        //Print the board.
        for (int i = 0; i < Board; i++) {
            for (int j = 0; j < Board; j++) {
                System.out.print(boardArray[i][j]);
            }
            System.out.println();
        }
    }

    private static void bradPit(int numPits) { //The pit maker
        /*The bradPit method uses a randomized number between 1-5 to spawn a number of pits onto the grid.
        It initializes the pits, then the x,y positions, and randomizes them also, for each and every pit.
        The "valid" part of the code ensures the pits aren't spawned on other pits, the player, or the win position 19,19
        */
        Random random = new Random();
        //initializing the pits
        for (int i = 0; i < numPits; i++) {
            int x, y;
            boolean valid;
            while (true) {
                x = random.nextInt(Board);
                y = random.nextInt(Board);
                valid = !pits[x][y] && (x != playerX || y != playerY) && (x != winX || y != winY);

                if (valid) {
                    pits[x][y] = true;
                    break;
                }
            }
        }
    }

    private static boolean makeMove(String move) {
        /*The makeMove method uses a switch-case to make it possible for the Player to move around the Board.
        It uses NORTH, SOUTH, EAST and WEST as directions.
        It also by default checks whether a wrong input has been entered and makes the user re-input it
        */
        //The movement switch-case:
        switch (move.toUpperCase()) {
            case "N":
                if (playerX > 0) {
                    playerX--;
                    return true;
                }
                break;
            case "S":
                if (playerX < Board - 1) {
                    playerX++;
                    return true;
                }
                break;
            case "E":
                if (playerY < Board - 1) {
                    playerY++;
                    return true;
                }
                break;
            case "W":
                if (playerY > 0) {
                    playerY--;
                    return true;
                }
                break;
            default:
                System.out.println("Invalid move. Try again.");
                return false;
        }
        return false;
    }

    //method to check if move is valid. It looks at whether the checker is checking positions on the grid.
    private static boolean isValid(int x, int y) {
        return (x >= 0 && x < Board && y >= 0 && y < Board && !pits[x][y]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //printing the board.
            makeBoard();


            System.out.print("Make your move, if you dare >:)");
            //prompt the user to make a move
            System.out.print("(N-S-E-W)\n");
            String move = scanner.nextLine();

            // Clear the marked array that is used to keep track of the possible paths to victory
            marked = new boolean[Board][Board];

            // If invalid move, ask for input again //Actually the code that makes the Player move :
            if (!makeMove(move)) {
                continue;
            }

            //Randomize the number of pits and call the pit-maker "bradPit"
            Random random = new Random();
            bradPit(random.nextInt(5) + 1);

            //Win condition!
            if (playerX == winX && playerY == winY) {
                System.out.println("Congratulations! You reached the goal!");
                break;
            }

            //Lose condition #1
            if (pits[playerX][playerY]) {
                System.out.println("You stepped on a pit! Game Over!");
                break;
            }

            //Lose condition #2
            if (!findPath(playerX, playerY, winX, winY)) {
                makeBoard();
                System.out.println("No path found! Game Over!");
                break;
            }
        }
    }
}
