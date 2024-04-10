import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] parent;
    private static List<int[]> graph;

    public static void main(String[] args) throws IOException {
        init();
        long k = kruskal();
        System.out.println(k);
    }

    private static long kruskal() {
        long sum = 0;
        for (int i = 0; i < graph.size(); i++) {
            int a = graph.get(i)[0];
            int b = graph.get(i)[1];
            int cost = graph.get(i)[2];
            if (find(a) != find(b)) {
                sum += cost;
                union(a, b);
            }
        }
        return sum;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                int cost = Integer.parseInt(tokenizer.nextToken());
                if (i == j) {
                    continue;
                }
                int[] g = new int[]{i, j, cost};
                graph.add(g);
            }
        }

        Collections.sort(graph, Comparator.comparingInt(o -> o[2]));
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