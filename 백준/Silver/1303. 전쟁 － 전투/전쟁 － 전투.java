import java.util.*;
import java.io.*;

public class Main {

    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n, m;
    private static char[][] map;
    private static int bluePower, whitePower;

    public static void main(String[] args) throws IOException {
        init();
        findPower();
        System.out.println(whitePower + " " + bluePower);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    private static void findPower() {
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int wCnt = 0;
                int bCnt = 0;
                if (map[i][j] == 'W' && !visited[i][j]) {
                    wCnt += bfs(i, j, map[i][j], visited);
                }

                if (map[i][j] == 'B' && !visited[i][j]) {
                    bCnt += bfs(i, j, map[i][j], visited);
                }
                whitePower += Math.pow(wCnt, 2);
                bluePower += Math.pow(bCnt, 2);
            }
        }
    }

    private static int bfs(int r, int c, char color, boolean[][] visited) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{r, c});
        visited[r][c] = true;
        int count = 1;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] d : direction) {
                int nextR = curR + d[0];
                int nextC = curC + d[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == color) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC});
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < m && 0 <= c && c < n;
    }
}