import java.util.*;

public class TopoSort_SCC {
    private int V;
    private List<List<Integer>> adj;
    private List<List<Integer>> revAdj;

    public TopoSort_SCC(int V) {
        this.V = V;
        adj = new ArrayList<>();
        revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        revAdj.get(v).add(u);
    }

    // 1. DFS
    public void dfs(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    // 2. Topological Sort (DFS Based)
    public void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        System.out.println("Topological Sort:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // 3. Strongly Connected Components (Kosaraju’s Algorithm)
    public void fillOrder(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    public void dfsTranspose(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : revAdj.get(node)) {
            if (!visited[neighbor]) {
                dfsTranspose(neighbor, visited);
            }
        }
    }

    public void printSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Step 1: Fill nodes in stack by finish time
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // Step 2: Reset visited for second pass
        Arrays.fill(visited, false);

        // Step 3: Process all nodes in order defined by the stack
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfsTranspose(node, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        TopoSort_SCC g = new TopoSort_SCC(5);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("DFS starting from node 0:");
        boolean[] visited = new boolean[5];
        g.dfs(0, visited);
        System.out.println();

        g.topologicalSort();
        g.printSCCs();
    }
}
