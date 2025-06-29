import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {

    class Job {
        int start, finish, profit;

        public Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

    public int jobScheduling(Job[] jobs, int n) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.finish));

        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            int includeProfit = jobs[i].profit;
            int lastNonConflicting = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j].finish <= jobs[i].start) {
                    lastNonConflicting = j;
                    break;
                }
            }

            if (lastNonConflicting != -1) {
                includeProfit += dp[lastNonConflicting];
            }

            dp[i] = Math.max(dp[i - 1], includeProfit);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        JobScheduling jobScheduling = new JobScheduling();
        Job[] jobs = {
            jobScheduling.new Job(1, 3, 50),
            jobScheduling.new Job(3, 5, 20),
            jobScheduling.new Job(6, 19, 100),
            jobScheduling.new Job(2, 100, 200)
        };
        int n = jobs.length;
        System.out.println("Maximum profit: " + jobScheduling.jobScheduling(jobs, n));
    }
}
