import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static int[] start = new int[2];
    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
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
                if (map[i][j] == 2) {
                    start = new int[] {i, j};
                }
            }
        }
    }

    private static void findAnswer() {
        int[][] dis = new int[n][m];
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        que.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curDis = cur[2];
            dis[curR][curC] = curDis;
            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    que.offer(new int[] {nextR, nextC, curDis + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    answer.append(-1).append(" ");
                    continue;
                }
                answer.append(dis[i][j]).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && c >= 0 && c < m;
    }
}
