import java.io.*;
import java.util.*;

public class Main {

    private static int n, l, r;
    private static int[][] map;
    private static final int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(findAnswer());
    }

    private static int findAnswer() {
        int day = 0;

        while (true) {
            boolean[][] visited = new boolean[n][n];
            boolean moved = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (bfsAndShare(i, j, visited)) {
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) break;
            day++;
        }

        return day;
    }

    private static boolean bfsAndShare(int i, int j, boolean[][] visited) {
        Deque<int[]> que = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>();

        que.offer(new int[]{i, j});
        visited[i][j] = true;
        union.add(new int[]{i, j});
        int sum = map[i][j];

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curR = cur[0], curC = cur[1];

            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                if (!isInRange(nextR, nextC) || visited[nextR][nextC]) continue;

                int diff = Math.abs(map[curR][curC] - map[nextR][nextC]);
                if (l <= diff && diff <= r) {
                    visited[nextR][nextC] = true;
                    que.offer(new int[]{nextR, nextC});
                    union.add(new int[]{nextR, nextC});
                    sum += map[nextR][nextC];
                }
            }
        }

        if (union.size() == 1) return false;

        int avg = sum / union.size();
        for (int[] cell : union) {
            map[cell[0]][cell[1]] = avg;
        }
        
        return true;
    }

    private static boolean isInRange(int r0, int c0) {
        return 0 <= r0 && r0 < n && 0 <= c0 && c0 < n;
    }

    private static void init() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
