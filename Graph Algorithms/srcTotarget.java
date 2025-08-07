import java.util.*;

public class srcTotarget {
    static class Edge {
        int src;
        int dest;

        public Edge (int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void modified_dfs(ArrayList<Edge> graph[], int curr, boolean vis[]) {
        System.out.print(curr +" ");
        vis[curr] = true;

        for (int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (vis[e.dest] == false) {
                modified_dfs(graph, e.dest, vis);
            }
            
        }
    }

    public static void printAllPath(ArrayList<Edge> graph[], boolean vis[], int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path + " ");
            return;
        }

        for (int i = 0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                vis[curr] = true;
                printAllPath(graph, vis, e.dest, path + " " + e.dest, tar);
                vis[curr] = false;
            }
        }
    }

    public static void main(String[] args) {
        int V = 7;

        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        int src = 0, tar = 3;
        printAllPath(graph, new boolean[V], src, "0", tar);
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        // to avoid null pointer exception
        for (int i = 0; i<graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        } 

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 2));
    }
}

