package Previous_Year.EvaLab_1;
// 3rd Problem
import java.util.*;

public class IslandPerimeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();  // Consume newline

        int[][] grid = new int[r][c];
        for (int i = 0; i < r; i++) {
            String[] line = sc.nextLine().split(",");
            for (int j = 0; j < c; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        int perimeter = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    if (i > 0 && grid[i - 1][j] == 1) perimeter--; // top
                    if (i < r - 1 && grid[i + 1][j] == 1) perimeter--; // bottom
                    if (j > 0 && grid[i][j - 1] == 1) perimeter--; // left
                    if (j < c - 1 && grid[i][j + 1] == 1) perimeter--; // right
                }
            }
        }

        System.out.println(perimeter);
        sc.close();
    }
}

