import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static List<Edge> graph;
    private static int totalLength;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                char c = line.charAt(j);
                if (c != '0') {
                    int length = getLength(c);
                    graph.add(new Edge(i, j, length));
                    totalLength += length;
                }
            }
        }
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Collections.sort(graph, Comparator.comparingInt(e -> e.length));
    }

    private static void findAnswer() {
        int mstLength = 0;
        int edgesUsed = 0;

        for (Edge edge : graph) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstLength += edge.length;
                edgesUsed++;
            }
        }

        if (edgesUsed == n - 1) {
            System.out.println(totalLength - mstLength);
        } else {
            System.out.println(-1);
        }
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot < bRoot) {
            parent[bRoot] = aRoot;
            return;
        }
        parent[aRoot] = bRoot;
    }

    private static int find(int from) {
        if (parent[from] == from) {
            return from;
        }
        return parent[from] = find(parent[from]);
    }

    private static int getLength(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
class Edge {
    int from;
    int to;
    int length;

    public Edge(int from, int to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }
}