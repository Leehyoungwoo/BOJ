import java.io.*;
import java.util.*;

public class Main {

    private static int v;
    private static int e;
    private static int[] parent;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        int k = kruskal();
        System.out.println(k);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        graph = new int[e][3];
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            graph[i][0] = Integer.parseInt(tokenizer.nextToken());
            graph[i][1] = Integer.parseInt(tokenizer.nextToken());
            graph[i][2] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
    }

    private static int kruskal() {
        int sum = 0;
        for (int i = 0; i < e; i++) {
            int a = graph[i][0];
            int b = graph[i][1];
            int cost = graph[i][2];
            if (find(a) != find(b)) {
                union(a, b);
                sum += cost;
            }
        }

        return sum;
    }

    private static void union(int x, int y) {
        int xP = find(x);
        int yP = find(y);
        if (xP != yP) {
            parent[yP] = xP;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}