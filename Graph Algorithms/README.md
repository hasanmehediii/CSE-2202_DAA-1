# Graph Algorithms

This directory contains implementations of various graph algorithms.

## 1. Bellman-Ford Algorithm

The Bellman-Ford algorithm computes the shortest paths from a single source vertex to all other vertices in a weighted, directed graph. It is more versatile than Dijkstra's algorithm because it can handle graphs with negative edge weights.

### How it Works

1.  **Initialization**: The distance to the source vertex is set to 0, and the distances to all other vertices are set to infinity.
2.  **Edge Relaxation**: The algorithm iterates through all the edges of the graph `|V| - 1` times, where `|V|` is the number of vertices. In each iteration, for every edge from vertex `u` to vertex `v` with weight `w`, it checks if the path through `u` is shorter than the known path to `v`. If `distance[u] + w < distance[v]`, it updates `distance[v]`.
3.  **Negative Cycle Detection**: After `|V| - 1` iterations, a final iteration is performed over all edges. If any distance can still be shortened, it means the graph contains a negative-weight cycle.

### Time and Space Complexity

*   **Time Complexity**: O(V * E)
*   **Space Complexity**: O(V)

## 2. Breadth-First Search (BFS)

Breadth-First Search (BFS) is an algorithm for traversing or searching tree and graph data structures. It starts at a selected "source" node and explores all of its neighbors at the present depth before moving on to the nodes at the next depth level.

### How it Works

1.  **Initialization**: Start with a graph and a source vertex. Create a queue and a "visited" list. Add the source vertex to the queue and mark it as visited.
2.  **Exploration**: While the queue is not empty, remove the first vertex from the queue.
3.  **Visit Neighbors**: Add all of its unvisited adjacent neighbors to the back of the queue and mark them as visited.
4.  **Repeat**: Continue this process until the queue is empty.

### Applications

*   Finding the shortest path in unweighted graphs.
*   Web crawlers.
*   Network broadcasting.
*   Finding connected components.

### Complexity

*   **Time Complexity**: O(V + E)
*   **Space Complexity**: O(V)

## 3. Cycle Detection

Cycle detection is the problem of determining if a graph contains a cycle.

### Methods

*   **Undirected Graphs**: Using DFS, a cycle is detected if we encounter a visited vertex that is not the immediate parent of the current vertex. Using BFS, a cycle is detected if we encounter a visited node that is not the immediate parent. The Union-Find algorithm can also be used.
*   **Directed Graphs**: Using DFS, a cycle is detected by looking for a "back edge" to an ancestor in the DFS traversal tree. Kahn's algorithm (for topological sort) can also detect cycles.

## 4. Depth-First Search (DFS)

Depth-First Search (DFS) is an algorithm for traversing or searching tree and graph data structures. The process starts at a selected root node and explores as far as possible along each branch before backtracking.

### How it Works

1.  **Start**: Begin at a starting vertex and add it to a stack. Mark it as visited.
2.  **Explore**: Take the top item from the stack and find its adjacent, unvisited neighbors.
3.  **Push**: Add any unvisited neighbors to the top of the stack.
4.  **Repeat**: Continue this process until the stack is empty.
5.  **Backtrack**: If a vertex has no unvisited neighbors, you "backtrack" by popping items from the stack.

### Applications

*   Pathfinding and cycle detection.
*   Topological sorting.
*   Solving puzzles like mazes.
*   Finding connected components.

### Complexity

*   **Time Complexity**: O(V + E)
*   **Space Complexity**: O(V)

## 5. Dijkstra's Algorithm

Dijkstra's algorithm is an algorithm for finding the shortest paths between nodes in a weighted graph. It does not work with negative edge weights.

## 6. Floyd-Warshall Algorithm

The Floyd-Warshall algorithm is an algorithm for finding the shortest paths in a weighted graph with positive or negative edge weights (but no negative cycles). It finds the shortest path between all pairs of vertices.

### Complexity

*   **Time Complexity**: O(V³)
*   **Space Complexity**: O(V²)

## 7. Johnson's Algorithm

Johnson's algorithm finds the shortest paths between all pairs of vertices in a weighted, directed graph. It can handle negative edge weights but no negative-weight cycles. It is efficient for sparse graphs.

### How it Works

1.  A new vertex is added with zero-weight edges to all other vertices.
2.  Bellman-Ford is run from the new vertex to find a "potential" for each vertex.
3.  The edges are reweighted using these potentials to make all weights non-negative.
4.  Dijkstra's algorithm is run from each vertex on the reweighted graph.
5.  The distances are converted back to the original path weights.

### Complexity

*   **Time Complexity**: O(V² log V + VE)

## 8. Kosaraju's Algorithm

Kosaraju's algorithm is a linear-time algorithm used to find the strongly connected components (SCCs) of a directed graph.

### How it Works

1.  **First DFS**: Perform a DFS on the original graph to determine the finishing times of each vertex.
2.  **Transpose Graph**: Reverse the direction of all edges in the graph.
3.  **Second DFS**: Perform a DFS on the transposed graph, visiting vertices in the order of their finishing times from the first DFS. Each tree in the resulting DFS forest is a strongly connected component.

### Complexity

*   **Time Complexity**: O(V + E)
*   **Space Complexity**: O(V)

## 9. Kruskal's Algorithm

Kruskal's algorithm is a greedy algorithm used to find a Minimum Spanning Tree (MST) for a connected, weighted, and undirected graph.

### How it Works

1.  **Sort Edges**: Sort all edges in non-decreasing order of their weights.
2.  **Iterate and Add Edges**: Iterate through the sorted edges. For each edge, if adding it does not form a cycle, add it to the MST.
3.  **Cycle Check**: Use a Disjoint Set Union (DSU) data structure to efficiently detect cycles.

### Complexity

*   **Time Complexity**: O(E log E)
*   **Space Complexity**: O(V + E)

## 10. Prim's Algorithm

Prim's algorithm is a greedy algorithm used to find a Minimum Spanning Tree (MST) for a weighted, undirected graph.

### How it Works

Prim's algorithm builds the MST by growing a single tree, one edge at a time, from an arbitrary starting vertex.

1.  **Initialization**: Choose a starting vertex.
2.  **Grow the Tree**: Identify all edges that connect vertices in the tree to vertices outside the tree.
3.  **Select the Cheapest Edge**: Choose the edge with the minimum weight from this set.
4.  **Add to MST**: Add the chosen edge and the new vertex to the MST.
5.  **Repeat**: Continue until all vertices are in the MST.

### Complexity

*   **Time Complexity**: O(E log V) using a binary heap.

## 11. Tarjan's Algorithm

Tarjan's algorithm is an efficient method for finding the strongly connected components (SCCs) of a directed graph in linear time. It performs a single Depth First Search (DFS) traversal.

### Complexity

*   **Time Complexity**: O(V + E)
*   **Space Complexity**: O(V)

## 12. Topological Sort

Topological sort is a linear ordering of vertices in a directed acyclic graph (DAG) such that for every directed edge from vertex `u` to vertex `v`, `u` comes before `v` in the ordering.

### Algorithms

*   **Kahn's Algorithm (BFS-based)**: Repeatedly finds vertices with no incoming edges.
*   **DFS-based Algorithm**: Adds vertices to the front of the sorted list as the DFS finishes visiting them.

### Complexity

*   **Time Complexity**: O(V + E)