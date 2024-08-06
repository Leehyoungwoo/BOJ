import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int t;
    private static int[] parent;
    private static List<Edge> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int totalCost = 0;
        int edgesUsed = 0;
        int currentCost = 0;
        int numCitiesConquered = 1;

        graph.sort(Comparator.comparingInt(e -> e.cost));
        for (Edge e : graph) {
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                totalCost += e.cost + t * (numCitiesConquered - 1); // 현재 정복한 도시 수에 따라 비용 조정
                edgesUsed++;
                numCitiesConquered++;
                if (edgesUsed == n - 1) break;
            }
        }
        System.out.println(totalCost);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        t = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int fron = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.add(new Edge(fron, to, cost));
            graph.add(new Edge(to, fron, cost));
        }
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    private static int find(int n) {
        if (n == parent[n]) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot < bRoot) {
            parent[bRoot] = aRoot;
            return;
        }
        parent[aRoot] = bRoot;

    }
}
class Edge {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}