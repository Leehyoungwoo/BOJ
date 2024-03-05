import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private static int n;
    private static int m;
    private static int[][] map;
    private static boolean[][] pool;

    public static void main(String[] args) throws IOException {
        init();
        findPollArea();
        int sum = findWallAmount();
        System.out.println(sum);
    }

    public static int main(int N, int M, int[][] l) {
        n = N;
        m = M;
        map = l;
        pool = new boolean[n][m];
        findPollArea();
        return findWallAmount();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] s = input.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        map = new int[n][m];
        pool = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = input.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
    }

    private static void findPollArea() {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (pool[i][j]) {
                    continue;
                }
                checkArea(i, j);
            }
        }
    }

    private static void checkArea(int r, int c) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { r, c });
        visited[r][c] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (IsInRange(nextR, nextC) && !visited[nextR][nextC] && canflow(r, c, nextR, nextC)) {
                    if (IsMeetWall(nextR, nextC)) {
                        return;
                    }
                    que.offer(new int[] { nextR, nextC });
                    visited[nextR][nextC] = true;
                }
            }
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (visited[i][j]) {
                    pool[i][j] = true;
                }
            }
        }
    }

    private static boolean canflow(int r, int c, int nextR, int nextC) {
        return map[r][c] >= map[nextR][nextC];
    }

    private static boolean IsMeetWall(int r, int c) {
        return r == 0 || r == n - 1 || c == 0 || c == m - 1;
    }

    private static boolean IsInRange(int nextR, int nextC) {
        return 0 <= nextR && nextR < n && 0 <= nextC && nextC < m;
    }

    private static int findWallAmount() {
        boolean[][] visited = new boolean[n][m];
        boolean[][] isUsed = new boolean[n][m];
        int sum = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (pool[i][j] && !visited[i][j]) {
                    int height = findHeight(i, j, visited);
                    for (int k = 1; k < n - 1; k++) {
                        for (int l = 1; l < m - 1; l++) {
                            if (visited[k][l] && !isUsed[k][l]) {
                                sum += height - map[k][l];
                                isUsed[k][l] = true;
                            }
                        }
                    }
                }
            }
        }

        return sum;
    }

    private static int findHeight(int i, int j, boolean[][] result) {
        int h = Integer.MAX_VALUE;
        Queue<int[]> que = new LinkedList<>();
        final boolean[][] visited = new boolean[n][m];

        que.offer(new int[] { i, j });
        visited[i][j] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int[] dir : direction) {
                int nextR = cur[0] + dir[0];
                int nextC = cur[1] + dir[1];

                if (IsInRange(nextR, nextC) && !visited[nextR][nextC]) {
                    if (map[nextR][nextC] > map[cur[0]][cur[1]]) {
                        if (pool[nextR][nextC]) {
                            que.offer(new int[] { nextR, nextC });
                            continue;
                        }

                        h = Math.min(h, map[nextR][nextC]);
                        continue;
                    }
                    visited[nextR][nextC] = true;
                    que.offer(new int[] { nextR, nextC });
                }
            }
        }

        update(result, visited);
        return h;
    }

    private static void update(final boolean[][] src, final boolean[][] target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                src[i][j] = (src[i][j] || target[i][j]);
            }
        }
    }
}