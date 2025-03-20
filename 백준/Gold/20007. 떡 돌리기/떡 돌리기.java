import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, x, y;
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            // 양방향 도로
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
    }

    private static void findAnswer() {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[y] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{y, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int to = cur[0];
            int cost = cur[1];
            if (dist[to] < cost) {
                continue;
            }
            for (int[] next : graph.get(to)) {
                if (dist[next[0]] > cost + next[1]) {
                    dist[next[0]] = cost + next[1];
                    pq.offer(new int[]{next[0], cost + next[1]});
                }
            }
        }
        // 거리가 가까운 순서로 방문
        Arrays.sort(dist);
        int day = 1;
        int walk = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] * 2 > x) {
                System.out.println(-1);
                return;
            }
            walk += 2 * dist[i];
            if (walk > x) {
                day++;
                walk = 2* dist[i];
            }
        }

        System.out.println(day);
    }
}
