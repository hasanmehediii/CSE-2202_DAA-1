# Greedy Algorithms

This directory contains implementations of various greedy algorithms.

## 1. Activity Selection Problem

The Activity Selection Problem involves finding the maximum number of activities that a single person or machine can perform in a given timeframe, where each activity has a specific start and finish time and activities cannot overlap.

### Greedy Approach

The greedy approach is to always choose the activity that finishes earliest. This maximizes the remaining time available for other activities.

1.  **Sort**: Sort the activities based on their finish times in ascending order.
2.  **Select**: Choose the first activity from the sorted list.
3.  **Iterate**: For each subsequent activity, if its start time is greater than or equal to the finish time of the previously selected activity, then select it.

## 2. Coin Change Problem

The Coin Change problem involves finding the minimum number of coins to make a specific amount of change. The greedy algorithm for this problem is not always optimal for all coin systems.

### Greedy Approach

1.  Sort the available coin denominations in descending order.
2.  Start with the largest denomination.
3.  Take as many coins of the current denomination as possible without exceeding the remaining amount.
4.  Move to the next smaller denomination and repeat until the amount is zero.

## 3. Huffman Coding

Huffman Coding is a lossless data compression algorithm that uses a greedy approach to assign variable-length codes to characters based on their frequencies. It uses shorter codes for more frequent characters.

### Greedy Approach

The algorithm builds a Huffman tree from the bottom up by repeatedly merging the two nodes with the lowest frequencies.

1.  **Count Frequencies**: Calculate the frequency of each character.
2.  **Create Leaf Nodes**: Create a leaf node for each character and add them to a priority queue.
3.  **Build the Tree**: Repeatedly extract the two nodes with the minimum frequencies, create a new internal node with their combined frequency, and add it back to the priority queue until only one node (the root) remains.
4.  **Assign Codes**: Traverse the tree to assign binary codes (0 for left, 1 for right).

## 4. Job Scheduling Problem

Given a set of jobs, where each job has a profit and a deadline, the goal is to find a schedule that maximizes the total profit. Each job takes one unit of time.

### Greedy Approach

1.  **Sort**: Sort all jobs in descending order of their profit.
2.  **Initialize**: Create a schedule (timeline) array.
3.  **Schedule**: Iterate through the sorted jobs. For each job, find the latest available time slot on or before its deadline and schedule it.

## 5. Fractional Knapsack Problem

Given a set of items with weights and values, and a knapsack with a maximum weight capacity, the goal is to maximize the total value. You can take fractions of items.

### Greedy Approach

1.  **Calculate Value-to-Weight Ratio**: For each item, calculate the ratio of its value to its weight.
2.  **Sort Items**: Sort all items in descending order based on this ratio.
3.  **Fill the Knapsack**: Iterate through the sorted items. Add whole items if they fit. If an item doesn't fit completely, take a fraction of it to fill the remaining capacity.
