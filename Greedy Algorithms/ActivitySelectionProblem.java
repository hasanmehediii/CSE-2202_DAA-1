import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelectionProblem {

    static class Activity {
        int start;
        int finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public static void printMaxActivities(Activity[] activities) {
        // Sort activities by finish time
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        System.out.println("Following activities are selected:");

        // The first activity always gets selected
        int i = 0;
        System.out.println("(" + activities[i].start + ", " + activities[i].finish + ")");

        // Consider rest of the activities
        for (int j = 1; j < activities.length; j++) {
            // If this activity has start time greater than or equal to the finish
            // time of previously selected activity, then select it
            if (activities[j].start >= activities[i].finish) {
                System.out.println("(" + activities[j].start + ", " + activities[j].finish + ")");
                i = j;
            }
        }
    }

    public static void main(String[] args) {
        Activity[] activities = new Activity[6];
        activities[0] = new Activity(1, 2);
        activities[1] = new Activity(3, 4);
        activities[2] = new Activity(0, 6);
        activities[3] = new Activity(5, 7);
        activities[4] = new Activity(8, 9);
        activities[5] = new Activity(5, 9);

        printMaxActivities(activities);
    }
}
