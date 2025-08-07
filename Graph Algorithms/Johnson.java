import java.util.*;

public class Johnson {

    // Bellman-Ford Algorithm to compute shortest paths from a source vertex
    public static int[] bellmanFord(int[][] graph, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        return dist;
    }

    // Dijkstra's Algorithm to compute shortest paths from a source vertex
    public static int[] dijkstra(int[][] graph, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            if (d > dist[u]) continue;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }

    // Johnson's Algorithm
    public static void johnson(int[][] graph, int V) {
        int[][] newGraph = new int[V + 1][V + 1];
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, V);
        }
        Arrays.fill(newGraph[V], Integer.MAX_VALUE);
        for (int i = 0; i < V; i++) {
            newGraph[V][i] = 0;
        }

        // Step 1: Run Bellman-Ford from the new vertex V
        int[] h = bellmanFord(newGraph, V + 1, V);

        // Step 2: Reweight the graph's edges
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 0) {
                    graph[i][j] += h[i] - h[j];
                }
            }
        }

        // Step 3: Run Dijkstra's algorithm from each vertex
        for (int i = 0; i < V; i++) {
            int[] dist = dijkstra(graph, V, i);
            System.out.println("Shortest distances from vertex " + i + ": " + Arrays.toString(dist));
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] graph = {
            {0, 2, 4, 0, 0},
            {0, 0, -4, 0, 0},
            {0, 0, 0, 2, 0},
            {0, 0, 0, 0, 4},
            {0, -1, 0, 0, 0}
        };

        johnson(graph, V);
    }
}
