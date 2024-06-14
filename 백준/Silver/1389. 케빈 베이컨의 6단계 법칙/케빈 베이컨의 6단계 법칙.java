import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < n + 1; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private static void findAnswer() {
        int answer = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            int sum = 0;
            for (int j = 1; j < n + 1; j++) {
                boolean[] visited = new boolean[n + 1];
                if (i == j) {
                    continue;
                }
                sum += bfs(i, j, visited);
            }
            if (sum < min) {
                min = sum;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int from, int to, boolean[] visited) {
        int cnt = Integer.MAX_VALUE;
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{from, 0});
        visited[from] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curId = cur[0];
            int dis = cur[1];
            for (Integer next : graph.get(curId)) {
                if (!visited[next]) {
                    que.offer(new int[]{next, dis + 1});
                    visited[next] = true;
                    if (next == to) {
                        cnt = Math.min(cnt, dis + 1);
                    }
                }
            }
        }
        return cnt;
    }
}