import java.util.*;

public class KosarajuSCC {
    private int V;
    private List<List<Integer>> adj, revAdj;

    public KosarajuSCC(int V) {
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

    private void dfs(int u, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) dfs(v, visited, stack);
        }
        stack.push(u);
    }

    private void dfsTranspose(int u, boolean[] visited) {
        visited[u] = true;
        System.out.print(u + " ");
        for (int v : revAdj.get(u)) {
            if (!visited[v]) dfsTranspose(v, visited);
        }
    }

    public void printSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs(i, visited, stack);
        }

        Arrays.fill(visited, false);

        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!visited[u]) {
                dfsTranspose(u, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        KosarajuSCC g = new KosarajuSCC(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.printSCCs();
    }
}
