import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;
    private static List<List<Edge>> graph;
    private static int answer;
    private static int[] distance;
    private static int[] path;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
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
        distance = new int[n + 1];
        path = new int[n + 1];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(start).add(new Edge(end, cost));
            graph.get(end).add(new Edge(start, cost));
        }
    }

    private static void findAnswer() {
        answer = 0;
        int realTime = findRealTime();
        if (realTime == Integer.MAX_VALUE) {
            answer = -1;
            return;
        }

        int to = n;
        int maxDelay = 0;
        while (to != 1) {
            int from = path[to];
            int delay = findBlockedTime(from, to);
            if (delay == Integer.MAX_VALUE) {
                answer = -1;
                return;
            }
            maxDelay = Math.max(maxDelay, delay);
            to = from;
        }

        if (maxDelay == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = maxDelay - realTime;
        }
    }

    private static int findRealTime() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.end;
            int cost = edge.cost;
            if (cost > distance[cur]) continue;
            for (Edge e : graph.get(cur)) {
                int next = e.end;
                int nextCost = e.cost;
                if (distance[next] > distance[cur] + nextCost) {
                    distance[next] = distance[cur] + nextCost;
                    path[next] = cur;
                    pq.offer(new Edge(next, distance[next]));
                }
            }
        }
        return distance[n];
    }

    private static int findBlockedTime(int from, int to) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.end;
            int cost = edge.cost;
            if (cost > distance[cur]) continue;
            for (Edge e : graph.get(cur)) {
                int next = e.end;
                int nextCost = e.cost;
                if ((cur == from && next == to) || (cur == to && next == from)) {
                    continue;
                }
                if (distance[next] > distance[cur] + nextCost) {
                    distance[next] = distance[cur] + nextCost;
                    pq.offer(new Edge(next, distance[next]));
                }
            }
        }
        return distance[n];
    }
}

class Edge implements Comparable<Edge> {
    int end;
    int cost;

    public Edge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}