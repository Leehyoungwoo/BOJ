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
        System.out.println(result);
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
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int move = current[2];

            if (r == n - 1 && c == m - 1) {
                return move;
            }

            for (int[] dir : direction) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    visited[nextR][nextC] = true;
                    queue.add(new int[]{nextR, nextC, move + 1});
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int nextR, int nextC) {
        return 0 <= nextR && nextR < n && 0 <= nextC && nextC < m;
    }
}