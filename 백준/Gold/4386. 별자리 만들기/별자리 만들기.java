import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<Edge> graph;
    private static int[] parent;
    private static double[][] starts;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        graph = new ArrayList<>();
        starts = new double[n][2];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            starts[i][0] = Float.parseFloat(tokenizer.nextToken());
            starts[i][1] = Float.parseFloat(tokenizer.nextToken());
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double distance = Math.sqrt(
                        Math.pow(starts[i][0] - starts[j][0], 2) + Math.pow(starts[i][1] - starts[j][1], 2)
                );
                graph.add(new Edge(i, j, distance));
            }
        }
        Collections.sort(graph, Comparator.comparing(edge -> edge.weight));
    }

    private static void findAnswer() {
        double mstCost = 0.0;
        int edgesUsed = 0;
        for (Edge edge : graph) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstCost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) {
                    break;
                }
            }
        }
        System.out.printf("%.2f", mstCost);
    }

    private static int find(int from) {
        if (parent[from] == from) {
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