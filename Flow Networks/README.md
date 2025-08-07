# Flow Networks

This directory contains implementations of various algorithms for solving problems related to flow networks.

## Algorithms

### 1. Ford-Fulkerson Algorithm

*   **Theory:** The Ford-Fulkerson method is a greedy approach for computing the maximum flow in a network. It is based on three key concepts: residual graphs, augmenting paths, and cuts. A residual graph indicates the additional flow possible for each edge. An augmenting path is a simple path from the source to the sink in the residual graph. The algorithm repeatedly finds an augmenting path and increases the flow along this path until no more augmenting paths can be found. The max-flow min-cut theorem states that the maximum flow in a network is equal to the minimum capacity of an s-t cut.

*   **Applications:**
    *   **Bipartite Matching:** Finding the maximum number of pairings between two sets of objects.
    *   **Network Reliability:** Determining the connectivity of a network after failures.
    *   **Resource Allocation:** Optimally assigning resources to tasks.

*   **Implementation Process:**
    1.  Initialize the flow in the network to zero.
    2.  While there is an augmenting path from the source to the sink in the residual graph:
        a. Find the bottleneck capacity (the minimum residual capacity) of the path.
        b. Augment the flow along the path by this bottleneck capacity.
        c. Update the residual graph accordingly.
    3.  The total flow computed is the maximum flow.

### 2. Edmonds-Karp Algorithm

*   **Theory:** The Edmonds-Karp algorithm is a specific implementation of the Ford-Fulkerson method. It uses Breadth-First Search (BFS) to find the augmenting path with the minimum number of edges in the residual graph. This choice guarantees that the algorithm terminates and runs in polynomial time, specifically O(V * E^2), where V is the number of vertices and E is the number of edges.

*   **Applications:** Similar to Ford-Fulkerson, it is used in scenarios where a guaranteed performance bound is required. It is particularly effective for graphs with integer capacities.

*   **Implementation Process:**
    1.  The implementation is identical to the Ford-Fulkerson method, with the specific requirement that the augmenting path is found using BFS.
    2.  BFS starts at the source and explores the graph layer by layer, ensuring that the path found is the shortest in terms of the number of edges.

### 3. Dinic's Algorithm

*   **Theory:** Dinic's algorithm is a more efficient max-flow algorithm. It works in phases. In each phase, it constructs a "level graph" using BFS, which is a subgraph containing only edges that lead to a vertex with a higher level (distance from the source). Then, it finds a "blocking flow" in this level graph using Depth-First Search (DFS). A blocking flow is a flow that saturates at least one edge on every augmenting path in the level graph. This process is repeated until the sink is no longer reachable from the source in the level graph.

*   **Applications:**
    *   **Computer Vision:** Used in image segmentation and object recognition.
    *   **Network Routing:** Optimizing data flow in communication networks.
    *   **Competitive Programming:** A popular choice for problems requiring fast max-flow computations.

*   **Implementation Process:**
    1.  Initialize flow to zero.
    2.  While the sink is reachable from the source:
        a. Construct a level graph using BFS from the source. If the sink is not reachable, terminate.
        b. Find a blocking flow from the source to the sink in the level graph using DFS.
        c. Add the blocking flow to the total maximum flow.
    3.  Return the total flow.

### 4. Push-relabel Algorithm

*   **Theory:** The push-relabel algorithm takes a different approach. It maintains a "preflow," which is a flow that can violate the flow conservation constraint (i.e., inflow can exceed outflow). The algorithm maintains a height for each vertex. It pushes flow from vertices with excess flow to their neighbors at a lower height. If a vertex with excess flow has no neighbors at a lower height, its height is increased (a "relabel" operation). The algorithm terminates when no more push or relabel operations are possible.

*   **Applications:** It is efficient for dense graphs and is used in various optimization problems.

*   **Implementation Process:**
    1.  Initialize a preflow by sending as much flow as possible from the source.
    2.  Initialize the height of the source to |V| and all other vertices to 0.
    3.  While there is an active node (a node with excess flow, other than the source or sink):
        a. Select an active node `u`.
        b. Try to push flow from `u` to its neighbors.
        c. If no push is possible, relabel `u` by increasing its height.
    4.  The excess flow at the sink is the maximum flow.

### 5. Min-cost Max-flow

*   **Theory:** This is a class of problems where the goal is to find a flow of a certain value (often the maximum possible flow) that has the minimum possible cost. Each edge in the network has an associated cost per unit of flow.

*   **Applications:**
    *   **Transportation and Logistics:** Finding the cheapest way to transport goods from suppliers to consumers.
    *   **Network Design:** Designing networks with minimum cost while meeting capacity requirements.
    *   **Resource Allocation:** Assigning resources to tasks with varying costs.

*   **Implementation Process:** This is a general problem that can be solved by several algorithms, including the Successive Shortest Path and Cycle Canceling algorithms.

### 6. Successive Shortest Path Algorithm

*   **Theory:** This algorithm solves the min-cost max-flow problem by extending the Ford-Fulkerson method. Instead of finding just any augmenting path, it finds the one with the minimum cost in the residual graph. This is achieved by using a shortest path algorithm like Bellman-Ford or SPFA (if edge costs can be negative) or Dijkstra with potentials (if costs are non-negative).

*   **Applications:** Solves min-cost max-flow problems.

*   **Implementation Process:**
    1.  Initialize flow and cost to zero.
    2.  While there is an augmenting path from the source to the sink in the residual graph:
        a. Find the shortest path from the source to the sink based on cost (e.g., using Bellman-Ford).
        b. Determine the bottleneck capacity of this path.
        c. Augment the flow along this path and update the total cost.
    3.  Return the total flow and minimum cost.

### 7. Cycle Canceling Algorithm

*   **Theory:** This algorithm provides another way to solve the min-cost max-flow problem. It first establishes a maximum flow, ignoring costs. Then, it iteratively finds negative cost cycles in the residual graph and pushes flow through them. Pushing flow through a negative cycle maintains the total flow value but reduces the total cost. The algorithm terminates when no more negative cost cycles exist.

*   **Applications:** Solves min-cost max-flow problems.

*   **Implementation Process:**
    1.  Compute a maximum flow from the source to the sink (e.g., using Edmonds-Karp).
    2.  While a negative cost cycle exists in the residual graph:
        a. Find a negative cost cycle using an algorithm like Bellman-Ford.
        b. Determine the capacity of the cycle.
        c. Augment the flow in the cycle by its capacity and update the total cost.
    3.  Return the max flow and the final minimum cost.

### 8. Capacity Scaling Algorithm

*   **Theory:** This is a scaling-based variation of the successive shortest path algorithm for the min-cost max-flow problem. It considers only edges with a residual capacity greater than or equal to a scaling factor, `delta`. It finds augmenting paths in this restricted graph. The `delta` value starts large and is gradually reduced (typically halved in each phase). This approach can be more efficient, especially when edge capacities are large.

*   **Applications:** Solves min-cost max-flow problems, particularly useful for networks with a wide range of capacities.

*   **Implementation Process:**
    1.  Initialize `delta` to the largest power of 2 less than or equal to the maximum capacity.
    2.  While `delta >= 1`:
        a. Find augmenting paths in the residual graph, considering only edges with residual capacity `>= delta`.
        b. Use a shortest path algorithm to find min-cost paths.
        c. Augment flow along these paths.
        d. Update `delta = delta / 2`.
    3.  Return the flow and cost.

### 9. Preflow-push Algorithm

*   **Theory:** This is the generic version of the push-relabel algorithm for solving the max-flow problem. The core idea is to work with a preflow and iteratively push excess flow from higher-height vertices to lower-height vertices until the flow is balanced everywhere except the source and sink.

*   **Applications:** Same as the push-relabel algorithm.

*   **Implementation Process:** Same as the push-relabel algorithm.

### 10. Relabel-to-front Algorithm

*   **Theory:** This is a specific, and often more practical, implementation of the push-relabel algorithm. It maintains a list of all vertices (except the source and sink). It processes the list, and for each vertex, it performs push and relabel operations until its excess flow is zero (this is called a "discharge" operation). If a vertex is relabeled, it is moved to the front of the list. This heuristic often leads to fewer relabel operations and better performance in practice.

*   **Applications:** Same as the push-relabel algorithm, but often faster in practice.

*   **Implementation Process:**
    1.  Initialize the preflow and heights.
    2.  Create a list of all vertices except the source and sink.
    3.  Iterate through the list of vertices:
        a. Select a vertex `u`.
        b. Discharge `u` by pushing its excess flow to neighbors or relabeling `u` if necessary.
        c. If `u` is relabeled, move it to the front of the list.
    4.  Return the excess flow at the sink.