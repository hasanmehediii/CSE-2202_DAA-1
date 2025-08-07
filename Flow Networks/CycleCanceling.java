
import java.util.Arrays;

public class CycleCanceling {

    private int[][] capacity;
    private int[][] cost;
    private int[][] flow;
    private int[] dist;
    private int[] parent;
    private int numVertices;

    public CycleCanceling(int numVertices) {
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

        // Find max flow
        FordFulkerson ff = new FordFulkerson(numVertices, capacity);
        maxFlow = ff.maxFlow(s, t);
        flow = ff.getFlow();

        // Cancel negative cycles
        while (findNegativeCycle()) {
            int pathFlow = Integer.MAX_VALUE;
            int u, v;

            // Find a vertex in the negative cycle
            boolean[] visited = new boolean[numVertices];
            int cycleStart = -1;
            for (int i = 0; i < numVertices; i++) {
                int curr = i;
                for (int j = 0; j < numVertices; j++) {
                    curr = parent[curr];
                    if (curr == -1) break;
                }
                int check = curr;
                while (check != -1 && !visited[check]) {
                    visited[check] = true;
                    check = parent[check];
                }
                if (check != -1 && check != -1) {
                    cycleStart = check;
                    break;
                }
            }

            if (cycleStart == -1) break; // No cycle found, should not happen

            v = cycleStart;
            do {
                u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
                v = u;
            } while (v != cycleStart);

            v = cycleStart;
            do {
                u = parent[v];
                flow[u][v] += pathFlow;
                flow[v][u] -= pathFlow;
                minCost += pathFlow * cost[u][v];
                v = u;
            } while (v != cycleStart);
        }

        return new int[]{maxFlow, minCost};
    }

    private boolean findNegativeCycle() {
        Arrays.fill(dist, 0);
        Arrays.fill(parent, -1);

        for (int i = 0; i < numVertices; i++) {
            for (int u = 0; u < numVertices; u++) {
                for (int v = 0; v < numVertices; v++) {
                    if (capacity[u][v] - flow[u][v] > 0 && dist[u] + cost[u][v] < dist[v]) {
                        dist[v] = dist[u] + cost[u][v];
                        parent[v] = u;
                        if (i == numVertices - 1) {
                            return true; // Negative cycle found
                        }
                    }
                }
            }
        }
        return false;
    }

    // Inner class for Ford-Fulkerson
    private static class FordFulkerson {
        private int[][] capacity;
        private int[][] flow;
        private boolean[] visited;
        private int[] parent;
        private int numVertices;

        public FordFulkerson(int numVertices, int[][] capacity) {
            this.numVertices = numVertices;
            this.capacity = capacity;
            this.flow = new int[numVertices][numVertices];
        }

        public int maxFlow(int s, int t) {
            int maxFlow = 0;
            while (hasAugmentingPath(s, t)) {
                int pathFlow = Integer.MAX_VALUE;
                for (int v = t; v != s; v = parent[v]) {
                    int u = parent[v];
                    pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
                }
                for (int v = t; v != s; v = parent[v]) {
                    int u = parent[v];
                    flow[u][v] += pathFlow;
                    flow[v][u] -= pathFlow;
                }
                maxFlow += pathFlow;
            }
            return maxFlow;
        }

        private boolean hasAugmentingPath(int s, int t) {
            visited = new boolean[numVertices];
            parent = new int[numVertices];
            java.util.Queue<Integer> queue = new java.util.LinkedList<>();
            queue.add(s);
            visited[s] = true;
            parent[s] = -1;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < numVertices; v++) {
                    if (!visited[v] && capacity[u][v] - flow[u][v] > 0) {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            return visited[t];
        }

        public int[][] getFlow() {
            return flow;
        }
    }

    public static void main(String[] args) {
        CycleCanceling cc = new CycleCanceling(4);
        cc.addEdge(0, 1, 2, 2);
        cc.addEdge(0, 2, 1, 5);
        cc.addEdge(1, 2, 1, 2);
        cc.addEdge(1, 3, 1, 3);
        cc.addEdge(2, 3, 2, 1);

        int[] result = cc.minCostMaxFlow(0, 3);
        System.out.println("Max flow: " + result[0] + ", Min cost: " + result[1]);
    }
}
