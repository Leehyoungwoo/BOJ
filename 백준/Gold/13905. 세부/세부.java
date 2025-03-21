import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int s, e;
    private static List<int[]> edges;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        s = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        edges = new ArrayList<>();
        parent = new int[n + 1];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            edges.add(new int[] {a, b, cost});
        }
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        edges.sort((a, b) -> b[2] - a[2]);
    }

    private static void findAnswer() {
        parent[s] = 0;
        // s -> e 하면서 가장 무게 제한이 무거운 다리로 건너고, 그 중에 최솟값이 가지고 갈 수 있는 뺴빼로 아닌가?
        // 다리 무게 제한이 큰 순서로 정렬
        int minWeight = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int weight = edge[2];
            if (find(a) != find(b)) {
                union(a, b);
                minWeight = Math.min(minWeight, weight);
            }

            if (find(s) == find(e)) {
                System.out.println(minWeight);
                return;
            }
        }

        System.out.println(0);
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