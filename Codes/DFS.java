import java.util.*;

class Edge {
    int dest;
    
    Edge(int dest) {
        this.dest = dest;
    }
}

public class DFS {
    public static void main(String[] args) {
        int V = 6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        graph[0].add(new Edge(1));
        graph[0].add(new Edge(2));
        graph[1].add(new Edge(3));
        graph[1].add(new Edge(4));
        graph[2].add(new Edge(5));

        DFS_perform(graph, V);
    }

    public static void DFS_perform(ArrayList<Edge> graph[], int V) {
        boolean[] vis = new boolean[V];
        DFS_util(graph, 0, vis);
    }

    public static void DFS_util(ArrayList<Edge> graph[], int current, boolean[] vis) {
        vis[current] = true;
        System.out.print(current + " ");

        for (Edge edge : graph[current]) {
            int neighbor = edge.dest;
            if (!vis[neighbor]) {
                DFS_util(graph, neighbor, vis);
            }
        }
    }
}
