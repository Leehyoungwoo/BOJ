import java.io.*;
import java.util.*;

class Main {

    private static int n;
    private static int e;
    private static int[] dist;
    private static List<List<Node>> graph;
    private static PriorityQueue<Node> priorityQueue;
    private static int v1;
    private static int v2;
    private static int INF = 200000000;


    public static void main(String[] args) throws IOException {
        init();
        int answer1 =0;
        int answer2 =0;
        answer1 += dijkstra(1, v1);
        answer1 += dijkstra(v1, v2);
        answer1 += dijkstra(v2, n);
        answer2 += dijkstra(1, v2);
        answer2 += dijkstra(v2, v1);
        answer2 += dijkstra(v1, n);
        int answer = (answer1 >= INF && answer2 >= INF) ? -1 : Math.min(answer1, answer2);

        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }
        tokenizer = new StringTokenizer(input.readLine());
        v1 = Integer.parseInt(tokenizer.nextToken());
        v2 = Integer.parseInt(tokenizer.nextToken());
    }

    private static int dijkstra(int x, int y) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        dist[x] = 0;
        priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(x, 0));
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            int curIdx = cur.idx;
            int curCost = cur.cost;
            if (dist[curIdx] < curCost) {
                continue;
            }

            for (Node next : graph.get(curIdx)) {
                if(dist[next.idx] > next.cost + curCost) {
                    dist[next.idx] = next.cost + curCost;
                    priorityQueue.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[y];
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