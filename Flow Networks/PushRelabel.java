
public class PushRelabel {

    private int[][] capacity;
    private int[][] flow;
    private int[] excess;
    private int[] height;
    private int numVertices;

    public PushRelabel(int numVertices) {
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
        while ((p = overFlowVertex()) != -1) {
            if (!push(p)) {
                relabel(p);
            }
        }

        int maxFlow = 0;
        for (int i = 0; i < numVertices; i++) {
            maxFlow += flow[i][t];
        }
        return maxFlow;
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

    private boolean push(int u) {
        for (int v = 0; v < numVertices; v++) {
            if (excess[u] > 0 && capacity[u][v] - flow[u][v] > 0 && height[u] > height[v]) {
                push(u, v);
                return true;
            }
        }
        return false;
    }

    private int overFlowVertex() {
        for (int i = 1; i < numVertices - 1; i++) {
            if (excess[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        PushRelabel pr = new PushRelabel(6);
        pr.addEdge(0, 1, 16);
        pr.addEdge(0, 2, 13);
        pr.addEdge(1, 2, 10);
        pr.addEdge(1, 3, 12);
        pr.addEdge(2, 1, 4);
        pr.addEdge(2, 4, 14);
        pr.addEdge(3, 2, 9);
        pr.addEdge(3, 5, 20);
        pr.addEdge(4, 3, 7);
        pr.addEdge(4, 5, 4);

        System.out.println("The maximum flow is " + pr.maxFlow(0, 5));
    }
}
