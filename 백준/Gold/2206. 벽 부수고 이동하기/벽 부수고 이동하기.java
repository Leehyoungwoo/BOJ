import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private final static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        int result = bfs();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        queue.offer(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];
            int dis = cur[2];
            int breakCnt = cur[3];

            if (row == n - 1 && col == m - 1) {
                return dis;
            }

            for (int[] dir : direction) {
                int nextR = row + dir[0];
                int nextC = col + dir[1];

                if (isInRange(nextR, nextC)) {
                    if (map[nextR][nextC] == 0 && !visited[nextR][nextC][breakCnt]) {
                        queue.offer(new int[]{nextR, nextC, dis + 1, breakCnt});
                        visited[nextR][nextC][breakCnt] = true;
                    } else if (map[nextR][nextC] == 1 && breakCnt == 0 && !visited[nextR][nextC][1]) {
                        queue.offer(new int[]{nextR, nextC, dis + 1, 1});
                        visited[nextR][nextC][1] = true;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
}