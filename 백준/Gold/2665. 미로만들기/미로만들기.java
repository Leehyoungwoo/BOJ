import java.util.*;
import java.io.*;

public class Main {

    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int[][] map;
    private static int[][] change;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        change = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(change[i], Integer.MAX_VALUE);
        }
    }

    private static void findAnswer() {
        Queue<int[]> que = new LinkedList<>();
        change[0][0] = 0;
        que.offer(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] dir : direction) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if (isInRange(nextR, nextC)) {
                    if (map[nextR][nextC] == 1 && change[nextR][nextC] > change[r][c]) {
                        change[nextR][nextC] = change[r][c];
                        que.offer(new int[]{nextR, nextC});
                        continue;
                    }
                    if (map[nextR][nextC] == 0 && change[nextR][nextC] > change[r][c] + 1) {
                        change[nextR][nextC] = change[r][c] + 1;
                        que.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }
        System.out.println(change[n - 1][n - 1]);
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && c >= 0 && c < n;
    }
}