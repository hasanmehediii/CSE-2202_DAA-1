package Previous_Year.EvaLab_1;
// 1st problem
import java.util.*;

public class MaxCities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int C = sc.nextInt();  // number of cities
        int R = sc.nextInt();  // number of roads
        int K = sc.nextInt();  // fuel left
        int L = sc.nextInt();  // starting city

        List<List<Integer>> roads = new ArrayList<>();
        for (int i = 0; i <= C; i++) {
            roads.add(new ArrayList<>());
        }
        
        // Reading the roads
        for (int i = 0; i < R; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            roads.get(u).add(v);
            roads.get(v).add(u);  // since the roads are bidirectional
        }
        
        // BFS to find reachable cities within K units of fuel
        boolean[] visited = new boolean[C + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(L);
        visited[L] = true;
        int visitedCount = 1;
        int fuelUsed = 0;

        while (!queue.isEmpty() && fuelUsed < K) {
            int size = queue.size();
            fuelUsed++;
            for (int i = 0; i < size; i++) {
                int city = queue.poll();
                for (int neighbor : roads.get(city)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                        visitedCount++;
                    }
                }
            }
        }
        
        System.out.println(visitedCount);
        sc.close();
    }
}
