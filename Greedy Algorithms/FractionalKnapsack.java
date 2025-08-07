import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    static class Item {
        int weight;
        int value;
        double ratio;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    public static double getMaxValue(int[] weights, int[] values, int capacity) {
        Item[] items = new Item[weights.length];
        for (int i = 0; i < weights.length; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0d;

        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                // Take the whole item
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Take a fraction of the item
                double fraction = (double) capacity / item.weight;
                totalValue += (item.value * fraction);
                capacity = 0;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(weights, values, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}
