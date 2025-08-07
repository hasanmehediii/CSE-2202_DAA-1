import java.util.*;

public class PrimMST {

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void primMST(List<List<Edge>> graph, int V) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Start with vertex 0
        key[0] = 0;
        parent[0] = -1; // First node is always root of MST

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;

            for (Edge edge : graph.get(u)) {
                if (!mstSet[edge.dest] && edge.weight < key[edge.dest]) {
                    parent[edge.dest] = u;
                    key[edge.dest] = edge.weight;
                }
            }
        }

        printMST(parent, graph, V);
    }

    private static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    private static void printMST(int[] parent, List<List<Edge>> graph, int V) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            for (Edge edge : graph.get(parent[i])) {
                if (edge.dest == i) {
                    System.out.println(parent[i] + " - " + i + "\t" + edge.weight);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(3, 6));
        graph.get(1).add(new Edge(0, 2));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 8));
        graph.get(1).add(new Edge(4, 5));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(4, 7));
        graph.get(3).add(new Edge(0, 6));
        graph.get(3).add(new Edge(1, 8));
        graph.get(4).add(new Edge(1, 5));
        graph.get(4).add(new Edge(2, 7));

        primMST(graph, V);
    }
}
