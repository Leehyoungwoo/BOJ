import java.io.*;
import java.util.*;

public class Main {

    private static int p;
    private static int w;
    private static int c;
    private static int v;
    private static int[] parent;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findAnswer());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        p = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        c = Integer.parseInt(tokenizer.nextToken());
        v = Integer.parseInt(tokenizer.nextToken());
        parent = new int[p];
        for (int i = 0; i < p; i++) {
            parent[i] = i;
        }
        edges = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int width = Integer.parseInt(tokenizer.nextToken());
            edges.add(new Edge(u, v, width));
        }
    }

    private static int findAnswer() {
        edges.sort((e1, e2) -> e2.width - e1.width);

        for (Edge edge : edges) {
            union(edge.u, edge.v);

            if (find(c) == find(v)) {
                return edge.width;
            }
        }
        return -1;
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA <= rootB) {
            parent[rootB] = rootA;
            return;
        }
        parent[rootA] = rootB;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

class Edge {
    int u;
    int v;
    int width;

    public Edge(int u, int v, int width) {
        this.u = u;
        this.v = v;
        this.width = width;
    }
}