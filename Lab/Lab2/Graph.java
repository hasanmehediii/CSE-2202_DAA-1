import java.io.*;
import java.util.*;

public class Graph {
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(String filename) throws IOException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        this.vertices = sc.nextInt();
        int edges = sc.nextInt();
        adjList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
        }

        sc.close();
    }

    public void addVertex(int n) {
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        vertices += n;
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public int getNumberOfVertices() {
        return vertices;
    }

    public List<Integer> getAdjacentVertices(int v) {
        return adjList.get(v);
    }

    public void displayGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[vertices];
        List<Integer> result = new ArrayList<>();
        dfsHelper(start, visited, result);
        System.out.print("DFS Traversal starting from vertex " + start + ": ");
        for (int v : result) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private void dfsHelper(int v, boolean[] visited, List<Integer> result) {
        visited[v] = true;
        result.add(v);
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topoHelper(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topoHelper(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                topoHelper(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }
}