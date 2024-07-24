import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] parent;
    private static int[][] gods;
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        parent = new int[n];
        gods = new int[n][2];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            gods[i][0] = x;
            gods[i][1] = y;
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken()) - 1; 
            int to = Integer.parseInt(tokenizer.nextToken()) - 1;
            union(from, to);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(
                        Math.pow(gods[i][0] - gods[j][0], 2) + Math.pow(gods[i][1] - gods[j][1], 2)
                );
                edges.add(new Edge(i, j, distance));
            }
        }
    }

    private static void findAnswer() {
        double mstCost = 0;
        int count = 0;
        Collections.sort(edges, Comparator.comparingDouble(edge -> edge.weight));
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstCost += edge.weight;
                count++;
                if (count == n - 1) {
                    break;
                }
            }
        }
        System.out.printf("%.2f", mstCost);
    }

    private static int find(int from) {
        if (from == parent[from]) {
            return from;
        }

        return parent[from] = find(parent[from]);
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
}

class Edge {
    int from;
    int to;
    double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}