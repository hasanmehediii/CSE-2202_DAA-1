// Reverse DFS = Kosaraju's Algorithm (To find Strongly Connected Components)
// Steps:
//     1. Get nodes in stack (topological sort)
//     2. Transpose the graph
//     3. Do DFS according to stack nodes on the transpose graph

import java.util.*;
import java.util.Stack;

public class Kosaraju {
    static class Edge {
        int src;
        int dest;

        public Edge (int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 3));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 4));
    }

    public static void dfs (ArrayList<Edge> graph[], int curr, boolean vis[]) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for (int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }
    }

    public static void topologicalSort (ArrayList<Edge> graph[], int curr, boolean vis[], Stack<Integer> s) {
        vis[curr] = true;
        for (int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topologicalSort(graph, e.dest, vis, s);
            }
        }

        s.push(curr);
    }

    public static void kosaraju_algo (ArrayList<Edge> graph[], int V) {
        // Step-1: Get nodes in stack (topological sort) O(V+E)
        Stack <Integer> s = new Stack<>();
        boolean vis[] = new boolean[V];

        for (int i=0; i<V; i++) {
            if (!vis[i]) {
                topologicalSort(graph, i, vis, s);
            }
        }

        // Step-2: Transpose the graph O(V+E)
        ArrayList<Edge> transpose_graph[] = new ArrayList[V];
        for (int i=0; i<graph.length; i++) {
            vis[i] = false;
            transpose_graph[i] = new ArrayList<Edge>();
        }

        for (int i=0; i<V; i++) {
            for (int j=0; j<graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                transpose_graph[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        // Step-3: Do DFS according to stack nodes on the transpose graph O(V+E)
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!vis[curr]) {
                dfs(transpose_graph, curr, vis);
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int V = 5;

        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        kosaraju_algo(graph, V);
    }
}
