import java.io.*;
import java.util.*;

public class Main {

    private static int t;
    private static int n;
    private static int[][] map;
    private static int[][] dp;
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(input.readLine());
        for (int i = 0; i < t; i++) {
            init(input);
            findAnswer();
        }
        System.out.print(answer.toString());
    }

    private static void init(BufferedReader input) throws IOException {
        n = Integer.parseInt(input.readLine());
        map = new int[2][n];
        for (int i = 0; i < 2; i++) {
            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dp = new int[2][n];
    }

    private static void findAnswer() {
        dp[0][0] = map[0][0];
        dp[1][0] = map[1][0];

        if (n > 1) {
            dp[0][1] = map[0][1] + map[1][0];
            dp[1][1] = map[1][1] + map[0][0];
        }

        for (int j = 2; j < n; j++) {
            dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + map[0][j];
            dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + map[1][j];
        }

        answer.append(Math.max(dp[0][n-1], dp[1][n-1])).append("\n");
    }
}