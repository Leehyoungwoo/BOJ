import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static List<List<int[]>> graph;
    private static int s, t;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(new int[]{to, value});
            graph.get(to).add(new int[]{from, value});
        }
        tokenizer = new StringTokenizer(input.readLine());
        s = Integer.parseInt(tokenizer.nextToken());
        t = Integer.parseInt(tokenizer.nextToken());
    }

    private static void findAnswer() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {s, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int curCost = cur[1];
            if (dist[node] < curCost) {
                continue;
            }
            for (int[] next : graph.get(node)) {
                if (dist[next[0]] > curCost + next[1]) {
                    dist[next[0]] = curCost + next[1];
                    pq.offer(new int[] {next[0], curCost + next[1]});
                }
            }
        }

        System.out.println(dist[t]);
    }
}