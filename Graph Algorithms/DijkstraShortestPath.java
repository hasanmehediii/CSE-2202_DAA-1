import java.util.*;

public class DijkstraShortestPath {

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int V, int startNode) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(startNode, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;

            for (Edge edge : graph.get(u)) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[u] + edge.weight;
                    pq.add(new Edge(edge.dest, dist[edge.dest]));
                }
            }
        }

        System.out.println("Shortest distances from source " + startNode + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To node " + i + ": " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 5));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(4, 2));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(2, 6));
        graph.get(4).add(new Edge(1, 3));
        graph.get(4).add(new Edge(2, 9));
        graph.get(4).add(new Edge(3, 2));

        dijkstra(graph, V, 0);
    }
}
