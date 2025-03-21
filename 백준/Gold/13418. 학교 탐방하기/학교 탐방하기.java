import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
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
        edges = new ArrayList<>();
        for (int i = 0; i < m+1; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int cost =0;
            if(c==0) {
cost=1;
            } else{
cost =0;
}
            edges.add(new int[]{a, b, cost});
        }
    }

    private static void findAnswer() {
        // 최악의 경로 - 최적의 경로를 출력하면 됨

        // 최악의 경로 = 오르막길부터 오르기
        edges.sort((a, b) -> b[2] - a[2]);
        int worst = kruskal();
        // 최적의 경로
        edges.sort(Comparator.comparingInt(a -> a[2]));
        int best = kruskal();
        System.out.println(worst - best);
    }

    private static int kruskal() {
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int edgeCount = 0;
        int sum = 0;
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            if (find(a, parent) != find(b, parent)) {
                union(a, b, parent);
                edgeCount++;
                sum += c;
            }

            if (edgeCount == n) {
                return sum * sum;
            }
        }

        return 0;
    }

    private static int find(int a, int[] parent) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a], parent);
    }

    private static void union(int a, int b, int[] parent) {
        int aParent = find(a, parent);
        int bParent = find(b, parent);
        if (aParent <= bParent) {
            parent[bParent] = aParent;
            return;
        }

        parent[aParent] = bParent;
    }
}