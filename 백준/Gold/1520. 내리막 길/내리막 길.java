import java.io.*;
import java.util.*;

public class Main {

    private final static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(findAnswer(0, 0));
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
    }

    private static int findAnswer(int row, int col) {
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        dp[row][col] = 0;
        for (int[] d : direction) {
            int nextR = row + d[0];
            int nextC = col + d[1];
            if (isInRange(nextR, nextC) && map[row][col] > map[nextR][nextC]) {
                dp[row][col] += findAnswer(nextR, nextC);
            }
        }

        return dp[row][col];
    }

    private static boolean isInRange(int nextR, int nextC) {
        return 0 <= nextR && nextR < m && 0 <= nextC && nextC < n;
    }
}