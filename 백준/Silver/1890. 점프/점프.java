import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] map;
    private final static int[][] direction = {{1, 0}, {0, 1}};
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dfs(0, 0));
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static long dfs(int r, int c) {
        if (r == n - 1 && c == n - 1) {
            return 1;
        }
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        int num = map[r][c];
        dp[r][c] = 0;
        for (int[] d : direction) {
            int nr = r + d[0] * num;
            int nc = c + d[1] * num;
            if (isInRange(nr, nc)) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    private static boolean isInRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}