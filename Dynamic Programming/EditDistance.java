public class EditDistance {

    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Min. operations = j (insert all chars of word2)
                } else if (j == 0) {
                    dp[i][j] = i; // Min. operations = i (remove all chars of word1)
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],       // Remove
                                            Math.min(dp[i][j - 1],   // Insert
                                                     dp[i - 1][j - 1])); // Replace
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "kitten";
        String word2 = "sitting";
        System.out.println("Edit distance between \"" + word1 + "\" and \"" + word2 + "\" is " + editDistance(word1, word2)); // Expected: 3
    }
}
