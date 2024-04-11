import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static int[] see;
    private static List<List<Node>> graph;
    private static long[] dist; // int에서 long으로 변경
    private static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    private static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer(0, n - 1); // int에서 long으로 변경
        if(answer == Long.MAX_VALUE) { // Integer.MAX_VALUE에서 Long.MAX_VALUE로 변경
            answer = -1;
        }
        output.write(String.valueOf(answer));
        output.flush();
        output.close();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        see = new int[n];
        dist = new long[n]; // int[]에서 long[]으로 변경
        tokenizer = new StringTokenizer(input.readLine());
        for (int i = 0; i < see.length; i++) {
            see[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.fill(dist, Long.MAX_VALUE); // Integer.MAX_VALUE에서 Long.MAX_VALUE로 변경
        dist[0] = 0;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Node> list = new ArrayList<>();
            graph.add(list);
        }
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            long c = Long.parseLong(tokenizer.nextToken()); // int에서 long으로 변경
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    private static long findAnswer(int start, int end) { // 반환 타입을 long으로 변경
        priorityQueue.offer(new Node(start, 0));
        boolean[] visited = new boolean[n];
        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            int curIdx = cur.idx;
            long curCost = cur.cost; // int에서 long으로 변경
            if (see[curIdx] == 1 && curIdx != n - 1) {
                continue;
            }
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
    long cost; // int에서 long으로 변경

    public Node(int idx, long cost) { // 생성자의 cost 파라미터 타입을 long으로 변경
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost); // 안전한 long 비교로 변경
    }
}