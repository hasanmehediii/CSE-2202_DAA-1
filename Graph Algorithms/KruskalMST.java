import java.util.*;

public class KruskalMST {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else if (rank[rootJ] < rank[rootI]) {
                    parent[rootJ] = rootI;
                } else {
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
            }
        }
    }

    public static void kruskalMST(List<Edge> edges, int V) {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);
        List<Edge> result = new ArrayList<>();
        int i = 0, e = 0;

        while (e < V - 1 && i < edges.size()) {
            Edge edge = edges.get(i++);
            int x = ds.find(edge.src);
            int y = ds.find(edge.dest);

            if (x != y) {
                result.add(edge);
                ds.union(x, y);
                e++;
            }
        }

        System.out.println("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (Edge edge : result) {
            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
            minimumCost += edge.weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskalMST(edges, V);
    }
}
