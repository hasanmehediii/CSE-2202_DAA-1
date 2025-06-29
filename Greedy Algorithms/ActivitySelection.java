import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {

    public int activitySelection(int[] start, int[] finish, int n) {
        // Sort activities based on their finish times
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> finish[i]));

        int count = 1;
        int lastSelected = 0;

        for (int i = 1; i < n; i++) {
            if (start[indexes[i]] >= finish[indexes[lastSelected]]) {
                count++;
                lastSelected = i;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] finish = {2, 4, 6, 7, 9, 9};
        int n = start.length;
        ActivitySelection activitySelection = new ActivitySelection();
        System.out.println("Maximum activities selected: " + activitySelection.activitySelection(start, finish, n));
    }
}

