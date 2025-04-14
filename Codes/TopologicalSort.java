import java.util.*;

class TopologicalSort {
    private int V;
    private List<List<Integer>> adj;

    public TopologicalSort(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    private void dfs(int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) dfs(v, visited, stack);
        }
        stack.push(u);
    }

    public void topologicalSort() {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs(i, visited, stack);
        }

        System.out.println("Topological Sort:");
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }

    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        g.topologicalSort();
    }
}