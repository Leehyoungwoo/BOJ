import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static List<int[]> edges;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        parent = new int[n + 1];
        edges = new ArrayList<>();
        // 물을 직접 파는건 0번 노드로 연결
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            int cost = Integer.parseInt(input.readLine());
            edges.add(new int[]{cost, 0, i});
        }

        for (int i = 1; i <= n; i++) {
            String[] s = input.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                int cost = Integer.parseInt(s[j - 1]);
                if (i < j) {
                    edges.add(new int[]{cost, i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        edges.sort(Comparator.comparingInt(a -> a[0]));
        int minCostOfWater = 0;
        int edgeCount = n;
        for (int[] edge : edges) {
            int from = edge[1];
            int to = edge[2];
            int cost = edge[0];
            if (find(from) != find(to)) {
                union(from, to);
                minCostOfWater+=cost;
                edgeCount--;
            }
            if (edgeCount == 0) {
                System.out.println(minCostOfWater);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean allWater(boolean[] water) {
        for (int i = 0; i < water.length; i++) {
            if (!water[i]) {
                return false;
            }
        }

        return true;
    }

    private static int find(int a) {
        if (parent[a] == a) {
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
