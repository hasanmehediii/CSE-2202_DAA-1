import java.util.*;

public class Note {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        
        int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        
        int noteCount = 0;
        for (int denom : denominations) {
            if (V == 0) break;
            int count = V / denom;
            noteCount += count;
            V -= count * denom;
        }
        
        System.out.println(noteCount);
        sc.close();
    }
}

