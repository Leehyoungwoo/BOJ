import java.io.*;

public class Main {
    private static int[][] dp = new int[10001][4];
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(input.readLine());
        precompute();

        while (testcase-- > 0) {
            int n = Integer.parseInt(input.readLine());
            answer.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
        }

        System.out.print(answer);
    }

    private static void precompute() {
        dp[1][1] = 1; // 1만 사용
        dp[2][1] = 1; // 1+1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 1+1+1
        dp[3][2] = 1; // 1+2
        dp[3][3] = 1; // 3

        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }
    }
}
