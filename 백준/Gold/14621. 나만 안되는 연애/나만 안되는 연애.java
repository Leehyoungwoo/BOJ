import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<Integer> men;
    private static List<Integer> women;
    private static char[] chars;
    private static int[] parent;
    private static List<int[]> edges;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        men = new ArrayList<>();
        women = new ArrayList<>();
        parent = new int[n + 1];
        chars = new char[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            chars[i] = tokenizer.nextToken().charAt(0);
        }
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            if (chars[a] != chars[b]) {
                edges.add(new int[]{cost, a, b});
            }
        }
    }

    private static void findAnswer() {
        int minCost = 0;
        int edgeCount = n;
        edges.sort(Comparator.comparingInt(a -> a[0]));
        for (int[] edge : edges) {
            int a = edge[1];
            int b = edge[2];
            int cost = edge[0];
            if (find(a) != find(b)){
                union(a, b);
                minCost+=cost;
                edgeCount--;
            }

            if (edgeCount == 1) {
                System.out.println(minCost);
                return;
            }
        }

        System.out.println(-1);
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent <= bParent) {
            parent[bParent] = aParent;
            return;
        }

        parent[aParent] = bParent;
    }
}