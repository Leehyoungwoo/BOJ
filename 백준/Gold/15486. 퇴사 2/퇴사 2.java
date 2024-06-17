import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[][] schedule;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        schedule = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            schedule[i][0] = Integer.parseInt(tokenizer.nextToken());
            schedule[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        dp = new int[n + 1];
    }

    private static void findAnswer() {
        for (int i = 1; i <= n; i++) {
            if (dp[i] <= dp[i - 1]) {
                dp[i] = dp[i - 1];
            }
            int time = schedule[i - 1][0];
            int warning = schedule[i - 1][1];
            if (i + time - 1 > n) continue;
            dp[i + time - 1] = Math.max(warning + dp[i - 1], dp[i + time - 1]);
        }
        System.out.println(dp[n]);
    }
}