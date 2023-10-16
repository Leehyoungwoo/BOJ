import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int row;
    int col;
    int cost;

    public Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }


    @Override
    public int compareTo(Node target) {
        return this.cost - target.cost;
    }
}

public class Main {

    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int T;
    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            N = Integer.parseInt(input.readLine());
            if (N == 0) {
                break;
            }

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());

                }
            }
            T++;
            int minLoopie = findMinLoopie();
            answer.append("Problem ").append(T).append(": ").append(minLoopie).append("\n");
        }

        System.out.println(answer);
    }

    private static int findMinLoopie() {
        int[][] dist = new int[N][N];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
//            int curR = cur.row;
//            int curC = cur.col;

            for (int[] dir : direction) {
                int nextR = cur.row + dir[0];
                int nextC = cur.col + dir[1];
                if (isInRange(nextR, nextC)) {
                    if (map[nextR][nextC] + cur.cost < dist[nextR][nextC]) {
                        dist[nextR][nextC] = map[nextR][nextC] + cur.cost;
                        pq.offer(new Node(nextR, nextC, dist[nextR][nextC]));
                    }
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
