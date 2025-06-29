import java.util.HashMap;

// Fibonacci using Memoization (Top-Down Approach)
public class Fibonacci1 {

    public int fibMemo(int n, HashMap<Integer, Integer> memo) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        Fibonacci1 fibonacci = new Fibonacci1();
        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println("fibMemo: " + fibonacci.fibMemo(10, memo));
    }
}

