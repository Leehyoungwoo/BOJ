import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int m;
    private static int[][] map;
    private static int[] parent;
    private static List<Edge> graph;

    public static void main(String[] args) throws IOException {
        init();
        int result = findAnswer();
        System.out.println(result);
    }

    private static int findAnswer() {
        int minSum = 0;
        graph.sort(Comparator.comparingInt(edge -> edge.cost));
        int edgesUsed = 0;

        for (Edge edge : graph) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                minSum += edge.cost;
                edgesUsed++;
            }
        }

        for (int i = 2; i <= parent.length - 1; i++) {
            if (find(1) != find(i)) {
                return -1;
            }
        }

        return minSum;
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent < bParent) {
            parent[bParent] = aParent;
            return;
        }
        parent[aParent] = bParent;
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int idx = 1;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    findIsland(i, j, visited, idx);
                    idx++;
                }
            }
        }

        parent = new int[idx];
        for (int i = 1; i < idx; i++) {
            parent[i] = i;
        }
        graph = new ArrayList<>();

        // 간선 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    addEdges(i, j, map[i][j]);
                }
            }
        }
    }

    private static void findIsland(int r, int c, boolean[][] visited, int idx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = idx;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];

            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];

                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    queue.add(new int[]{nextR, nextC});
                    map[nextR][nextC] = idx;
                }
            }
        }
    }

    private static void addEdges(int r, int c, int islandIdx) {
        for (int[] d : direction) {
            int length = 0;
            int nextR = r + d[0];
            int nextC = c + d[1];

            while (isInRange(nextR, nextC)) {
                if (map[nextR][nextC] == islandIdx) {
                    break;
                }
                if (map[nextR][nextC] != 0) {
                    if (length > 1) {
                        graph.add(new Edge(islandIdx, map[nextR][nextC], length));
                    }
                    break;
                }
                nextR += d[0];
                nextC += d[1];
                length++;
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}

class Edge {
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}