import java.util.*;

public class ArticulationPoints {
    private int V, time = 0;
    private List<List<Integer>> adj;

    public ArticulationPoints(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void findArticulationPoints() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V], low = new int[V], parent = new int[V];
        boolean[] ap = new boolean[V];
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++)
            if (!visited[i]) dfs(i, visited, disc, low, parent, ap);

        System.out.print("Articulation Points: ");
        for (int i = 0; i < V; i++)
            if (ap[i]) System.out.print(i + " ");
    }

    private void dfs(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v, visited, disc, low, parent, ap);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) ap[u] = true;
                if (parent[u] != -1 && low[v] >= disc[u]) ap[u] = true;
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        ArticulationPoints g = new ArticulationPoints(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.findArticulationPoints();
    }
}

