public class SubsetSum {

    public static boolean isSubsetSum(int[] set, int sum) {
        int n = set.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        // Fill the subset table in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < set[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        if (isSubsetSum(set, sum)) {
            System.out.println("Found a subset with given sum");
        } else {
            System.out.println("No subset with given sum");
        }

        sum = 30;
        if (isSubsetSum(set, sum)) {
            System.out.println("Found a subset with given sum");
        } else {
            System.out.println("No subset with given sum");
        }
    }
}
