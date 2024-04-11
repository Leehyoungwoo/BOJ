import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] dist;
    private static List<List<Node>> graph;
    private static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    private static int v1;
    private static int v2;
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer(v1, v2);
        writer.write(String.valueOf(answer));
        writer.flush();
        writer.close();
//        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }
        StringTokenizer tokenizer;
        for (int i = 0; i < m; i++) {
             tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, cost));
        }
        tokenizer = new StringTokenizer(input.readLine());
        v1 = Integer.parseInt(tokenizer.nextToken());
        v2 = Integer.parseInt(tokenizer.nextToken());
    }

    private static int findAnswer(int start, int end) {
        boolean[] visited = new boolean[n + 1];
        dist[start] = 0;
        priorityQueue.offer(new Node(start, 0));
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            int curIdx = cur.idx;
            int curCost = cur.cost;
            if (visited[curIdx]) {
                continue;
            }
            visited[curIdx] = true;

            for (Node next : graph.get(curIdx)) {
                if (!visited[next.idx] && dist[next.idx] > next.cost + curCost) {
                    dist[next.idx] = next.cost + curCost;
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