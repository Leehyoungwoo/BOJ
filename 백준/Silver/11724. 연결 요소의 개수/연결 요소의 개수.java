import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static int n;
    private static int m;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void bfs(int node) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(node);
        visited[node] = true;
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (Integer next : graph.get(cur)) {
                if (!visited[next]) {
                    que.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
}