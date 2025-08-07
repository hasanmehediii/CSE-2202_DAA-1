
import java.util.Arrays;

public class MinCostMaxFlow {

    private int[][] capacity;
    private int[][] cost;
    private int[][] flow;
    private int[] dist;
    private int[] parent;
    private int numVertices;

    public MinCostMaxFlow(int numVertices) {
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
        while (spfa(s, t)) {
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

    private boolean spfa(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        boolean[] inQueue = new boolean[numVertices];
        int[] queue = new int[numVertices];
        int head = 0, tail = 0;

        dist[s] = 0;
        queue[tail++] = s;
        inQueue[s] = true;

        while (head != tail) {
            int u = queue[head++];
            if (head == numVertices) {
                head = 0;
            }
            inQueue[u] = false;

            for (int v = 0; v < numVertices; v++) {
                if (capacity[u][v] - flow[u][v] > 0 && dist[v] > dist[u] + cost[u][v]) {
                    dist[v] = dist[u] + cost[u][v];
                    parent[v] = u;
                    if (!inQueue[v]) {
                        queue[tail++] = v;
                        if (tail == numVertices) {
                            tail = 0;
                        }
                        inQueue[v] = true;
                    }
                }
            }
        }
        return dist[t] != Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        MinCostMaxFlow mcmf = new MinCostMaxFlow(4);
        mcmf.addEdge(0, 1, 2, 2);
        mcmf.addEdge(0, 2, 1, 5);
        mcmf.addEdge(1, 2, 1, 2);
        mcmf.addEdge(1, 3, 1, 3);
        mcmf.addEdge(2, 3, 2, 1);

        int[] result = mcmf.minCostMaxFlow(0, 3);
        System.out.println("Max flow: " + result[0] + ", Min cost: " + result[1]);
    }
}
