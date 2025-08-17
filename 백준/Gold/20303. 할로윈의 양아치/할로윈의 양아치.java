import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, k;
    private static int[] candy;
    private static int[] parent;
    private static int[] sum;   // 컴포넌트별 사탕 합
    private static int[] cnt;   // 컴포넌트별 인원 수

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        // 용량은 "K-1 명 이하"가 조건이라 dp 크기를 K로 만들고 dp[K-1]이 답
        int cap = k - 1;
        int[] dp = new int[Math.max(1, cap + 1)];

        // 각 컴포넌트(루트)에 대해 0/1 배낭
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i && cnt[i] > 0) { // i가 루트이면서 사람 있는 컴포넌트만
                int weight = cnt[i];
                int value  = sum[i];
                for (int w = cap; w >= weight; w--) {
                    dp[w] = Math.max(dp[w], dp[w - weight] + value);
                }
            }
        }

        System.out.println(dp[cap]);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        candy = new int[n + 1];
        parent = new int[n + 1];
        sum = new int[n + 1];
        cnt = new int[n + 1];

        for (int i = 1; i <= n; i++) parent[i] = i;

        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            candy[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            union(u, v);
        }

        // 컴포넌트별 인원/사탕 합 집계
        for (int i = 1; i <= n; i++) {
            int root = find(i);
            cnt[root] += 1;
            sum[root] += candy[i];
        }
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        parent[pb] = pa;
    }
}