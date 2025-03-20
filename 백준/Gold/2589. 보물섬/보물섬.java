import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static char[][] map;
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static Queue<int[]> points;
    private static int maxWay = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new char[n][m];
        points = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'L') {
                    points.offer(new int[]{i, j});
                }
            }
        }
    }

    private static void findAnswer() {
        // 포인트 별로 최대 거리 찾아야함
        for (int[] point : points) {
            int r = point[0];
            int c = point[1];
            int count = findMaxWay(r, c);
            maxWay = Math.max(maxWay, count);
        }

        System.out.println(maxWay);
    }

    private static int findMaxWay(int r, int c) {
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCount = cur[2];
            count = Math.max(count, curCount);
            for (int[] dir : direction) {
                int nextR = curR + dir[0];
                int nextC = curC + dir[1];
                int nextCount = curCount + 1;
                if (isInRange(nextR, nextC) && map[nextR][nextC] == 'L' && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC, nextCount});
                }
            }
        }

        return count;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}