import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int[] parent;
    private static int[] pay;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        k = Integer.parseInt(s[2]);
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        pay = new int[n + 1];
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            pay[i] = Integer.parseInt(tokenizer.nextToken());
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            union(a, b);
        }
    }

    private static void findAnswer() {
        int[] minCost = new int[n + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            int root = find(i);
            minCost[root] = Math.min(minCost[root], pay[i]);
        }

        int totalCost = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) { 
                totalCost += minCost[i];
            }
        }

        if (totalCost <= k) {
            System.out.println(totalCost);
        } else {
            System.out.println("Oh no");
        }
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

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}