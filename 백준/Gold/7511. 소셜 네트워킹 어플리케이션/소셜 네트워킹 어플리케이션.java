import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static int k;
    private static int[] parent;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            init(input);
            answer.append("Scenario " + i + ":").append("\n");
            findAnswer(input, answer);
        }

        System.out.println(answer);
    }

    private static void findAnswer(BufferedReader input, StringBuilder answer) throws IOException {
        m = Integer.parseInt(input.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            if (find(a) == find(b)) {
                answer.append(1).append("\n");
            } else {
                answer.append(0).append("\n");
            }
        }
        answer.append("\n");
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        k = Integer.parseInt(input.readLine());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < k; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            union(a, b);
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot <= bRoot) {
            parent[bRoot] = aRoot;
            return;
        }
        parent[aRoot] = bRoot;
    }
}