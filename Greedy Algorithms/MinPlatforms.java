import java.util.Arrays;

public class MinPlatforms {

    public static int findMinPlatforms(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int n = arr.length;
        int platforms = 1;
        int result = 1;
        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else if (arr[i] > dep[j]) {
                platforms--;
                j++;
            }
            result = Math.max(result, platforms);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Minimum platforms required: " + findMinPlatforms(arr, dep)); // Expected: 3
    }
}
