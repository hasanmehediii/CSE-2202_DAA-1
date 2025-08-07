# Dynamic Programming

This directory contains implementations of various algorithms that use dynamic programming.

## 1. Fibonacci Sequence

The Fibonacci sequence is a series of numbers in which each number is the sum of the two preceding ones, usually starting with 0 and 1. Dynamic programming can be used to efficiently calculate Fibonacci numbers.

### Methods

*   **Memoization (Top-Down)**: This approach uses recursion but stores the results of expensive function calls in a cache (e.g., a dictionary or array) to avoid re-computation.
*   **Tabulation (Bottom-Up)**: This approach builds the solution iteratively from the bottom up. It fills a table (e.g., an array) with results for all subproblems, starting from the base cases.

## 2. Knapsack Problem

The Knapsack Problem is a classic optimization problem. Given a set of items, each with a weight and a value, the goal is to determine the combination of items to include in a knapsack (a bag with a limited weight capacity) to maximize the total value without exceeding the weight limit. This directory contains the 0/1 Knapsack problem, where for each item, you can either take it (1) or leave it (0).

### Dynamic Programming Approach

The core idea is to create a table (a 2D array) to store the maximum value that can be achieved for various combinations of items and knapsack capacities. Let `dp[i][w]` be the maximum value that can be obtained using the first `i` items with a maximum weight capacity of `w`.

For each item `i`, we have two choices:

1.  **Don't include the item `i`**: The maximum value is the same as the maximum value achievable with the previous `i-1` items and the same weight capacity `w`. `dp[i][w] = dp[i-1][w]`
2.  **Include the item `i`**: This is only possible if the weight of item `i` is less than or equal to the current capacity `w`. The value is the value of item `i` plus the maximum value we could get with the remaining capacity (`w - weight[i]`) using the previous `i-1` items. `dp[i][w] = value[i] + dp[i-1][w - weight[i]]`

We take the maximum of these two choices to ensure an optimal solution at each step.

## 3. Longest Common Subsequence (LCS)

The Longest Common Subsequence (LCS) problem aims to find the longest subsequence present in two given sequences.

### Dynamic Programming Approach

The core idea is to build a 2D table that stores the lengths of the longest common subsequences for all prefixes of the two input strings. Let `X` and `Y` be two strings of length `m` and `n` respectively. We create a table `dp[m+1][n+1]`. The value `dp[i][j]` will store the length of the LCS of `X[0...i-1]` and `Y[0...j-1]`.

The table is filled using the following logic:

1.  **If the characters at the current position match** (`X[i-1] == Y[j-1]`): The length of the LCS is one plus the length of the LCS of the strings without these characters. `dp[i][j] = 1 + dp[i-1][j-1]`
2.  **If the characters do not match** (`X[i-1] != Y[j-1]`): The length of the LCS will be the maximum of the LCS of the two subproblems created by excluding one character from either string. `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`

## 4. Rock Climbing Problem

The Rock Climbing Problem, in this context, is about finding the minimum cost path on a 2D grid. Each cell in the grid has a cost, and the goal is to find a path from the bottom row to the top row with the minimum total cost.

### Dynamic Programming Approach

Let `dp[i][j]` be the minimum cost to reach cell `(i, j)` starting from the bottom row (row 0).

*   **Base Case (Bottom Row)**: For the first row (`i = 0`), the cost to reach any cell `(0, j)` is just the cost of the cell itself. `dp[0][j] = C[0][j]`
*   **General Case (Subsequent Rows)**: For any other cell `(i, j)` where `i > 0`, the minimum cost is its own cost plus the minimum of the costs of the three cells below it from which we can move. `dp[i][j] = C[i][j] + min(dp[i-1][j-1], dp[i-1][j], dp[i-1][j+1])`

The final solution is the minimum value in the last row of the `dp` table.
