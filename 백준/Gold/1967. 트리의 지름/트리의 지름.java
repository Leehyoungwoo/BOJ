import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<List<Edge>> graph;
    private static int max;
    private static int maxNode;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Edge> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
    }

    private static void findAnswer() {
        dfs(1, 0, new boolean[n + 1]);
        max = 0;
        dfs(maxNode, 0, new boolean[n + 1]);
        System.out.println(max);
    }

    private static void dfs(int node, int w, boolean[] visited) {
        visited[node] = true;
        if (w > max) {
            max = w;
            maxNode = node;
        }

        for (Edge next : graph.get(node)) {
            if(!visited[next.child]) {
                dfs(next.child, w + next.cost, visited);
            }
        }
    }
}

class Edge {
    int child;
    int cost;

    public Edge(int child, int cost) {
        this.child = child;
        this.cost = cost;
    }
}