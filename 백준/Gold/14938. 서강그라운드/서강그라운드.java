import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, r;
    private static int[] items;
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
        r = Integer.parseInt(tokenizer.nextToken());
        items = new int[n];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(tokenizer.nextToken());
        }
        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }
    }

    private static void findAnswer() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = findMaxSum(i);
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    private static int findMaxSum(int idx) {
        int sum = items[idx - 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[idx] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {idx, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curIdx = cur[0];
            int cost = cur[1];
            for (int[] next : graph.get(curIdx)) {
                if (dist[next[0]] > cost + next[1]) {
                    dist[next[0]] = cost + next[1];
                    pq.offer(new int[] {next[0], cost + next[1]});
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (i == idx) {
                continue;
            }
            if (dist[i] <= m) {
                sum+=items[i - 1];
            }
        }

        return sum;
    }
}