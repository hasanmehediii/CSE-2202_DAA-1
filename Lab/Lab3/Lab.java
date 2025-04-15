import java.util.*;

public class Lab {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>();
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();

    static void dfs1(int node) {
        visited[node] = true;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs1(next);
            }
        }
        stack.push(node);
    }

    static void dfs2(int node) {
        visited[node] = true;
        for (int next : reverseGraph.get(node)) {
            if (!visited[next]) {
                dfs2(next);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }

        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs1(i);
            }
        }

        visited = new boolean[n + 1];
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfs2(node);
                sccCount++;
            }
        }
        System.out.println("# of strongly Connected Components: "+sccCount);
        sc.close();
    }
}
