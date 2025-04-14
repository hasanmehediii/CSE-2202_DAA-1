import java.util.*;

class Bridges {
    private int V, time = 0;
    private List<List<Integer>> adj;

    public Bridges(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void findBridges() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V], low = new int[V], parent = new int[V];
        Arrays.fill(parent, -1);

        System.out.println("Bridges:");
        for (int i = 0; i < V; i++)
            if (!visited[i]) dfs(i, visited, disc, low, parent);
    }

    private void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, visited, disc, low, parent);

                low[u] = Math.min(low[u], low[v]);

                if (low[v] > disc[u]) {
                    System.out.println(u + " -- " + v);
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        Bridges g = new Bridges(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.findBridges();
    }
}

