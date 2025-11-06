import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static Planet[] planets;
    private static PriorityQueue<Edge> edges;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        init();
        long answer = findAnswer();

        System.out.println(answer);
    }

    private static long findAnswer() {
        // 우선 간선을 세팅할건데 x순으로 한번, y순으로 오름차순해서 한번, z순으로 오름차순해서 한번 하면 작은 순서대로 정렬이 되는데
        // 그러면 i, i + 1이 인접 행성 기준으로 가장 가까운 거리가 되는거 아닌가? 그러면 그렇게 from, to 간선을 모두 edges에 담아두고 3(n - 1)개
        // 모든 행성이 연결될때까지, 간선이 n - 1개 생길떄까지 크루스칼 돌리면서 sun을 더해주면 되는듯
        setting();
        long sum = 0;
        int link = 0;

        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            int from = cur.from;
            int to = cur.to;
            int w = cur.w;
            if (find(from) != find(to)) {
                union(from, to);
                sum += w;
                link++;
            }

            if (link == n - 1) {
                break;
            }
        }

        return sum;
    }

    private static int find(int a) {
        if (a == parent[a]) return a;

        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent != bParent) {
            parent[bParent] = aParent;
            return;
        }

        parent[aParent] = bParent;
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        planets = new Planet[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int z = Integer.parseInt(tokenizer.nextToken());
            planets[i] = new Planet(i, x, y, z);
        }

        parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        edges = new PriorityQueue<>();
    }

    private static void setting() {
        // x, y, z순으로 i ~ i + 1의 모든 간선을 우선 순위 큐에 넣자
        // 우선 x 순 정렬
        Arrays.sort(planets, Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < n - 1; i++) {
            Planet from = planets[i];
            Planet to = planets[i + 1];
            edges.offer(new Edge(from.idx, to.idx, Math.abs(from.x - to.x)));
        }

        Arrays.sort(planets, Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < n - 1; i++) {
            Planet from = planets[i];
            Planet to = planets[i + 1];
            edges.offer(new Edge(from.idx, to.idx, Math.abs(from.y - to.y)));
        }

        Arrays.sort(planets, Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < n - 1; i++) {
            Planet from = planets[i];
            Planet to = planets[i + 1];
            edges.offer(new Edge(from.idx, to.idx, Math.abs(from.z - to.z)));
        }
    }
}

class Planet {
    int idx;
    int x;
    int y;
    int z;

    Planet(int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Edge implements Comparable<Edge> {
    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }

    int from;
    int to;
    int w;

    Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}