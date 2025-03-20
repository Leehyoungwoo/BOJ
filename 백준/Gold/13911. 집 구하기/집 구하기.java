import java.io.*;
import java.util.*;

public class Main {
    private static int v, e, x, y;
    private static List<List<int[]>> graph;
    private static Set<Integer> mac, starbucks;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findOptimalHouse());
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        mac = new HashSet<>();
        tokenizer = new StringTokenizer(input.readLine());
        int m = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < m; i++) {
            mac.add(Integer.parseInt(tokenizer.nextToken()));
        }

        starbucks = new HashSet<>();
        tokenizer = new StringTokenizer(input.readLine());
        int s = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < s; i++) {
            starbucks.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }

    private static int findOptimalHouse() {
        int[] macDist = multiSourceDijkstra(mac);  // 모든 맥도날드에서 다익스트라
        int[] starDist = multiSourceDijkstra(starbucks);  // 모든 스타벅스에서 다익스트라

        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            if (!mac.contains(i) && !starbucks.contains(i)) { // 집이 있는 정점만 고려
                if (macDist[i] <= x && starDist[i] <= y) { // 맥세권 & 스세권 만족하는 집 찾기
                    minSum = Math.min(minSum, macDist[i] + starDist[i]);
                }
            }
        }

        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }

    private static int[] multiSourceDijkstra(Set<Integer> sources) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[v + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int src : sources) {
            pq.offer(new int[]{src, 0});
            dist[src] = 0;
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], cost = cur[1];

            if (cost > dist[node]) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0], nextCost = cost + next[1];

                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }

        return dist;
    }
}