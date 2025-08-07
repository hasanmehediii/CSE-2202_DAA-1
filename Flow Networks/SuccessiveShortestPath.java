
import java.util.Arrays;

public class SuccessiveShortestPath {

    private int[][] capacity;
    private int[][] cost;
    private int[][] flow;
    private int[] dist;
    private int[] parent;
    private int numVertices;

    public SuccessiveShortestPath(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
        this.cost = new int[numVertices][numVertices];
        this.flow = new int[numVertices][numVertices];
        this.dist = new int[numVertices];
        this.parent = new int[numVertices];
    }

    public void addEdge(int u, int v, int cap, int c) {
        capacity[u][v] = cap;
        cost[u][v] = c;
    }

    public int[] minCostMaxFlow(int s, int t) {
        int maxFlow = 0;
        int minCost = 0;
        while (bellmanFord(s, t)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
            }
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
                minCost += pathFlow * cost[u][v];
            }
            maxFlow += pathFlow;
        }
        return new int[]{maxFlow, minCost};
    }

    private boolean bellmanFord(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[s] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (int u = 0; u < numVertices; u++) {
                for (int v = 0; v < numVertices; v++) {
                    if (capacity[u][v] - flow[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + cost[u][v] < dist[v]) {
                        dist[v] = dist[u] + cost[u][v];
                        parent[v] = u;
                    }
                }
            }
        }
        return dist[t] != Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        SuccessiveShortestPath ssp = new SuccessiveShortestPath(4);
        ssp.addEdge(0, 1, 2, 2);
        ssp.addEdge(0, 2, 1, 5);
        ssp.addEdge(1, 2, 1, 2);
        ssp.addEdge(1, 3, 1, 3);
        ssp.addEdge(2, 3, 2, 1);

        int[] result = ssp.minCostMaxFlow(0, 3);
        System.out.println("Max flow: " + result[0] + ", Min cost: " + result[1]);
    }
}
