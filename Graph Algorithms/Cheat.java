// Prim Algorithm

// import java.util.*;

// public class Prims {
//     static class Edge {
//         int src;
//         int dest;
//         int weight;

//         public Edge (int s, int d, int w) {
//             this.src = s;
//             this.dest = d;
//             this.weight = w;
//         }
//     }

//     public static void createGraph(ArrayList<Edge> graph[]) {
//         for (int i=0; i<graph.length; i++) {
//             graph[i] = new ArrayList<Edge>();
//         }

//         graph[0].add(new Edge(0, 1, 10));
//         graph[0].add(new Edge(0, 2, 15));
//         graph[0].add(new Edge(0, 3, 30));

//         graph[1].add(new Edge(1, 0, 10));
//         graph[1].add(new Edge(1, 3, 40));

//         graph[2].add(new Edge(2, 0, 15));
//         graph[2].add(new Edge(2, 3, 50));

//         graph[3].add(new Edge(3, 1, 40));
//         graph[3].add(new Edge(3, 2, 50));
//     }

//     public static class Pair implements Comparable<Pair>{
//         int node;
//         int cost;

//         public Pair (int n, int c) {
//             this.node = n;
//             this.cost = c;
//         }

//         @Override
//         public int compareTo(Pair p2) {
//             return this.cost - p2.cost; // Ascending order
//         }
//     }

//     public static void prims_algo(ArrayList<Edge> graph[], int V) {
//         PriorityQueue<Pair> pq = new PriorityQueue<>(); // non-mst set
//         boolean vis[] = new boolean[V]; // mst set
//         pq.add(new Pair(0, 0));

//         int mstCost = 0;

//         while (!pq.isEmpty()) {
//             Pair curr = pq.remove();
//             if (!vis[curr.node]) {
//                 vis[curr.node] = true;
//                 mstCost += curr.cost;

//                 for (int i=0; i<graph[curr.node].size(); i++) {
//                     Edge e = graph[curr.node].get(i);
//                     if (!vis[e.dest]) {
//                         pq.add(new Pair(e.dest, e.weight));
//                     }
//                 }
//             }
//         }

//         System.out.println("Minimum Cost of MST: " + mstCost);
//     }

//     public static void main(String[] args) {
//         int V = 4;

//         ArrayList<Edge> graph[] = new ArrayList[V];
//         createGraph(graph);
//         prims_algo(graph, V);
//     }
// }

// --------------------------------------------------------------------------

// Kruskal Algorithm

// import java.util.*;

// public class Kruskal {
//     static class Edge {
//         int src;
//         int dest;
//         int weight;

//         public Edge(int s, int d, int w) {
//             this.src = s;
//             this.dest = d;
//             this.weight = w;
//         }
//     }

//     // Union-Find (Disjoint Set) Data Structure
//     static class DisjointSet {
//         int[] parent, rank;

//         public DisjointSet(int n) {
//             parent = new int[n];
//             rank = new int[n];
//             for (int i = 0; i < n; i++) {
//                 parent[i] = i;
//                 rank[i] = 0;
//             }
//         }

//         public int find(int u) {
//             if (parent[u] != u) {
//                 parent[u] = find(parent[u]); // Path compression
//             }
//             return parent[u];
//         }

//         public void union(int u, int v) {
//             int rootU = find(u);
//             int rootV = find(v);

//             // Union by rank
//             if (rootU != rootV) {
//                 if (rank[rootU] > rank[rootV]) {
//                     parent[rootV] = rootU;
//                 } else if (rank[rootU] < rank[rootV]) {
//                     parent[rootU] = rootV;
//                 } else {
//                     parent[rootV] = rootU;
//                     rank[rootU]++;
//                 }
//             }
//         }
//     }

//     public static void kruskalAlgo(ArrayList<Edge> edges, int V) {
//         // Step 1: Sort all edges by their weight
//         Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

//         DisjointSet ds = new DisjointSet(V);
//         int mstCost = 0;

//         // Step 2: Iterate through sorted edges and pick the smallest edge that does not form a cycle
//         for (Edge e : edges) {
//             int srcParent = ds.find(e.src);
//             int destParent = ds.find(e.dest);

//             if (srcParent != destParent) {
//                 ds.union(srcParent, destParent);
//                 mstCost += e.weight;
//                 System.out.println("Edge: " + e.src + " - " + e.dest + " with weight " + e.weight);
//             }
//         }

//         System.out.println("Minimum Cost of MST: " + mstCost);
//     }

//     public static void createGraph(ArrayList<Edge> edges) {
//         edges.add(new Edge(0, 1, 10));
//         edges.add(new Edge(0, 2, 15));
//         edges.add(new Edge(0, 3, 30));
//         edges.add(new Edge(1, 3, 40));
//         edges.add(new Edge(2, 3, 50));
//     }

//     public static void main(String[] args) {
//         int V = 4;
//         ArrayList<Edge> edges = new ArrayList<>();
//         createGraph(edges);
//         kruskalAlgo(edges, V);
//     }
// }

// ---------------------------------------------------------------------

// Dijkstra Algorithm

// import java.util.*;

// public class Dijkstra {
//     static class Edge {
//         int src;
//         int dest;
//         int weight;

//         public Edge (int s, int d, int w) {
//             this.src = s;
//             this.dest = d;
//             this.weight = w;
//         }
//     }

//     public static void createGraph(ArrayList<Edge> graph[]) {
//         for (int i=0; i<graph.length; i++) {
//             graph[i] = new ArrayList<Edge>();
//         }

//         graph[0].add(new Edge(0, 1, 2));
//         graph[0].add(new Edge(0, 2, 4));

//         graph[1].add(new Edge(1, 3, 7));
//         graph[1].add(new Edge(1, 2, 1));

//         graph[2].add(new Edge(2, 4, 3));

//         graph[3].add(new Edge(3, 5, 1));

//         graph[4].add(new Edge(4, 3, 2));
//         graph[4].add(new Edge(4, 5, 5));
//     }

//     public static class Pair implements Comparable<Pair>{
//         int node;
//         int dist;

//         public Pair(int n, int d) {
//             this.node = n;
//             this.dist = d;
//         }

//         @Override
//         public int compareTo (Pair p2) {
//             return this.dist - p2.dist; // ascending order
//             // return  p2.dist - this.dist; // descending order
//         }
//     }

//     // Time Complexity: O(E + ElogV)
//     public static void dijkstra(ArrayList<Edge> graph[], int src, int V) {
//         PriorityQueue<Pair> pq = new PriorityQueue<>();
//         int dist[] = new int[V];
//         for (int i=0; i<V; i++) {
//             if (i != src) {
//                 dist[i] = Integer.MAX_VALUE;
//             }
//         }
//         boolean vis[] = new boolean[V];

//         pq.add(new Pair(0, 0));

//         //BFS Code
//         while (!pq.isEmpty()) {
//             Pair curr = pq.remove(); // shortest distance ber hoise
//             if (!vis[curr.node]) {
//                 vis[curr.node] = true;

//                 for(int i=0; i<graph[curr.node].size(); i++) {
//                     Edge e = graph[curr.node].get(i);
//                     int u = e.src;
//                     int v = e.dest;

//                     if (dist[u] + e.weight < dist[v]) {
//                         dist[v] = dist[u] + e.weight; // Relaxation
//                         pq.add(new Pair(v, dist[v]));
//                     }
//                 }
//             }
//         }

//         for (int i=0; i<V; i++) {
//             System.out.print(dist[i] + " ");
//         }
//         System.out.println();

//     }

//     public static void main(String[] args) {
//         int V = 6;

//         ArrayList<Edge> graph[] = new ArrayList[V];
//         createGraph(graph);

//         dijkstra(graph, 0, V);
//     }
// }

// ---------------------------------------------------------------------

// BellmanFord Algorithm

// import java.util.*;

// public class BellmanFord {
//     static class Edge {
//         int src;
//         int dest;
//         int weight;

//         public Edge (int s, int d, int w) {
//             this.src = s;
//             this.dest = d;
//             this.weight = w;
//         }
//     }

//     public static void createGraph(ArrayList<Edge> graph[]) {
//         for (int i=0; i<graph.length; i++) {
//             graph[i] = new ArrayList<Edge>();
//         }

//         graph[0].add(new Edge(0, 1, 2));
//         graph[0].add(new Edge(0, 2, 4));

//         graph[1].add(new Edge(1, 2, -4));

//         graph[2].add(new Edge(2, 3, 2));

//         graph[3].add(new Edge(3, 4, 4));

//         graph[4].add(new Edge(4, 1, -1));
//         // graph[4].add(new Edge(4, 1, -1));
//     }

//     public static void bellman_ford(ArrayList<Edge> graph[], int src, int V) {
//         int dist[] = new int[V];
//         for (int i=0; i<V; i++) {
//             if(i != src) {
//                 dist[i] = Integer.MAX_VALUE;
//             }
//         }

//         for (int k=0; k<V-1; k++) {
//             for (int i=0; i<V; i++) {
//                 for (int j=0; j<graph[i].size(); j++) {
//                     Edge e = graph[i].get(j);
//                     int u = e.src;
//                     int v = e.dest;

//                     if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
//                         dist[v] = dist[u] + e.weight;
//                     }
//                 }
//             }
//         }

//         // To detect negative weight cycle
//         for (int i=0; i<V; i++) {
//             for (int j=0; j<graph[i].size(); j++) {
//                 Edge e = graph[i].get(j);
//                 int u = e.src;
//                 int v = e.dest;

//                 if (dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
//                     System.out.println("negative weight cycle exists");
//                 }
//             }
//         }

//         for (int i=0; i<dist.length; i++) {
//             System.out.print(dist[i] + " ");
//         }
//         System.out.println();
//     }

//     public static void main(String[] args) {
//         int V = 5;

//         ArrayList<Edge> graph[] = new ArrayList[V];
//         createGraph(graph);
//         bellman_ford(graph, 0, V);
//     }
// }

// ---------------------------------------------------------------------

// Floyad Warshall ALgorithm

// import java.util.*;

// public class FloydWarshall {
//     public static void floydWarshall(int[][] graph, int V) {
//         // Step 1: Initialize the distance matrix
//         int[][] dist = new int[V][V];
//         for (int i = 0; i < V; i++) {
//             for (int j = 0; j < V; j++) {
//                 if (graph[i][j] == 0 && i != j) {
//                     dist[i][j] = Integer.MAX_VALUE; // No path
//                 } else {
//                     dist[i][j] = graph[i][j];
//                 }
//             }
//         }

//         // Step 2: Floyd-Warshall Algorithm
//         for (int k = 0; k < V; k++) { // intermediate vertex
//             for (int i = 0; i < V; i++) { // source vertex
//                 for (int j = 0; j < V; j++) { // destination vertex
//                     if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
//                         dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
//                     }
//                 }
//             }
//         }

//         // Step 3: Check for negative weight cycles
//         for (int i = 0; i < V; i++) {
//             if (dist[i][i] < 0) {
//                 System.out.println("Negative weight cycle detected");
//                 return;
//             }
//         }

//         // Step 4: Print the distance matrix
//         System.out.println("Shortest distances between every pair of vertices:");
//         for (int i = 0; i < V; i++) {
//             for (int j = 0; j < V; j++) {
//                 if (dist[i][j] == Integer.MAX_VALUE) {
//                     System.out.print("INF ");
//                 } else {
//                     System.out.print(dist[i][j] + " ");
//                 }
//             }
//             System.out.println();
//         }
//     }

//     public static void main(String[] args) {
//         int V = 5;
//         int[][] graph = {
//             {0, 2, 4, 0, 0},
//             {0, 0, -4, 0, 0},
//             {0, 0, 0, 2, 0},
//             {0, 0, 0, 0, 4},
//             {0, -1, 0, 0, 0}
//         };

//         floydWarshall(graph, V);
//     }
// }
