package Previous_Year.EvaLab_1;
// 4th problem
import java.util.*;

public class Kingdoms {
    static class Edge {
        int to;
        Edge(int to) {
            this.to = to;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of planets
        int m = sc.nextInt(); // number of teleporters

        List<List<Edge>> graph = new ArrayList<>();
        List<List<Edge>> reverseGraph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // Read the teleporters and construct the graph and reverse graph
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1; // 0-indexed
            int b = sc.nextInt() - 1; // 0-indexed
            graph.get(a).add(new Edge(b));
            reverseGraph.get(b).add(new Edge(a));
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> finishStack = new Stack<>();
        
        // Step 1: Perform DFS on the original graph to get the finishing order
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i, graph, visited, finishStack);
            }
        }

        // Step 2: Perform DFS on the reverse graph in the order of finishing times
        Arrays.fill(visited, false);
        int kingdomCount = 0;
        int[] kingdoms = new int[n];

        while (!finishStack.isEmpty()) {
            int node = finishStack.pop();
            if (!visited[node]) {
                kingdomCount++;
                dfs2(node, reverseGraph, visited, kingdoms, kingdomCount);
            }
        }

        // Output the result
        System.out.println(kingdomCount);
        for (int i = 0; i < n; i++) {
            System.out.print(kingdoms[i] + " ");
        }
        sc.close();
    }

    // DFS on the original graph to compute finishing order
    private static void dfs1(int node, List<List<Edge>> graph, boolean[] visited, Stack<Integer> finishStack) {
        visited[node] = true;
        for (Edge neighbor : graph.get(node)) {
            if (!visited[neighbor.to]) {
                dfs1(neighbor.to, graph, visited, finishStack);
            }
        }
        finishStack.push(node); // Push the node onto the stack when it's finished
    }

    // DFS on the reverse graph to label SCCs (kingdoms)
    private static void dfs2(int node, List<List<Edge>> reverseGraph, boolean[] visited, int[] kingdoms, int kingdomLabel) {
        visited[node] = true;
        kingdoms[node] = kingdomLabel;
        for (Edge neighbor : reverseGraph.get(node)) {
            if (!visited[neighbor.to]) {
                dfs2(neighbor.to, reverseGraph, visited, kingdoms, kingdomLabel);
            }
        }
    }
}
