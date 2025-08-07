public class CoinChangeProblem {

    public static long countWays(int[] coins, int amount) {
        long[] dp = new long[amount + 1];
        dp[0] = 1; // One way to make 0 amount (by choosing no coins)

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

    public static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 4;
        System.out.println("Number of ways to make change for " + amount + ": " + countWays(coins, amount)); // Expected: 4 (1+1+1+1, 1+1+2, 2+2, 1+3)

        int[] coins2 = {1, 5, 6, 8};
        int amount2 = 11;
        System.out.println("Minimum coins to make change for " + amount2 + ": " + minCoins(coins2, amount2)); // Expected: 2 (5+6)
    }
}
