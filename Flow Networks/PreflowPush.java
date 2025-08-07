
import java.util.Arrays;

public class PreflowPush {

    private int[][] capacity;
    private int[][] flow;
    private int[] excess;
    private int[] height;
    private int numVertices;

    public PreflowPush(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
        this.flow = new int[numVertices][numVertices];
        this.excess = new int[numVertices];
        this.height = new int[numVertices];
    }

    public void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
    }

    public int maxFlow(int s, int t) {
        height[s] = numVertices;
        excess[s] = Integer.MAX_VALUE;
        for (int v = 0; v < numVertices; v++) {
            if (v != s) {
                push(s, v);
            }
        }

        int p;
        while ((p = overFlowVertex(s, t)) != -1) {
            if (!discharge(p, t)) {
                relabel(p);
            }
        }

        return excess[t];
    }

    private void push(int u, int v) {
        int send = Math.min(excess[u], capacity[u][v] - flow[u][v]);
        flow[u][v] += send;
        flow[v][u] -= send;
        excess[u] -= send;
        excess[v] += send;
    }

    private void relabel(int u) {
        int minHeight = Integer.MAX_VALUE;
        for (int v = 0; v < numVertices; v++) {
            if (capacity[u][v] - flow[u][v] > 0) {
                minHeight = Math.min(minHeight, height[v]);
                height[u] = minHeight + 1;
            }
        }
    }

    private boolean discharge(int u, int t) {
        for (int v = 0; v < numVertices; v++) {
            if (u == t) continue;
            if (excess[u] > 0 && capacity[u][v] - flow[u][v] > 0 && height[u] == height[v] + 1) {
                push(u, v);
                return true;
            }
        }
        return false;
    }

    private int overFlowVertex(int s, int t) {
        for (int i = 0; i < numVertices; i++) {
            if (i != s && i != t && excess[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        PreflowPush pp = new PreflowPush(6);
        pp.addEdge(0, 1, 16);
        pp.addEdge(0, 2, 13);
        pp.addEdge(1, 2, 10);
        pp.addEdge(1, 3, 12);
        pp.addEdge(2, 1, 4);
        pp.addEdge(2, 4, 14);
        pp.addEdge(3, 2, 9);
        pp.addEdge(3, 5, 20);
        pp.addEdge(4, 3, 7);
        pp.addEdge(4, 5, 4);

        System.out.println("The maximum flow is " + pp.maxFlow(0, 5));
    }
}
