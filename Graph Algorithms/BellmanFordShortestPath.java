import java.util.Arrays;

public class BellmanFordShortestPath {

    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void bellmanFord(int V, Edge[] edges, int startNode) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        System.out.println("Shortest distances from source " + startNode + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To node " + i + ": " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        Edge[] edges = new Edge[8];
        edges[0] = new Edge(0, 1, -1);
        edges[1] = new Edge(0, 2, 4);
        edges[2] = new Edge(1, 2, 3);
        edges[3] = new Edge(1, 3, 2);
        edges[4] = new Edge(1, 4, 2);
        edges[5] = new Edge(3, 2, 5);
        edges[6] = new Edge(3, 1, 1);
        edges[7] = new Edge(4, 3, -3);

        bellmanFord(V, edges, 0);
    }
}
