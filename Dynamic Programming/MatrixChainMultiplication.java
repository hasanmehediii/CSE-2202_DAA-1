public class MatrixChainMultiplication {

    public static int matrixChainOrder(int[] p) {
        int n = p.length;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n]; // To store optimal split points

        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }

        for (int L = 2; L < n; L++) { // L is chain length
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k; // Store split point
                    }
                }
            }
        }
        return m[1][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4}; // Matrix dimensions: 1x2, 2x3, 3x4
        System.out.println("Minimum number of multiplications is " + matrixChainOrder(arr)); // Expected: 18 ( (A(BC)) or ((AB)C) )
    }
}
