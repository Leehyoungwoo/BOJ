import java.io.*;
import java.util.*;

public class Main {

    private static int m;
    private static int n;
    private static int[] parent;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            String[] s = input.readLine().split(" ");
            if (s.length == 2) {
                if (s[0].equals("0") && s[1].equals("0")) {
                    break;
                }
            }
            m = Integer.parseInt(s[0]);
            n = Integer.parseInt(s[1]);
            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }
            graph = new int[n][3];
            int totalCost = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int cost = Integer.parseInt(tokenizer.nextToken());
                graph[i][0] = a;
                graph[i][1] = b;
                graph[i][2] = cost;
                totalCost += cost;
            }
            Arrays.sort(graph, Comparator.comparingInt(o -> o[2]));

            int sum = 0;
            for (int i = 0; i < n; i++) {
                int a = graph[i][0];
                int b = graph[i][1];
                int cost = graph[i][2];
                if (find(a) != find(b)) {
                    union(a, b);
                    sum += cost;
                }
            }
            answer.append(totalCost - sum).append("\n");
        }
        System.out.println(answer);
    }

    private static void init() {
    }

    private static void findKruskal() {
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