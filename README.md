# CSE-2202_DAA-1
Design and Analysis of Algorithms

This repository contains implementations of various algorithms and data structures related to the Design and Analysis of Algorithms course.

## Algorithms Implemented

### Graph Algorithms

*   **Breadth-First Search (BFS):** A graph traversal algorithm that explores all the neighbor nodes at the present depth before moving on to the nodes at the next depth level. It's used to find the shortest path in an unweighted graph.
*   **Depth-First Search (DFS):** An algorithm for traversing or searching tree or graph data structures. The algorithm starts at the root node and explores as far as possible along each branch before backtracking.
*   **Topological Sort:** A linear ordering of vertices in a directed acyclic graph (DAG) such that for every directed edge from vertex u to vertex v, u comes before v in the ordering. It's often used in scheduling tasks with dependencies. A topological sort is possible if and only if the graph has no directed cycles.
*   **Strongly Connected Components (SCC):** In a directed graph, a strongly connected component is a subgraph where there is a path from every vertex to every other vertex within that component. SCCs partition the vertices of a directed graph.
*   **Articulation Points (Cut Vertices):** A vertex in a graph which, when removed, increases the number of connected components. These are critical nodes in a network, and their removal can disconnect the network.
*   **Bridges (Cut-Edges):** An edge in a graph whose removal increases the number of connected components. A bridge is an edge that is not part of any cycle.

### Dynamic Programming

*   **Fibonacci Sequence:** A series of numbers where each number is the sum of the two preceding ones, usually starting with 0 and 1.
*   **Knapsack Problem:** A problem in combinatorial optimization where one must select a number of items, each with a weight and a value, to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible.
*   **Longest Common Subsequence (LCS):** The problem of finding the longest subsequence common to all sequences in a set of sequences. Unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences. It has applications in data comparison programs and bioinformatics.
*   **Rock Climbing:** This is not a standard algorithm, but likely refers to a problem of finding a path in a grid, which can be solved with techniques like BFS or DFS.

### Greedy Algorithms

*   **Activity Selection Problem:** The problem of selecting the maximum number of activities that can be performed by a single person or machine, given a set of activities each with a start and finish time. This problem can be optimally solved using a greedy algorithm.
*   **Coin Change Problem:** This problem involves finding the number of ways to make change for a particular amount of money using a given set of coin denominations. It is a classic example of a problem that can be solved using dynamic programming.
*   **Huffman Coding:** A lossless data compression algorithm that assigns variable-length codes to input characters based on their frequencies. More frequent characters get shorter codes, and less frequent characters get longer codes.
*   **Job Scheduling Problem:** A class of problems that involve scheduling jobs on machines to optimize a certain objective, such as minimizing the total time to complete all jobs (makespan).

## How to Contribute

Contributions are welcome! Please follow these steps:

1.  **Fork the repository.**
2.  **Create a new branch** for your feature or bug fix.
3.  **Add your code** and make sure it is well-commented.
4.  **Add a README.md** in the folder of your code with a brief explanation of the algorithm and how to run the code.
5.  **Create a pull request.**