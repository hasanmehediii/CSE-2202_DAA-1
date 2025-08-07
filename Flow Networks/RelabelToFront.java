
import java.util.LinkedList;

public class RelabelToFront {

    private int[][] capacity;
    private int[][] flow;
    private int[] excess;
    private int[] height;
    private int numVertices;
    private LinkedList<Integer> list;
    private int[] current;

    public RelabelToFront(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
        this.flow = new int[numVertices][numVertices];
        this.excess = new int[numVertices];
        this.height = new int[numVertices];
        this.list = new LinkedList<>();
        this.current = new int[numVertices];
    }

    public void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
    }

    public int maxFlow(int s, int t) {
        initializePreflow(s);

        for (int i = 0; i < numVertices; i++) {
            if (i != s && i != t) {
                list.add(i);
            }
        }

        int p = 0;
        while (p < list.size()) {
            int u = list.get(p);
            int oldHeight = height[u];
            discharge(u);
            if (height[u] > oldHeight) {
                list.remove(p);
                list.addFirst(u);
                p = 0;
            }
            p++;
        }

        return excess[t];
    }

    private void initializePreflow(int s) {
        height[s] = numVertices;
        for (int v = 0; v < numVertices; v++) {
            if (capacity[s][v] > 0) {
                flow[s][v] = capacity[s][v];
                flow[v][s] = -capacity[s][v];
                excess[v] = capacity[s][v];
                excess[s] -= capacity[s][v];
            }
        }
    }

    private void discharge(int u) {
        while (excess[u] > 0) {
            if (current[u] == numVertices) {
                relabel(u);
                current[u] = 0;
            } else {
                int v = current[u];
                if (capacity[u][v] - flow[u][v] > 0 && height[u] == height[v] + 1) {
                    push(u, v);
                } else {
                    current[u]++;
                }
            }
        }
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

    public static void main(String[] args) {
        RelabelToFront rtf = new RelabelToFront(6);
        rtf.addEdge(0, 1, 16);
        rtf.addEdge(0, 2, 13);
        rtf.addEdge(1, 2, 10);
        rtf.addEdge(1, 3, 12);
        rtf.addEdge(2, 1, 4);
        rtf.addEdge(2, 4, 14);
        rtf.addEdge(3, 2, 9);
        rtf.addEdge(3, 5, 20);
        rtf.addEdge(4, 3, 7);
        rtf.addEdge(4, 5, 4);

        System.out.println("The maximum flow is " + rtf.maxFlow(0, 5));
    }
}
