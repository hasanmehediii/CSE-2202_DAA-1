
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Dinic {

    private int[][] capacity;
    private int[][] flow;
    private int[] level;
    private int[] ptr;
    private int numVertices;

    public Dinic(int numVertices) {
        this.numVertices = numVertices;
        this.capacity = new int[numVertices][numVertices];
        this.flow = new int[numVertices][numVertices];
        this.level = new int[numVertices];
        this.ptr = new int[numVertices];
    }

    public void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
    }

    public int maxFlow(int s, int t) {
        int maxFlow = 0;
        while (bfs(s, t)) {
            Arrays.fill(ptr, 0);
            int pushed;
            while ((pushed = dfs(s, t, Integer.MAX_VALUE)) > 0) {
                maxFlow += pushed;
            }
        }
        return maxFlow;
    }

    private boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < numVertices; v++) {
                if (level[v] < 0 && capacity[u][v] - flow[u][v] > 0) {
                    level[v] = level[u] + 1;
                    queue.add(v);
                }
            }
        }
        return level[t] != -1;
    }

    private int dfs(int u, int t, int pushed) {
        if (pushed == 0) {
            return 0;
        }
        if (u == t) {
            return pushed;
        }
        for (int v = ptr[u]; v < numVertices; v++) {
            if (level[v] != level[u] + 1 || capacity[u][v] - flow[u][v] == 0) {
                continue;
            }
            int tr = dfs(v, t, Math.min(pushed, capacity[u][v] - flow[u][v]));
            if (tr == 0) {
                continue;
            }
            flow[u][v] += tr;
            flow[v][u] -= tr;
            return tr;
        }
        return 0;
    }

    public static void main(String[] args) {
        Dinic dinic = new Dinic(6);
        dinic.addEdge(0, 1, 16);
        dinic.addEdge(0, 2, 13);
        dinic.addEdge(1, 2, 10);
        dinic.addEdge(1, 3, 12);
        dinic.addEdge(2, 1, 4);
        dinic.addEdge(2, 4, 14);
        dinic.addEdge(3, 2, 9);
        dinic.addEdge(3, 5, 20);
        dinic.addEdge(4, 3, 7);
        dinic.addEdge(4, 5, 4);

        System.out.println("The maximum flow is " + dinic.maxFlow(0, 5));
    }
}
