import java.util.*;
import java.io.*;

public class Main {

    private static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            String s = input.readLine();
            for (int j = 1; j <= n; j++) {
                map[i][j] = s.charAt(j - 1) - '0';
            }
        }
        dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
    }

    private static void findAnswer() {
        dp[1][1] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] dir : direction) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if (isInRange(nextR, nextC)) {
                    if (map[nextR][nextC] == 1) {
                        if (dp[nextR][nextC] > dp[r][c] + 1) {
                            dp[nextR][nextC] = dp[r][c] + 1;
                            queue.offer(new int[]{nextR, nextC});
                        }
                        continue;
                    }
                    if (dp[nextR][nextC] > dp[r][c]) {
                        dp[nextR][nextC] = dp[r][c];
                        queue.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }
        System.out.println(dp[m][n]);
    }

    private static boolean isInRange(int nextR, int nextC) {
        return 1 <= nextR && nextR < m + 1 && 1 <= nextC && nextC < n + 1;
    }
}