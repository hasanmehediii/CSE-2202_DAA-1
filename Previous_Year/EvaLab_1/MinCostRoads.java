package Previous_Year.EvaLab_1;
// 2nd problem
import java.util.*;

public class MinCostRoads {
    static class Road {
        int from, to, cost;
        Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt(); // number of cities (and roads)
        
        List<Road> roads = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reverseGraph = new ArrayList<>();
        
        for (int i = 0; i < R; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // Read roads and construct adjacency lists for the graph and the reversed graph
        for (int i = 0; i < R; i++) {
            int a = sc.nextInt() - 1; // 0-indexed
            int b = sc.nextInt() - 1; // 0-indexed
            int c = sc.nextInt();
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
            roads.add(new Road(a, b, c));
        }

        // Step 1: Kosaraju's algorithm to find SCCs
        boolean[] visited = new boolean[R];
        Stack<Integer> finishStack = new Stack<>();
        for (int i = 0; i < R; i++) {
            if (!visited[i]) {
                dfs1(i, graph, visited, finishStack);
            }
        }

        // Step 2: Reverse DFS to find SCCs
        Arrays.fill(visited, false);
        int[] sccId = new int[R]; // This will store the SCC ID for each node
        int sccCount = 0;
        
        while (!finishStack.isEmpty()) {
            int node = finishStack.pop();
            if (!visited[node]) {
                sccCount++;
                dfs2(node, reverseGraph, visited, sccId, sccCount);
            }
        }

        // Step 3: Construct the SCC graph (DAG) and find the minimum cost to connect them
        List<List<Road>> sccGraph = new ArrayList<>();
        for (int i = 0; i < sccCount; i++) {
            sccGraph.add(new ArrayList<>());
        }
        
        for (Road road : roads) {
            int u = road.from;
            int v = road.to;
            int cost = road.cost;

            int sccU = sccId[u];
            int sccV = sccId[v];

            if (sccU != sccV) {
                sccGraph.get(sccU).add(new Road(sccU, sccV, cost));
            }
        }

        // Step 4: Find the number of source and sink SCCs
        int[] inDegree = new int[sccCount];
        int[] outDegree = new int[sccCount];
        int totalCost = 0;

        // Calculate in-degrees and out-degrees for each SCC
        for (int i = 0; i < sccCount; i++) {
            for (Road road : sccGraph.get(i)) {
                inDegree[road.to]++;
                outDegree[road.from]++;
            }
        }

        // Step 5: The minimum cost to make the graph strongly connected
        int sources = 0, sinks = 0;
        for (int i = 0; i < sccCount; i++) {
            if (inDegree[i] == 0) sources++;
            if (outDegree[i] == 0) sinks++;
        }

        // The minimum cost is the number of sources or sinks, whichever is larger
        totalCost = Math.max(sources, sinks);

        System.out.println(totalCost);
        sc.close();
    }

    // DFS to calculate the finishing order in the original graph
    private static void dfs1(int node, List<List<Integer>> graph, boolean[] visited, Stack<Integer> finishStack) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, graph, visited, finishStack);
            }
        }
        finishStack.push(node);
    }

    // DFS to assign SCC IDs in the reversed graph
    private static void dfs2(int node, List<List<Integer>> reverseGraph, boolean[] visited, int[] sccId, int sccCount) {
        visited[node] = true;
        sccId[node] = sccCount;
        for (int neighbor : reverseGraph.get(node)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, reverseGraph, visited, sccId, sccCount);
            }
        }
    }
}
