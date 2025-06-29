import java.util.Scanner;

public class RockClimbing {

    public int rockClimbing(int[][] wall) {
        int m = wall.length;
        int n = wall[0].length;

        // DP table to store the maximum energy collected at each position
        int[][] dp = new int[m][n];

        // Initialize the bottom row with the energy values of the wall
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = wall[m - 1][i];
        }

        // Fill the DP table from bottom to top
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // Move straight up, left-diagonal, or right-diagonal
                int maxEnergy = dp[i + 1][j]; // Move straight up
                if (j - 1 >= 0) {
                    maxEnergy = Math.max(maxEnergy, dp[i + 1][j - 1]); // Move left-diagonal
                }
                if (j + 1 < n) {
                    maxEnergy = Math.max(maxEnergy, dp[i + 1][j + 1]); // Move right-diagonal
                }
                // Add the current cell's energy value to the maximum energy found
                dp[i][j] = wall[i][j] + maxEnergy;
            }
        }

        // The result will be the maximum energy collected from the top row
        int maxEnergyCollected = dp[0][0];
        for (int i = 1; i < n; i++) {
            maxEnergyCollected = Math.max(maxEnergyCollected, dp[0][i]);
        }

        return maxEnergyCollected;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows (m): ");
        int m = scanner.nextInt();
        System.out.print("Enter the number of columns (n): ");
        int n = scanner.nextInt();

        int[][] wall = new int[m][n];

        System.out.println("Enter the energy values for the wall:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                wall[i][j] = scanner.nextInt();
            }
        }

        RockClimbing rockClimbing = new RockClimbing();
        int result = rockClimbing.rockClimbing(wall);
        System.out.println("Maximum energy collected: " + result);

        scanner.close();
    }
}

