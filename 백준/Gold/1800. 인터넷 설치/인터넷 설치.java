import java.io.*;
import java.util.*;

public class Main {

    private static int n, p, k;
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer();
        System.out.println(answer);
    }

    private static int findAnswer() {
        int maxCost = 0;
        for (int i = 1; i <= n; i++) {
            for (int[] edge : graph.get(i)) {
                maxCost = Math.max(maxCost, edge[1]);
            }
        }

        int left = 0;
        int right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int needExpensive = dijkstra(mid);

            if (needExpensive != Integer.MAX_VALUE && needExpensive <= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static int dijkstra(int mid) {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int usedExpensive = cur[1];

            if (usedExpensive > dist[now]) continue;
            if (usedExpensive > k) continue;

            if (now == n) {
                return usedExpensive;
            }

            for (int[] next : graph.get(now)) {
                int nextNode = next[0];
                int w = next[1];

                int cost = usedExpensive + (w > mid ? 1 : 0);
                if (cost < dist[nextNode] && cost <= k) {
                    dist[nextNode] = cost;
                    pq.offer(new int[]{nextNode, cost});
                }
            }
        }

        return dist[n];
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        p = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < p; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            graph.get(x).add(new int[]{y, w});
            graph.get(y).add(new int[]{x, w});
        }
    }
}