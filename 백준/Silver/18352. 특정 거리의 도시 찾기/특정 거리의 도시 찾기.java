import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int x;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            graph.get(start).add(end);
        }
    }

    private static void findAnswer() {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];
        dp[x] = 0;
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        visited[x] = true;
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Integer path : graph.get(cur)) {
                if (!visited[path]) {
                    dp[path] = dp[cur] + 1;
                    que.offer(path);
                    visited[path] = true;
                }
            }
        }
        boolean found = false;
        for (int i = 1; i <= n; i++) {
            if (dp[i] == k) {
                found = true;
                System.out.println(i);
            }
        }
        if (!found) {
            System.out.println(-1);
        }
    }
}