//Author: Salar Komeyshi
import java.util.Random;


public class Main {
    private static final int GRID_SIZE = 10; // The size of the grid.
    private static final double BLOCKED_PERCENTAGE = 0.25; // The percentage of blocked spaces.
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Possible movement directions.
    private static final char BLOCKED = '#'; // Symbol for blocked spaces.
    private static final char OPEN = '□'; // Symbol for open spaces.
    private static char[][] grid = new char[GRID_SIZE][GRID_SIZE]; // Create a 2D grid.

    public static void main(String[] args) {
        makeGrid(); // Create the grid and fill it with blocked spaces.
        printGrid(); // Print the grid.

        if (findPath(0, 0, 9, 9)) { // Find a path
            System.out.println("\nPath from (0,0) to (9,9):");
            printGrid(); // IF FOUND, print the path
        } else {
            System.out.println("\nNo path from (0,0) to (9,9) found."); //If no path was found
        }
    }

    // Initialize the grid withh randomly placed blocked areas.
    private static void makeGrid() {
        int blockedCount = (int) (BLOCKED_PERCENTAGE * GRID_SIZE * GRID_SIZE); // Formula for number of blocked spaces.
        Random random = new Random();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = (random.nextInt(100) < blockedCount) ? BLOCKED : OPEN; // Randomly assign the blocked and open to grid parts.
            }
        }

        grid[0][0] = grid[GRID_SIZE - 1][GRID_SIZE - 1] = OPEN; // Code to make sure our sstart and end are open
    }

    // Utility method to print the grid.
    private static void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " "); // Print each cell
            }
            System.out.println();
        }
    }

    //find a path from (0,0) to (9,9).
    private static boolean findPath(int startX, int startY, int endX, int endY) {
        if (startX == endX && startY == endY) {
            return true; // search the destination
        }

        for (int[] direction : DIRECTIONS) {
            int newX = startX + direction[0];
            int newY = startY + direction[1];

            if (isValid(newX, newY)) {
                grid[newX][newY] = '■'; // Symbol for marked part
                if (findPath(newX, newY, endX, endY)) {
                    return true;
                }
                grid[newX][newY] = OPEN; // Unmark the path if no route
            }
        }

        return false;
    }

    //method to check if a move is valid
    private static boolean isValid(int x, int y) {
        return (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE && grid[x][y] == OPEN);
    }
}
