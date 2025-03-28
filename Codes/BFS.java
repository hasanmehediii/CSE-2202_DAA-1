import java.util.*;

class Edge {
    int dest;
    
    Edge(int dest) {
        this.dest = dest;
    }
}

public class BFS {
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

        BFS_perform(graph, V);
    }

    public static void BFS_perform(ArrayList<Edge> graph[], int V) {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] vis = new boolean[V];
        
        Q.add(0);
        vis[0] = true;
        
        while (!Q.isEmpty()) {
            int current = Q.remove();
            System.out.print(current + " ");
            
            for (Edge edge : graph[current]) {
                int neighbor = edge.dest;
                if (!vis[neighbor]) {
                    Q.add(neighbor);
                    vis[neighbor] = true;
                }
            }
        }
    }
}
