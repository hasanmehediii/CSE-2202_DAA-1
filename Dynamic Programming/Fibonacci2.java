import java.util.*;

// Fibonacci using Tabulation (Bottom-Up Approach)
public class Fibonacci2 {

    public int fibTab(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Fibonacci2 fibonacci = new Fibonacci2();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println("fibTab: " + fibonacci.fibTab(n));
        sc.close();
    }
}

