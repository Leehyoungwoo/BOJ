import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int start, end;
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();

        System.out.println(answer);
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
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        tokenizer = new StringTokenizer(input.readLine());
        start = Integer.parseInt(tokenizer.nextToken());
        end = Integer.parseInt(tokenizer.nextToken());
    }

    private static int findAnswer() {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        dist[start] = 0;
        pq.offer(new int[]{start, Integer.MAX_VALUE});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode =  cur[0];
            int curCost = cur[1];

            if (dist[curNode] > curCost) {
                continue;
            }

            if (curNode == end) {
                return curCost;
            }

            for (int[] next : graph.get(curNode)) {
                int nextNode =   next[0];
                int limit = next[1];

                int possible = Math.min(curCost, limit);

                if (dist[nextNode] < possible) {
                    dist[nextNode] = possible;
                    pq.offer(new int[]{nextNode, possible});
                }
            }
        }

        return 0;
    }
}