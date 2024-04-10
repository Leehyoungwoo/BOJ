import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] parent;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        int k = kruskal();
        System.out.println(k);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        graph = new int[m][3];
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int n1 = Integer.parseInt(tokenizer.nextToken());
            int n2 = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph[i][0] = n1;
            graph[i][1] = n2;
            graph[i][2] = cost;
        }
        Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));
    }

    private static int kruskal() {
        int sum = 0;
        for (int i = 0; i < graph.length; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                union(graph[i][0], graph[i][1]);
                sum += graph[i][2];
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
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}