import java.util.Arrays;
import java.util.Comparator;

public class JobSequencingProblem {

    static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void printJobScheduling(Job[] arr) {
        int n = arr.length;

        // Sort all jobs according to decreasing order of profit
        Arrays.sort(arr, (a, b) -> b.profit - a.profit);

        boolean[] slot = new boolean[n];
        char[] result = new char[n];

        // Initialize all slots to be free
        for (int i = 0; i < n; i++) {
            slot[i] = false;
        }

        // Iterate through all given jobs
        for (int i = 0; i < n; i++) {
            // Find a free slot for this job (starting from the last possible slot)
            for (int j = Math.min(n - 1, arr[i].deadline - 1); j >= 0; j--) {
                // If this slot is free, assign this job to it
                if (!slot[j]) {
                    result[j] = arr[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }

        // Print the result
        System.out.println("Following is maximum profit sequence of jobs:");
        for (int i = 0; i < n; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[5];
        jobs[0] = new Job('a', 2, 100);
        jobs[1] = new Job('b', 1, 19);
        jobs[2] = new Job('c', 2, 27);
        jobs[3] = new Job('d', 1, 25);
        jobs[4] = new Job('e', 3, 15);

        printJobScheduling(jobs);
    }
}
