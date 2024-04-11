import java.io.*;
import java.util.*;

class Main {

    private static int n;
    private static int m;
    private static int x;
    private static int[] dist;
    private static List<List<Node>> graph;
    private static PriorityQueue<Node> priorityQueue;
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= n; i++) {
            int length = findAnswer(i);
            answer = Math.max(answer, length);
        }

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, cost));
        }
    }

    private static int findAnswer(int idx) {
        return dijkstra(idx, x) + dijkstra(x, idx);
    }

    private static int dijkstra(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, MAX_VALUE);
        dist[0] = 0;
        dist[start] = 0;
        priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            int curIdx = cur.idx;
            int curCost = cur.cost;
            if (!visited[curIdx]) {
                visited[curIdx] = true;
            }
            for (Node next : graph.get(curIdx)) {
                if (!visited[next.idx] && dist[next.idx] > curCost + next.cost) {
                    dist[next.idx] = curCost + next.cost;
                    priorityQueue.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[end];
    }
}

class Node implements Comparable<Node> {
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}