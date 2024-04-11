import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] dist;
    private static List<List<Node>> graph;
    private static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    private static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        init();
        int answer = findAnswer(1, n);
        output.write(String.valueOf(answer));
        output.flush();
        output.close();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        graph = new ArrayList<>();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < n + 1; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    private static int findAnswer(int start, int end) {
        priorityQueue = new PriorityQueue<>();
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