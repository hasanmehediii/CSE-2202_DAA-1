import java.util.*;

public class Kruskal {
    static class Edge {
        int src;
        int dest;
        int weight;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.weight = w;
        }
    }

    // Union-Find (Disjoint Set) Data Structure
    static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]); // Path compression
            }
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            // Union by rank
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    public static void kruskalAlgo(ArrayList<Edge> edges, int V) {
        // Step 1: Sort all edges by their weight
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(V);
        int mstCost = 0;

        // Step 2: Iterate through sorted edges and pick the smallest edge that does not form a cycle
        for (Edge e : edges) {
            int srcParent = ds.find(e.src);
            int destParent = ds.find(e.dest);

            if (srcParent != destParent) {
                ds.union(srcParent, destParent);
                mstCost += e.weight;
                System.out.println("Edge: " + e.src + " - " + e.dest + " with weight " + e.weight);
            }
        }

        System.out.println("Minimum Cost of MST: " + mstCost);
    }

    public static void createGraph(ArrayList<Edge> edges) {
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(1, 3, 40));
        edges.add(new Edge(2, 3, 50));
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> edges = new ArrayList<>();
        createGraph(edges);
        kruskalAlgo(edges, V);
    }
}

