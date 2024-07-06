import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] distance;
    private static int[] path;
    private static List<Edge>[] graph;
    private static int start;
    private static int end;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void findAnswer() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.offer(new Edge(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.next;
            int curCost = edge.cost;
            if (curCost > distance[cur]) {
                continue;
            }
            for (Edge next : graph[cur]) {
                int nextIdx = next.next;
                int nextCost = next.cost;
                if (distance[nextIdx] > distance[cur] + nextCost) {
                    path[nextIdx] = cur;
                    distance[nextIdx] = distance[cur] + nextCost;
                    pq.offer(new Edge(nextIdx, nextCost));
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        List<Integer> paths = new ArrayList<>();
        answer.append(distance[end]).append("\n");
        findPath(end, paths);
        answer.append(paths.size()).append("\n");
        for (int i = paths.size() - 1; i >= 0; i--) {
            answer.append(paths.get(i)).append(" ");
        }
        System.out.println(answer);
    }

    private static void findPath(int n, List<Integer> paths) {
        if (n == start) {
            paths.add(n);
            return;
        }
        paths.add(n);
        findPath(path[n], paths);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        distance = new int[n + 1];
        path = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph[start].add(new Edge(end, cost));
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        String[] s = input.readLine().split(" ");
        start = Integer.parseInt(s[0]);
        end = Integer.parseInt(s[1]);
    }
}
class Edge {
    int next;
    int cost;

    public Edge(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
}