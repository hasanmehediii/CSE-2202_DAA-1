import java.util.Arrays;

public class CoinChangeGreedy {

    public static void findMinCoins(int[] coins, int amount) {
        Arrays.sort(coins);
        int n = coins.length;

        System.out.print("Coins used to make " + amount + ": ");
        for (int i = n - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                System.out.print(coins[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int amount = 93;
        findMinCoins(coins, amount);

        amount = 49;
        findMinCoins(coins, amount);
    }
}
