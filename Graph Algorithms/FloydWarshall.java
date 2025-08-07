import java.util.*;

public class FloydWarshall {
    public static void floydWarshall(int[][] graph, int V) {
        // Step 1: Initialize the distance matrix
        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 0 && i != j) {
                    dist[i][j] = Integer.MAX_VALUE; // No path
                } else {
                    dist[i][j] = graph[i][j];
                }
            }
        }

        // Step 2: Floyd-Warshall Algorithm
        for (int k = 0; k < V; k++) { // intermediate vertex
            for (int i = 0; i < V; i++) { // source vertex
                for (int j = 0; j < V; j++) { // destination vertex
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative weight cycle detected");
                return;
            }
        }

        // Step 4: Print the distance matrix
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
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

        floydWarshall(graph, V);
    }
}
