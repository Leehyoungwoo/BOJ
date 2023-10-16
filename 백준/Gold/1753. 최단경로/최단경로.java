import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex>{

    int label;
    int cost;

    public Vertex(int label, int cost) {
        this.label = label;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex target) {
        return this.cost - target.cost;
    }
}

public class Main {

    private static int V;
    private static int E;
    private static int start;
    private static int[] distance;
    private static final int INF = Integer.MAX_VALUE;
    private static PriorityQueue<Vertex> open;
    private static ArrayList<ArrayList<Vertex>> graph;

    public static void main(String[] args) throws IOException {
        init();
        findMinDist(start);
        printAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] split = input.readLine().split(" ");
        V = Integer.parseInt(split[0]);
        E = Integer.parseInt(split[1]);
        start = Integer.parseInt(input.readLine());
        distance = new int[V + 1];
        open = new PriorityQueue<>();
        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            ArrayList<Vertex> adjList = new ArrayList<>();
            graph.add(adjList);
        }

        Arrays.fill(distance, INF);
        distance[start] = 0;

        for (int i = 1; i <= E; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(new Vertex(v, w));
        }
    }

    private static void findMinDist(int start) {
        open.offer(new Vertex(start, 0));

        while (!open.isEmpty()) {
            Vertex cur = open.poll();
            int curVertex = cur.label;

            if (cur.cost > distance[curVertex]) {
                continue;
            }

            for (Vertex neighbor : graph.get(curVertex)) {
                int newDistance = distance[curVertex] + neighbor.cost;

                if (newDistance < distance[neighbor.label]) {
                    distance[neighbor.label] = newDistance;
                    open.offer(new Vertex(neighbor.label, newDistance));
                }
            }
        }
    }

    private static void printAnswer() {
        for (int i = 1; i < distance.length; i++) {
            if(distance[i] == INF) {
                System.out.println("INF");
                continue;
            }
            System.out.println(distance[i]);
        }
    }
}