import java.io.*;
import java.util.*;

class Main {

    private static int testCase;
    private static int n;
    private static int d;
    // start
    private static int c;
    private static int[] dist;
    private static List<List<Node>> graph;
    private static PriorityQueue<Node> pq;
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(input.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            init(input);
            findAnswer();
            int infection = 0;
            int total = 0;

            for (int i = 1; i < n + 1; i++) {
                if (dist[i] != Integer.MAX_VALUE) { //
                    infection++;
                    total = Math.max(total, dist[i]);
                }
            }
            builder.append(infection).append(" ").append(total);
            builder.append("\n");
        }

        System.out.println(builder);
    }

    private static void init(BufferedReader input) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        pq = new PriorityQueue<>();
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[c] = 0;
        dist[0] = 0;

        for (int i = 0; i < d; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            graph.get(b).add(new Node(a, s));
        }
    }

    private static void findAnswer() {
        boolean[] visited = new boolean[n + 1];
        pq.offer(new Node(c, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.idx;
            int curCost = cur.cost;

            if (!visited[cur.idx]) {
                visited[cur.idx] = true;

                for (Node next : graph.get(curIdx)) {
                    if (!visited[next.idx] && dist[next.idx] > curCost + next.cost) {
                        dist[next.idx] = curCost + next.cost;
                        pq.offer(new Node(next.idx, dist[next.idx]));
                    }
                }
            }
        }
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