import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = input.readLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        dp[0][0] = map[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                } else if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + map[i][j];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }
        System.out.println(max);
    }
}