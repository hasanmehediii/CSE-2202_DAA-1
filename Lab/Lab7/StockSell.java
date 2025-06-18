import java.util.*;

public class StockSell {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int days_num = sc.nextInt();
        int[] days = new int[days_num];

        for (int i = 0; i < days_num; i++) {
            days[i] = sc.nextInt();
        }

        int min_price = Integer.MAX_VALUE;
        int max_profit = 0;

        for (int i = 0; i < days_num; i++) {
            int current_price = days[i];

            if (current_price < min_price) {
                min_price = current_price;
            }

            int profit = current_price - min_price;
            if (profit > max_profit) {
                max_profit = profit;
            }
        }

        System.out.println(max_profit);
        sc.close();
    }
}
