
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    private int[][] capacity;
    private int[][] flow;
    private boolean[] visited;
    private int[] parent;
    private int numVertices;

    public FordFulkerson(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
        this.flow = new int[numVertices][numVertices];
    }

    public void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
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
        Queue<Integer> queue = new LinkedList<>();
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

    public static void main(String[] args) {
        FordFulkerson ff = new FordFulkerson(6);
        ff.addEdge(0, 1, 16);
        ff.addEdge(0, 2, 13);
        ff.addEdge(1, 2, 10);
        ff.addEdge(1, 3, 12);
        ff.addEdge(2, 1, 4);
        ff.addEdge(2, 4, 14);
        ff.addEdge(3, 2, 9);
        ff.addEdge(3, 5, 20);
        ff.addEdge(4, 3, 7);
        ff.addEdge(4, 5, 4);

        System.out.println("The maximum flow is " + ff.maxFlow(0, 5));
    }
}
