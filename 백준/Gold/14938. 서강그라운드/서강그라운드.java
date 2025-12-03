import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, r;
    private static int[] items;
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static int findAnswer() {
        int maxItem = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int item = findItem(i);
            maxItem = Math.max(maxItem, item);
        }

        return maxItem;
    }

    private static int findItem(int start) {
        int sum = 0;
        int[] dist = new int[n  +1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{start, 0});
        dist[start] = 0;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            for (int[] next : graph.get(curNode)) {
                if (dist[next[0]] > curDist + next[1]) {
                    dist[next[0]] = curDist + next[1];
                    que.offer(new int[]{next[0], curDist + next[1]});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                sum += items[i];
            }
        }

        return sum;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());
        items = new int[n + 1];
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(tokenizer.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost});
        }
    }
}